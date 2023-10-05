# IFT3913_TP1 par Vennila Sooben (20235256) et Yalin

Date de remise: 06 octobre 2023

Auteurs : 
Vennila Sooben (matricule: 20235356, courriel: vennila.sooben@umontreal.ca, github: https://github.com/mildshield14)
Yalin (matricule: , courriel: , github: )


Lien du repositoire : https://github.com/mildshield14/IFT3913_TP1.git


Guide d'utilisation :

1. Télécharger JFreeChart : https://github.com/jfree/jfreechart
   
   1.1. Copiez le absolute path de son emplacement localement sur votre appareil et remplacez "/Users/vennilasooben/Downloads/jfreechart-master" des étapes 3 par ce path
   
   1.2. Remplacez /Users/vennilasooben/ de l'étape 2 par l'endroit où le repo a été enregistré sur votre appareil


2. Executer les fichiers jar dans le terminal
   
   2.1. Utilisez le absolute path, par exemple
   
       tloc -> cd /Users/vennilasooben/IFT3913_TP1/out/artifacts/tloc_jar
      
       tassert -> cd /Users/vennilasooben/IFT3913_TP1/out/artifacts/tassert_jar
      
       tls -> cd /Users/vennilasooben/IFT3913_TP1/out/artifacts/tls_jar
      
       tropcomp -> cd /Users/vennilasooben/IFT3913_TP1/out/artifacts/tropcomp_jar


   2.2. Faites java -jar du fichier jar voulu suivi des arguments (toujours en absolute path).
   
    2.2.1 Note: N'oubliez pas de rajouter un seuil à tropcomp
   
       tloc -> java -jar tloc.jar /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart/title/ImageTitleTest.java
      
       tassert -> java -jar tassert.jar /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart/title/ImageTitleTest.java
      
       tls -> java -jar tls.jar /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart/title
      
       tropcomp -> java -jar tropcomp.jar /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart/title 20%


3. Autres précisions sur les paramètres des fichiers jar:
   
   3.1. Pour tls et tropcomp vous avez l'option de rajouter un fichier csv.
        Pour votre convenance, nous l'avons déja crée et vous n'avez qu'à utiliser le absolute path
   
        tls -> java -jar tls.jar -o /Users/vennilasooben/IFT3913_TP1 /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart/title
   
        tropcomp -> java -jar tropcomp.jar -o /Users/vennilasooben/IFT3913_TP1 /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart/title 20%

4. Autres commentaires pertinents sur l'éxécution:
   
   4.1. Une éxécution avec le seuils de 1,5,10 % sur jfreechart-master/src/test/java/org/jfree/chart/title ne fonctionnera pas. Essayez en avec des seuils > 18% mais <=100% ou changez de directoire notamment en prenant plus de fichiers comme /Users/vennilasooben/Downloads/jfreechart-master/src/test/java/org/jfree/chart




   
    

