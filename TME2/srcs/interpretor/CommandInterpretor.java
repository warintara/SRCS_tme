package srcs.interpretor;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class CommandInterpretor {
    private final Map<String, Class<? extends Command>> commands = new HashMap<>();
    private final Set<String> internalCommands = new HashSet<>(Arrays.asList("echo", "cat", "deploy", "undeploy"));

    public CommandInterpretor() {
        commands.put("echo", Echo.class);
        commands.put("cat", Cat.class);
        commands.put("deploy", Deploy.class);
        commands.put("undeploy", Undeploy.class);
    }

    public Class<? extends Command> getClassOf(String commandName) {
        return commands.get(commandName);
    }

    public void perform(String line, PrintStream out) throws Exception {
        // Gérer les lignes vides ou composées uniquement d'espaces
        if (line == null || line.trim().isEmpty()) {
            return; // Ne rien faire si la ligne est vide
        }

        // Utiliser StringTokenizer pour séparer les mots
        StringTokenizer tokenizer = new StringTokenizer(line);
        String commandName = tokenizer.nextToken();

        // Vérifier si la commande existe
        Class<? extends Command> commandClass = getClassOf(commandName);
        if (commandClass == null) {
            throw new CommandNotFoundException("Commande inconnue : " + commandName);
        }

        // Construire la liste des arguments
        List<String> argsList = new ArrayList<>();
        argsList.add(commandName);
        while (tokenizer.hasMoreTokens()) {
            argsList.add(tokenizer.nextToken());
        }

        // Instancier et exécuter la commande
        Command command = commandClass.getConstructor(String[].class)
                .newInstance((Object) argsList.toArray(new String[0]));
        command.execute(out);
    }

    // Classe interne pour gérer la commande deploy
    public class Deploy implements Command {
        private final String name;
        private final File rootDir;
        private final String className;

        public Deploy(String[] args) {
            if (args.length != 4) throw new IllegalArgumentException("Arguments invalides pour deploy.");
            this.name = args[1];
            this.rootDir = new File(args[2]);
            this.className = args[3];

            if (!rootDir.exists() || !rootDir.isDirectory()) {
                throw new IllegalArgumentException("Chemin invalide.");
            }

            // Vérifier si la commande existe déjà
            if (CommandInterpretor.this.commands.containsKey(name)) {
                throw new IllegalArgumentException("Une commande avec ce nom existe déjà : " + name);
            }
        }

        @Override
        public void execute(PrintStream out) throws Exception {
            // Charger et déployer la commande
            try (URLClassLoader loader = new URLClassLoader(new URL[]{rootDir.toURI().toURL()})) {
                Class<?> cls = loader.loadClass(className);
                CommandInterpretor.this.commands.put(name, (Class<? extends Command>) cls);
                out.println("Commande " + name + " déployée.");
            }
        }
    }

    // Classe interne pour gérer la commande undeploy
    public class Undeploy implements Command {
        private final String name;

        public Undeploy(String[] args) {
            if (args.length != 2) throw new IllegalArgumentException("Nom de commande requis.");
            this.name = args[1];
        }

        @Override
        public void execute(PrintStream out) {
            // Empêcher la suppression des commandes internes
            if (CommandInterpretor.this.internalCommands.contains(name)) {
                out.println("Impossible de supprimer la commande interne : " + name);
                return;
            }

            // Supprimer la commande si elle existe
            if (CommandInterpretor.this.commands.remove(name) != null) {
                out.println("Commande " + name + " supprimée.");
            } else {
                out.println("Commande " + name + " introuvable.");
            }
        }
    }
}
