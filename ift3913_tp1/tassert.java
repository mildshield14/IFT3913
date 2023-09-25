package ift3913_tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class tassert {

    private static ArrayList<String> assertExp;
    private static int countAssertExp;

     public static void setCountAssertExp(int i) {
         tassert.countAssertExp = i;
     }

     public static int getCountAssertExp() {
         return countAssertExp;
     }

    public static ArrayList<String> getAssertExp() {
        return assertExp;
    }

    public static void setAssertExp(ArrayList<String> assertExpr) {
       assertExp = assertExpr;
    }

   public static void main(String[] args) {

    // the idea would be to use a regex to check for either 
    // fail, assertArrayEquals, assertEquals, assertNotEquals, assertFalse, assertNotNull, assertNotSame
    // assertNull, assertSame, assertThat, assertThrows, assertTrue

     setCountAssertExp(0);

     populateAssertExp();

     try {
      File fileJava = new File(args[0]);
      Scanner myReader = new Scanner(fileJava);

      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        boolean found = checkAssertExp(data);

        if(found==true){
            countAssertExp=countAssertExp+1;
        }
        
      }

      myReader.close();
      setCountAssertExp(countAssertExp);
      System.out.println(countAssertExp);
      
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
   }   

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

        setAssertExp(tempAssert);

    }

    public static boolean checkAssertExp(String data){

        ArrayList<String> regexData = getAssertExp();

        for (int i=0; i<regexData.size();i++){

            String regex= regexData.get(i);

            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(data);
            boolean matchFound = matcher.find();

            if(matchFound && !data.contains("//") && !data.contains("*")) {
                return true;
            }

        }

        return false;

    }
}
