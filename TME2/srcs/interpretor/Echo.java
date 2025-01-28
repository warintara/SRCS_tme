package srcs.interpretor;

import java.io.*;

public class Echo implements Command {
    private final String[] args;

    public Echo(String[] args) {
        this.args = args;
    }

    @Override
    public void execute(PrintStream out) {
        // Construire la sortie en excluant args[0] (nom de la commande)
        for (int i = 1; i < args.length; i++) {
            out.print(args[i]);
            if (i < args.length - 1) {
                out.print(" "); // Ajouter un espace entre les arguments, mais pas après le dernier
            }
        }
        out.println(); // Nouvelle ligne après les arguments
    }
}
