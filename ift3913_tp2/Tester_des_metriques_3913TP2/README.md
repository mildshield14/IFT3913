README.md
# Outil de Mesure Tache2

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
  Le lien ver le script :https://github.com/mildshield14/IFT3913/blob/main/ift3913_tp2/Tp2_3913/3913tp2.py

4. Entrez le réperatoire où se trouve le script dans le terminal
    "cd path/to/your/script"
    Example:"cd /Users/yalin/Desktop/Tp2_3913"
5. Modifiez les autorisations des fichiers avec la commande " chmod a+x 3913tp2.py" dans votre terminal
6. Exécutez le script avec la commande : python <nom_du_script>.py
7. Le résultat est imprimé sous la forme suivante
  “1: Rapport entre le code de test et le code principal: folat”
  “2:Taux de défauts: float”
  “3:Nombre moyen de lignes de cas de test: float”
8. Un fichier "tache2_results.html" est généré et les résultats seront enregistrés dans ce fichier aussi.
9. Vous pouvez ouvrir ce fichier dans un navigateur web pour afficher les métriques. C'est une option, parce que le résultat a été imprimé sur la console.

## Dépendances
1. Le script suppose que Maven est installé et est accessible depuis le terminal (i.e., dans le PATH).
2. Le projet JFreeChart doit avoir une structure de répertoire standard avec src/main et src/test.
3. Cet outil suppose que le projet JFreeChart est situé à /Users/yalin/Desktop/jfreechart-master. Vous pouvez modifier les variables src_directory et test_directory dans le script pour pointer vers votre répertoire de projet JFreeChart.
4. Le html généré final se trouve dans le repertoire '/Users/yalin/Desktop/Tester_des_metriques_3913TP2'. Vous pouvez changer les variables output_directory et output_file_path dans le script afin de pouvoir retrouver le fichier plus facilement.