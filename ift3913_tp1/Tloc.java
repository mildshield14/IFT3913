package ift3913_tp1;

import java.io.*;

public class Tloc {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please enter a correct file name");
            return;
        }
        String nameSourceFile = args[0]; //argument de la fonction "countTloc"
        int tlocNum = countTloc(nameSourceFile);

        System.out.println("TLOC: " + tlocNum);


    }

    /* Le paramètre de cette fonction est le nom du fichier de test, obtenu depuis le terminal. Cette fonction
    * peut obtenir le nombre réel de lignes de code (hors commentaires et espaces)
    * */
    public static int countTloc(String nameFile) {
        int tlocNum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String lineInfo; // Le contenu de chaque ligne du fichier
            while ((lineInfo = reader.readLine()) != null) {
                lineInfo = lineInfo.trim(); //Supprimez les espaces avant et après une ligne de contenu
                if (!lineInfo.isEmpty() && !lineInfo.startsWith("*")&&!lineInfo.startsWith("/")&& !lineInfo.startsWith("@")) {
                    tlocNum+=1;
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading the file" );
        }

        return tlocNum;
    }


}