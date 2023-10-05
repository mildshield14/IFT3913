package ift3913_tp1;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Tropcomp {

    public static void main(String[] args) throws IOException {
        if (args.length != 4 || !args[0].equals("-o")) {
            System.out.println("Enter in the correct format -o <output-file.csv> <project-path> <seuil%>");
            return;
        }

        String outputFile = args[1];
        String projectPath = args[2];

        String seuilSrting = args[3];
        //Convertir le seuil en nombre double
        double seuil = Double.parseDouble(seuilSrting.replaceAll("%", ""));

        List<Path> javaFiles = new ArrayList<>();// une liste de fichiers qu'on va tester
        List<Integer> tlocV = new ArrayList<>(); // une liste de valeur tloc pour chaque fichier
        List<Integer> tassertV = new ArrayList<>(); // une liste de valeur tassert pour chaque fichier

        /** Analyser l'argument "projectPath" pour obtenir le chemin du projet et parcourir tous les fichiers
         * .java dans le répertoire du projet et les ajouter dans la liste javaFiles.*/

        // Obtenez des idées de cette page: https://zetcode.com/java/fileswalk/
        try {
            Files.walk(Paths.get(projectPath))
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(javaFiles::add);
        } catch (IOException e) {
            System.err.println("Error while traversing the project directory: " + e.getMessage());
            return;
        }

        int totalFiles = javaFiles.size(); // Le nombre total de fichiers Java qui doivent être testés


        // calculer le tloc et tassert de chaque fichier
        for (Path javaFile : javaFiles) {
            int tlocValue = Tloc.countTloc(javaFile.toString());
            int tassertValue = Tassert.countExp(javaFile.toString());

            //Ajouter les valeurs tloc et tassert calculées à la liste correspondante
            tlocV.add(tlocValue);
            tassertV.add(tassertValue);
        }

        // Calculez le nombre de fichiers les plus suspects qui doivent être sélectionnés
        int numOfSuspiciousFiles = (int) Math.ceil((seuil / 100) * totalFiles);

        // Créer deux nouvelles listes, organiser les listes tlocV et tassertV d'origine par ordre décroissant
        // et les copier dans la nouvelle liste.
        List<Integer> sortedTlocValues = new ArrayList<>(tlocV);
        List<Integer> sortedTassertValues = new ArrayList<>(tassertV);
        sortedTlocValues.sort(Collections.reverseOrder());
        sortedTassertValues.sort(Collections.reverseOrder());

        // Filtrer les fichiers les plus suspects. Ici Sélectionner les tloc et les tassert les plus suspects
        List<Integer> selectedTlocValues = sortedTlocValues.subList(0, numOfSuspiciousFiles);
        List<Integer> selectedTassertValues = sortedTassertValues.subList(0, numOfSuspiciousFiles);

        //la liste suspiciousFilesInfo qui enregistre les informations sur les fichiers suspects
        List<String[]> suspiciousFilesInfo = new ArrayList<>();


        // Si le tloc et le tassert d'un fichier peuvent être trouvés dans la liste selectedTlocValues et
        // selectedTassertValues en meme temps, alors le fichier est suspect. Ensuite, utiliser la class tls pour
        // déterminer tout les informations de ce fichier, puis les stocker à la liste suspiciousFilesInfo au format CSV

        for (int i = 0; i < javaFiles.size(); i++) {
            if (selectedTlocValues.contains(tlocV.get(i)) && selectedTassertValues.contains(tassertV.get(i))) {

                Tls.fileOrDirecCSV(Paths.get(javaFiles.get(i).toString()), Paths.get(outputFile));

                Tls.extractData(Paths.get(javaFiles.get(i).toString()));

                String line = String.join(", ", Tls.getDataLines().get(0));

                System.out.println(line);
                }

        }

        if (!outputFile.isEmpty()) {
            System.out.println("Results saved to " + outputFile);
        }


    }
}
