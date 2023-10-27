README.md
# IFT3913 TP par Vennila Sooben (20235256) et Yalin Mo (20199655)

# TP2

Date: 27 octobre 2023

Auteurs : 
Vennila Sooben (matricule: 20235356, courriel: vennila.sooben@umontreal.ca, github: https://github.com/mildshield14)

Yalin Mo (matricule: 20199655 , courriel: yalin.mo@umontreal.ca )

Lien du repositoire : `https://github.com/mildshield14/IFT3913.git`

Lien vers notre réponse dans le rapport dans le répositoire : `ift3913_tp2/rapport.pdf`

# Outil de Mesure Tache2

Ce projet utilise un script Python pour analyser les tests du projet JFreeChart, en particulier en testant le rapport code de test/code principal, le taux de défauts des tests et la longueur moyenne des cas de test.

## Prérequis

Avant d'utiliser cet outil, assurez-vous d'avoir les prérequis suivants installés sur votre système :
- Python 3.x.x
- Apache Maven
- Un dépôt git cloné localement
- cloc (Count Lines Of Code) qui est un outil de ligne de commande utilisé pour compter le nombre de lignes de code, le nombre de lignes de commentaires et le nombre de lignes vides dans le code source.

## Description
1. Le script “3913tp2.py” réalise les analyses suivantes :

Rapport entre le code de test et le code principal : Il détermine combien de lignes sont dédiées aux tests par rapport au code source principal.
Taux de défauts : Il exécute les tests en utilisant Maven et compte combien d'entre eux échouent.
Nombre moyen de lignes de cas de test : Il calcule la longueur moyenne de chaque fichier de test.

2. Le script “Temps_moyen_reparation.py” réalise un analyse suivante :

Ce script Python est conçu pour calculer le temps moyen nécessaire pour apporter des modifications à un projet git en analysant les logs git.
Elle ne peut calculer qu'approximativement le décalage horaire entre le moment où un fichier a été créé et le moment où il a été modifié pour la dernière fois.


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

3. Assurez-vous d'avoir cloné votre dépôt git localement
  3.1 Si vous souhaitez cloner cette ressource dans un répertoire spécifié, executez la commande "cd path/to/your/project".
  Sinon, la ressource sera clonée dans le répertoire dans lequel vous vous trouvez actuellement

  3.2 Utilisez la command "git clone https://github.com/jfree/jfreechart.git" pour cloner cette ressource localement
   Le resource du projet JFreeChart disponible à https://github.com/jfree/jfreechart.


4. Telecharge les des scripts sur ton ordinateur. Le script ”Temps_moyen_reparation.py“ doit être placé dans le répertoire cloné car
   nous devons utiliser git pour rechercher la date du chaque fichier, afin que le programme puisse mieux être exécuté.
   Le lien ver le script :https://github.com/mildshield14/IFT3913/tree/main/ift3913_tp2/Tester_des_metriques_3913TP2

5. Entrez le réperatoire où se trouve le script dans le terminal
    "cd path/to/your/script"
    Example:"/Users/yalin/Desktop/Tester_des_metriques_3913TP2"

6. Modifiez les autorisations des fichiers avec la commande " chmod a+x 3913tp2.py" dans votre terminal

7. Exécutez le script avec la commande : python <nom_du_script>.py

8. Le résultat est imprimé sous la forme suivante
  “1: Rapport entre le code de test et le code principal: folat”
  “2:Taux de défauts: float”
  “3:Nombre moyen de lignes de cas de test: float”

9. Un fichier "tache2_results.html" est généré et les résultats seront enregistrés dans ce fichier aussi.

10. Vous pouvez ouvrir ce fichier dans un navigateur web pour afficher les métriques. C'est une option, parce que le résultat a été   imprimé sur la console.


11. Pour exécuter le script "Temps_moyen_reparation.py", vouz devez vous assurer que votre répertoire de travail actuel est le       répertoire racine de jfreechart.

Example:"(base) yalin@YalindeMacBook-Air jfreechart % "
Sinon, executez la command "cd /path/to/directory/" pour y aller

  11.1 exécuter la commande "python git.py" pour exécuter le script
12. Le résultat est imprimé sous la forme suivante
    "Le temps moyen de réparation est: float heur/s"
  
13. Assurez-vous que l'outil Cloc est disponible sur votre terminal
  13.1 Vous pouvez vérifier la disponibilité de l'outil cloc en exécutant le code suivant dans le terminal :
    “cloc --version”
    Si cloc est installé, cette command affichera son numéro de version. S'il n'est pas installé, vous pouvez recevoir un message d'erreur tel que « command not found» ou un message similaire.
    -- Si c'est installé, executez la command "cloc /path/to/your/source/code"
      Example:"cloc /Users/yalin/Desktop/jfreechart/src"
      La langue, le nombre de fichiers, LOC, LOCet NLOC dans le projet seront tous affichés sur le terminal.
    -- Si non, utilisez la command suivant pour l'installer
      (1)"sudo apt-get install cloc" Linux
      (2)"brew install cloc" macOS
      (3)"choco install cloc" Windows
      Effectuez ensuite les opérations ci-dessus pour obtenir les résultats.



## Dépendances
1. Le script suppose que Maven est installé et est accessible depuis le terminal (i.e., dans le PATH).
2. Le projet JFreeChart doit avoir une structure de répertoire standard avec src/main et src/test.
3. Cet outil suppose que le projet JFreeChart est situé à /Users/yalin/Desktop/jfreechart. Vous pouvez modifier les variables src_directory et test_directory dans le script pour pointer vers votre répertoire de projet JFreeChart.
4. Le html généré final se trouve dans le repertoire '/Users/yalin/Desktop/Tester_des_metriques_3913TP2'. Vous pouvez changer les variables output_directory et output_file_path dans le script afin de pouvoir retrouver le fichier plus facilement.