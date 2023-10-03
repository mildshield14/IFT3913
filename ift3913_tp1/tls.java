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

import static org.junit.Assert.assertTrue;

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

    public static String findWord(String whatToFind, String line) {

        String regex = whatToFind;

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        boolean matchFound = matcher.find();

        if (matchFound && !line.contains("//") && !line.contains("*")) {
            line = line.replace(whatToFind, "");
            line = line.replace(" ", "");
            line = line.replace("public", "");
            line = line.replace("{", "");
            line = line.replace(";", "");
            return line;
        }

        return null;
    }


    // Source of the 3 following functions i.e convertToCSV(), givenDataArray_whenConvertToCSV_thenOutputCreated() 
    // and escapeSpecialCharacters() : https://www.baeldung.com/java-csv with only modification made with file name
    // and static mode changes
   
    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(Tls::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

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
        assertTrue(csvOutputFile.exists());
       }

    

    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

 // Used https://www.baeldung.com/java-csv for writing to CSV file
    public static void main(String[] args) throws IOException {
         if (args.length ==1) {
             Files.walk(Path.of(args[0])).forEach(path -> fileOrDirec(path));
       }else if (args.length==2){
                // Take folder comme entrée et extraire les données voulues
                Path dir = Paths.get(args[1]);
                Files.walk(dir).forEach(path -> fileOrDirecCSV(path, Path.of(args[0])));
            }
        }


    public static void fileOrDirecCSV(Path file, Path dir) {
        if (file.toFile().isFile() && file.toString().endsWith(".java")) {
            extractData(file);
            try {
                givenDataArray_whenConvertToCSV_thenOutputCreated(getDataLines(), dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void fileOrDirec( Path file) {
        if (file.toFile().isFile() && file.toString().endsWith(".java")) {
            extractData(file);

            for (String[] rowData : dataLines) {
                String line = String.join(", ", rowData);
                System.out.println(line);
            }
        }
    }
}
