package ift3913_tp1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tls {

    private static List<String[]> dataLines = new ArrayList<>();
    private static String packageName;
    private static String className;
    private static boolean isFirstExecution = true;

    public static void setClassName(String classNames) {
        className = classNames;
    }

    public static void setPackageName(String packageNames) {
        packageName = packageNames;
    }

    public static String getClassName() {
        return className;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setDataLines(List<String[]> toWriteInCSV) {
        dataLines = toWriteInCSV;
    }

    public static List<String[]> getDataLines() {
        return dataLines;
    }

    /** Cette méthode est au coeur de Tls. Elle va extraire toutes les infos des fichiers, l'isoler
     * de qui n'est pas important et rajouter tout ce qui est pertient à une liste
     * @param path - folder/fichier dont l'on veut extraire les données**/
    public static void extractData(Path path) {
        
        try {
            String package_name="";
            String class_name="";

            Scanner myReader = new Scanner(path.toFile());

            while (myReader.hasNextLine() ) {
                
                String data = myReader.nextLine();

                package_name = findWord("package", data);

                if (package_name != null) {
                    setPackageName(package_name);
                }

                class_name = findWord(" class ", data);

                if (class_name != null) {
                   setClassName(class_name);
                }

            }

            myReader.close();

            // now we will return all necessary data for CSV file writing

            List<String[]> toWriteInCSV = new ArrayList<>();

            String file_name = path.toFile().toString();

            String tloc_data=Tloc.countTloc(file_name) + "";

            String tassert_data=Tassert.countExp(file_name) + "";

            String file_title= path.toFile().getName().toString();

            if(path.toString().contains("/")){
                file_title= "./" + file_title;
            }

            String tcmp_data="0";
            if (Integer.parseInt(tloc_data)!=0 && Integer.parseInt(tassert_data)!=0){
                tcmp_data=Integer.parseInt(tloc_data)/Integer.parseInt(tassert_data)+"";
            }

            String[] row = {file_title, " " + getPackageName(), " " + getClassName(), " " + tloc_data+ " ", tassert_data, " " + tcmp_data};
            toWriteInCSV.add(row);

            setDataLines(toWriteInCSV);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /** Cette méthode va permettre de trouver certains mots dans une ligne de code
     * On l'utilise afin de trouver des informations telles que les noms du package, classe sans les parenthèses
     * @param whatToFind - ce que l'on recherche
     * @param line - ligne dont on veut véfifier s'il contient l'info de whatToFind
     * @return ce qu'on le recherche comme le nom de la classe sans infos en trop comme les parenthèses
     * ou le nom de la classe**/
    public static String findWord(String whatToFind, String line) {

        String regex = whatToFind;

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        boolean matchFound = matcher.find();

        // we found what we wanted, but now we want to isolate it from irrelevant information
        if (matchFound && !line.contains("//") && !line.contains("*")) {
            line = line.replace(whatToFind, "");
            line = line.replace(" ", "");
            line = line.replace("public", "");
            line = line.replace("{", "");
            line = line.replace(";", "");
            return line;
        }

        // found nothing
        return null;
    }


    // Source of the 3 following functions i.e convertToCSV(), givenDataArray_whenConvertToCSV_thenOutputCreated() 
    // and escapeSpecialCharacters() : https://www.baeldung.com/java-csv with only modification made with file name
    // and static mode changes
    /** Cette méthode va permettre de préparer chaques éléments de la liste pour l'écriture
     * au fichier CSV et va poser un délimiteur ","
     * @param data - lignes que l'on veut rajouter au fichier
     * @see <a href="https://www.baeldung.com/java-csv">Source of the 3 following functions i.e convertToCSV(), givenDataArray_whenConvertToCSV_thenOutputCreated()
     *     // and escapeSpecialCharacters() : https://www.baeldung.com/java-csv with only modification made with file name
     *     // and static mode changes </a>**/
    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(Tls::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    /** Cette méthode va prendre une liste en param et va permettre de déterminer si
     * c'est une première écriture du fichier et va ainsi procéder à l'écriture
     * @param dataLines lignes que l'on veut ajouter au fichier
     * @param dir path du fichier CSV
     * @see <a href="https://stackoverflow.com/questions/3154488/how-do-i-iterate-through-the-files-in-a-directory-and-its-sub-directories-in-java">...</a>*/
    public static void givenDataArray_whenConvertToCSV_thenOutputCreated(List<String[]> dataLines, Path dir) throws IOException {
        File csvOutputFile = (dir.toFile());

        // Check if it's the first execution
        boolean isFirstExecutionLocal = isFirstExecution;

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile, !isFirstExecutionLocal))) {
            if (isFirstExecutionLocal) {
                // If it's the first execution, overwrite the file
                pw.write(""); // Truncate the file
                isFirstExecution = false; // Update to indicate NOT first time
            }

            // inspo : https://stackoverflow.com/questions/3154488/how-do-i-iterate-through-the-files-in-a-directory-and-its-sub-directories-in-ja
            dataLines.stream()
                    .map(Tls::convertToCSV)
                    .forEach(pw::println);
        }
        
       }

    /** Cette méthode va permettre de préparer les données correctement pour une écriture
     * dans un fichier CSV
     * @param data - ligne que l'on veut modifier pour l'adapter au fichier CSV**/
    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

 /** Cette méthode va traverser un folder pour en extraire les fichiers et appliquer les
  * méthodes nécessaires notamment en cas de fichier de csv ou pas
  * @see  <a href="https://www.baeldung.com/java-csv"> for writing to CSV file as reference</a>**/
    public static void main(String[] args) throws IOException {
         if (args.length ==1) {
             Files.walk(Path.of(args[0])).forEach(path -> fileOrDirec(path));
       }else if (args.length==2){
                // Take folder comme entrée et extraire les données voulues
                Path dir = Paths.get(args[1]);
                Files.walk(dir).forEach(path -> fileOrDirecCSV(path, Path.of(args[0])));
            }
        }

    /** Cette méthode est utilisée lorsqu'on veut un fichier CSV et elle
     * va utiliser extractData() afin d'extraire les données du fichier et
     * va passer les infos obtenues à la méthode givenDataArray_whenConvertToCSV_thenOutputCreated
     * @param file - fichier dont l'on veut les infos
     * @param dir  - path du fichier csv **/
    public static void fileOrDirecCSV( Path file, Path dir) {
        if (file.toFile().isFile() && file.toString().endsWith(".java")) {
            extractData(file);
            try {
                givenDataArray_whenConvertToCSV_thenOutputCreated(getDataLines(), dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Cette méthode est utilisée lorsqu'on ne veut pas de fichier CSV et elle
     * va utiliser extractData() afin d'extraire les données du fichier et en faire des sorties en ligne de commande
     * @param file - fichier dont l'on veut les infos **/
    public static void fileOrDirec( Path file) {
        // vérifie si le fichier est un fichier java
        if (file.toFile().isFile() && file.toString().endsWith(".java")) {

            // extrait les données
            extractData(file);

            // rajoute une virgule aux données et print
            for (String[] rowData : dataLines) {
                String line = String.join(", ", rowData);
                System.out.println(line);
            }
        }
    }
}
