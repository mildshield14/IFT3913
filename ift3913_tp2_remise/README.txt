# IFT3913 TP par Vennila Sooben (20235256) et Yalin Mo (20199655)

# TP2

Date de remise: 06 octobre 2023

Auteurs : 
Vennila Sooben (matricule: 20235356, courriel: vennila.sooben@umontreal.ca, github: https://github.com/mildshield14)

Yalin Mo (matricule: 20199655 , courriel: yalin.mo@umontreal.ca )


Lien du repositoire : `https://github.com/mildshield14/IFT3913.git`

Lien vers notre réponse dans le rapport dans le répositoire : `/ift3913_tp2/IFT3913_TP2_rapport.pdf`


## Guide d'utilisation des scripts par métriques:
## Note importante | Disclaimer :
Nous n'avons pas testé nos scripts dans un environnement Windows. Théoriquement, notre guide devrait suffire mais la réussite de l'éxécution n'est pas garantie.

## Métrique 1 : Execution Time

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

## Metrique 2: Code de test/code principal, le taux de défauts des tests et la longueur moyenne des cas de test.

## Outil de Mesure Tache2

Ce projet utilise un script Python pour analyser les tests du projet JFreeChart, en particulier en testant le rapport code de test/code principal, le taux de défauts des tests et la longueur moyenne des cas de test.

## Prérequis

Avant d'utiliser cet outil, assurez-vous d'avoir les prérequis suivants installés sur votre système :
- Python 3.x.x
- Apache Maven

## Description
Le script réalise les analyses suivantes :

Rapport entre le code de test et le code principal : Il détermine combien de lignes sont dédiées aux tests par rapport au code source principal.
Taux de défauts : Il exécute les tests en utilisant Maven et compte combien d'entre eux échouent.
Nombre moyen de lignes de cas de test : Il calcule la longueur moyenne de chaque fichier de test.

## Comment utiliser
1. Assurez-vous d'avoir Python installé
  1.1 Vous pouvez utiliser la commande "python --version" dans votre terminal pour vérifier s'il est déja installé.
  1.2 Sinon, vous pouvez l'installer dans ce lien : https://www.python.org/

2. Assurez-vous qu'Apache Maven est correctement installé et configuré sur votre système avant d'exécuter le script
  2.1 Vous pouvez exécuter la commande "mvn --version" dans votre terminal.
  2.2 Si la commande Not found s'affiche après avoir exécuté "mvn --version" dans le terminal, cela est généralement dû au fait que Maven n'a pas été ajouté à la variable d'environnement PATH de votre système. 
  
  2.3 Les méthodes suivantes peuvent nous aider à résoudre ce problème.

  2.4 Vérifiez si Maven est installé : confirmez d'abord que vous avez bien installé Maven et que vous pouvez y accéder via le chemin absolu dans le terminal. 
      Vous peuvez exécuter la commande "ind / -name mvn 2>/dev/null"  dans votre terminal pour trouver son chemin absolu.

    Si c'est trouvé: 
      (1)Ajoutez ensuite le chemin absolu à la variable PATH. On peut utiliser la commande :
        -- utilisez Bash comme shell par défaut:
        "echo 'export PATH=/path/to/maven/bin:$PATH' >> ~/.bash_profile"
        -- utilisez Zsh comme comme shell par défaut:
        "echo 'export PATH=/path/to/maven/bin:$PATH' >> ~/.zshrc"

      (2)Redémarrez votre terminal ou exécutez la commande suivant pour que les modifications prennent effet
      -- utilisez Bash comme shell par défaut:
      "source ~/.bash_profile"
       -- utilisez Zsh comme shell par défaut:
      "source ~/.zshrc"
    Si non, téléchargez sur ce lien "https://maven.apache.org/". Suivez ensuite les étapes ci-dessus

  2.5 Après cela, vous devriez pouvoir exécuter la commande mvn --version et voir les informations sur la version Maven.



3. Telecharge le script, et projet JFreeChart sur ton ordinateur. Ils peuvent être dans des dossiers différents, comme tu veux.
  Le resource du projet JFreeChart disponible à https://github.com/jfree/jfreechart.

  Le lien vers le script :
  
     https://github.com/mildshield14/IFT3913/blob/main/ift3913_tp2/Tp2_3913/3913tp2.py

5. Entrez le réperatoire où se trouve le script dans le terminal
    `"cd path/to/your/script"`
    Example: "cd /Users/yalin/Desktop/Tp2_3913"
6. Modifiez les autorisations des fichiers avec la commande " chmod a+x 3913tp2.py" dans votre terminal
7. Exécutez le script avec la commande : python <nom_du_script>.py
8. Le résultat est imprimé sous la forme suivante
  “1: Rapport entre le code de test et le code principal: folat”
  “2:Taux de défauts: float”
  “3:Nombre moyen de lignes de cas de test: float”
9. Un fichier "tache2_results.html" est généré et les résultats seront enregistrés dans ce fichier aussi.
10. Vous pouvez ouvrir ce fichier dans un navigateur web pour afficher les métriques. C'est une option, parce que le résultat a été imprimé sur la console.

## Dépendances
1. Le script suppose que Maven est installé et est accessible depuis le terminal (i.e., dans le PATH).
2. Le projet JFreeChart doit avoir une structure de répertoire standard avec src/main et src/test.
3. Cet outil suppose que le projet JFreeChart est situé correctement. Vous pouvez modifier les variables src_directory et test_directory dans le script pour pointer vers votre répertoire de projet JFreeChart.


## Métrique 3 : Age

## Description:

    - Utilisation de script python

    - Entrées : Lien repo github clonable, path du dossier de tests, path du dossier source
    
    - Sorties : Données sauvegardées en format csv

## Guide:
 1.1 Sur votre terminal, naviguez vers le dossier analyse_metrique_age.
     Par exemple en clonant notre repo, le absolute path:

     cd /Users/vennilasooben/IFT3913-1/ift3913_tp2/analyse_metrique_age

 1.2 Entrez la ligne suivante:

     python script.py https://github.com/jfree/jfreechart.git src/test/java/org/jfree/chart/title src/main/java/org/jfree/chart/title

   1.2.1 La sortie se trouve dans le dossier jfreechart qu'on a cloné dans le script
   
   1.2.2. Notez qu'une ligne bizarre apparaît dans le fichier csv il s'agit du fichier package-info.java qui n'a pas de fichier test correspondant.


## Métrique 4 : Code Churn

## Description:

    - Utilisation de script bash

    - Entrées : Lien repo github clonable, path du dossier de tests
    
    - Sorties : Données sauvegardées en format csv

## Guide:
 1.1 Sur votre terminal, naviguez vers le dossier analyse_metrique_age.
     Par exemple en clonant notre repo, le absolute path:

     cd /Users/vennilasooben/IFT3913-1/ift3913_tp2/analyse_metrique_codechurn

 1.2 Entrez la ligne suivante:

     ./test.sh https://github.com/jfree/jfreechart.git src/main/java/org/jfree/chart/title

   1.2.1 La sortie se trouve dans le dossier appele temp_dir où on y retrouve jfreechart qu'on a cloné dans le script




----------------------------------------------------------------------------------
