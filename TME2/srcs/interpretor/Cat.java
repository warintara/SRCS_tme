package srcs.interpretor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
public class Cat implements Command {
    private final File file;

    public Cat(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Fichier non spécifié.");
        }
        this.file = new File(args[1]);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Fichier invalide.");
        }
    }

    @Override
    public void execute(PrintStream out) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        }
    }
}