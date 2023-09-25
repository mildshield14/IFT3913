import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class tls {

    private static List<String[]> dataLines = new ArrayList<>();
    private static String packageName;
    private static String className;

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

    public static void extractData(File file) {
        
        try {
            String package_name="";
            String class_name="";

            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine() ) {
                
                String data = myReader.nextLine();

                package_name = findWord("package", data);

                if (package_name != null) {
                    System.out.println("found");
                    setPackageName(package_name);
                }

                class_name = findWord("class", data);

                if (class_name != null) {
                    System.out.println("found");
                    setClassName(class_name);
                }

            }

            myReader.close();

            // now we will return all necessary data for CSV file writing

            List<String[]> toWriteInCSV = new ArrayList<>();
            String[] row = {"title name", getPackageName(), getClassName(), "tloc", "tassert", "tcmp"};
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
            return line;
        }

        return null;
    }


    // Source of the 3 following functions i.e convertToCSV(), givenDataArray_whenConvertToCSV_thenOutputCreated() 
    // and escapeSpecialCharacters() : https://www.baeldung.com/java-csv with only modification made with file name
    // and static mode changes
   
    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(tls::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public static void givenDataArray_whenConvertToCSV_thenOutputCreated(List<String[]> dataLines) throws IOException {
        File csvOutputFile = new File("csv_file");
    
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
           
                dataLines.stream()
                        .map(tls::convertToCSV)
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

        // Take folder comme entrée et extraire les données voulues
        Path dir = Paths.get("lib/jfreechart-master/src/test/java/org/jfree/chart/title/TitleTest.java");

       extractData(dir.toFile());
       givenDataArray_whenConvertToCSV_thenOutputCreated(dataLines);
    }
}
