package ift3913_tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Tassert {

    private static ArrayList<String> assertExp;

    /** Obtenir les expressions de la librairie JUnit
     * @return une liste d'expressions de la librairie JUnit **/
    public static ArrayList<String> getAssertExp() {
        return assertExp;
    }

    /** Mettre à jour la liste d'expressions de tests de la librairie JUnit **/
    public static void setAssertExp(ArrayList<String> assertExpr) {
       assertExp = assertExpr;
    }

    public static void main(String[] args) {

    // the idea would be to use a regex to check for either 
    // fail, assertArrayEquals, assertEquals, assertNotEquals, assertFalse, assertNotNull, assertNotSame
    // assertNull, assertSame, assertThat, assertThrows, assertTrue

        int countExp=countExp(args[0].toString());

        System.out.println("Tassert value : " +countExp);

    }

    /** Cette méthode est utilisée pour compter le nombre d'expressions permettant de
     * tester le code
     * @param filename le nom du fichier dont le nombre de lignes de code avec des tests doit être comptabilisé
     * @return le nombre de lignes de code avec des expressions de tests et -1 si le fichier n'en contient pas**/
    public static int countExp(String filename) {

        populateAssertExp();

        int countAssertExp = 0;

        // Lecture du fichier
        try {

            File fileJava = new File(filename);
            Scanner myReader = new Scanner(fileJava);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                boolean found = checkAssertExp(data);

                // mettre a jour le compteur si on trouve une expression de test
                if (found == true) {
                    countAssertExp = countAssertExp + 1;
                }

            }

            myReader.close();

            return countAssertExp;


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return -1;

    }

    /** Cette méthode remplit une liste de type String afin de comparer les lignes de code
     * avec les expressions de JUnit **/
    public static void populateAssertExp(){

        ArrayList<String> tempAssert = new ArrayList<>();

        tempAssert.add("assertArrayEquals");
        tempAssert.add("assertEquals");
        tempAssert.add("assertNotEquals");
        tempAssert.add("assertFalse");
        tempAssert.add("assertNotNull");
        tempAssert.add("assertNotSame");
        tempAssert.add("assertNull");
        tempAssert.add("assertSame");
        tempAssert.add("assertThat");
        tempAssert.add("assertThrows");
        tempAssert.add("assertTrue");
        tempAssert.add("fail");

        setAssertExp(tempAssert);

    }

    /** Cette méthose sert à vérifier si la ligne passé en paramètre contient une expression de test
     * @param data - ligne que l'on veut tester
     * @return boolean - retourne si contient (True) une ligne ou pas (False) **/
    public static boolean checkAssertExp(String data){

        ArrayList<String> regexData = getAssertExp();

        for (int i=0; i<regexData.size();i++){
            // utilisation des regex (liste qu'on a rempli au préalable) pour trouver si ligne = test ou pas
            String regex= regexData.get(i);

            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(data);

            // trouvé (True) ou pas (False)
            boolean matchFound = matcher.find();

            // vérifier s'il s'agit d'un commentaire
            if(matchFound && !data.contains("//") && !data.contains("*")) {
                return true;
            }

        }

        return false;

    }
}
