# Métrique 1 : Execution Time

## Description:

    - Utilisation de script bash

    - Entrées : Lien repo github clonable, path du dossier de tests avec fichier de test finissant par Test.java
    
    - Sorties : Données sauvegardées en format HTML + sorties dans le terminal

## Guide:

 1.1. Téléchargez mvn sur `https://maven.apache.org/download.cgi` et dézippez le

 1.2. Sur votre terminal, entrez
 
    export PATH=/path/to/your/directory/apache-maven-x.y.z/bin:$PATH`
      
   N'oubliez pas de remplacer /path/to/your/directory/apache-maven-x.y.z/bin par votre path local du bin de mvn
      
   Assurez-vous que ceci a fonctionné en entrant dans votre terminal,
   
    mvn -version

  1.3. Vous pouvez désormais roulez nos scripts.
  
   1.3.1. Pour commencer naviguer vers le path du script. Nous supposons que vous êtes déjà sur le dossier de remise:
   
   1.3.1.2 Sinon, vous pouvez utiliser le absolute path en clonant notre repo git, par exemple:
   
    cd /Users/vennilasooben/IFT3913/ift3913_tp2/analyse_metrique_exectime
          
   1.3.2. Assurez-vous d'avoir les permissions en entrant sur le terminal,
   
    chmod +x script_tests_exec.sh
    
   1.3.3. Nous avons essayer de généraliser le code en le faisant rouler sur tous les repos GitHub clonable ayant un folder de tests avec des noms de fichiers finissant par Test.java.

Ici pour tester JFreeChart, entrez le nom du script suivis des 2 arguments (lien github, path des tests)        sur le terminal pour l'éxécution:

    ./script_tests_exec.sh https://github.com/jfree/jfreechart.git src/test/java/org/jfree/chart/title
               
   1.3.4. Si tout se passe correctement, vous avez maintenant un fichier HTML qui se situe dans le dossier jfreechart cloné appelé test_results.HTML
   
    1.3.4.1. Vous pouvez utiliser l'extension Liveserver de Visual Studio Code pour visualiser ce fichier 
               
    1.3.4.2. Vous pouvez ouvrir le fichier avec un navigateur web.
               
    1.3.4.3. (Demande à ChatGPT: écrit un guide sur la manière de visualiser un fichier HTML ; 
                        nous avons obtenu des infos sur comment le faire sur le terminal mais n'avons pas testé pour Windows et Linux)
                        
                         Sur Windows -> start nom_du_fichier.html
                         Sur macOS -> open nom_du_fichier.html
                         Sur Linux -> xdg-open nom_du_fichier.html

                        
  Assurez-vous de remplacer nom_du_fichier.html par le nom réel de votre fichier HTML et d'être sur le bon emplacement(cd).