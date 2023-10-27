# Métrique 3 : Code Churn

## Description:

    - Utilisation de script bash

    - Entrées : Lien repo github clonable, path du dossier de tests
    
    - Sorties : Données sauvegardées en format csv

## Guide:
 1.1 Sur votre terminal, naviguez vers le dossier analyse_metrique_age.
     Par exemple en clonant notre repo, le absolute path:

     cd /Users/vennilasooben/IFT3913-1/ift3913_tp2/analyse_metrique_codechurn

 1.2 Entrez la ligne suivante:

     ./script_codechurn.sh https://github.com/jfree/jfreechart.git src/main/java/org/jfree/chart/title

   1.2.1 La sortie se trouve dans le dossier appele temp_dir où on y retrouve jfreechart qu'on a cloné dans le script

