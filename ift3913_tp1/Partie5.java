package ift3913_tp1;

import java.io.IOException;

public class Partie5 {
    public static void main(String[] args) {

        // Directory and file name to test code (using JFreeChart)
        String path_file = "/Users/vennilasooben/Downloads/jfreechart/src/test/java/org/jfree/chart/title";
        String csv_file = "etude-jfreechart/csv_file";

        for(int i=1;i<21;i=i+4){

            if (i==9){
                i=10;
            }

            System.out.println(i);


        String[] str= { "-o",csv_file+i+"",path_file, i+""};

        try {
            Tropcomp.main(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
        
    }
}
