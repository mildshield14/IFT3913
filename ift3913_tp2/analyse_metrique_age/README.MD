# Métrique 3 : Age

## Description:

    - Utilisation de script python

    - Entrées : Lien repo github clonable, path du dossier de tests, path du dossier source
    
    - Sorties : Données sauvegardées en format csv

## Guide:
 1.1 Sur votre terminal, naviguez vers le dossier analyse_metrique_age.
     Par exemple en clonant notre repo, le absolute path:

     cd /Users/vennilasooben/IFT3913-1/ift3913_tp2/analyse_metrique_age

 1.2 Entrez la ligne suivante:

     python script_age.py https://github.com/jfree/jfreechart.git src/test/java/org/jfree/chart/title src/main/java/org/jfree/chart/title

   1.2.1 La sortie se trouve dans le dossier jfreechart qu'on a cloné dans le script
   
   1.2.2. Notez qu'une ligne bizarre apparaît dans le fichier csv il s'agit du fichier package-info.java qui n'a pas de fichier test correspondant.
