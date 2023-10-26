README.md
# Outil de Mesure Tache2

Cet outil mesure diverses métriques du projet JFreeChart conformément aux exigences de Tache2.

## Prérequis

Avant d'utiliser cet outil, assurez-vous d'avoir les prérequis suivants installés sur votre système :

- Python 3.x
- Apache Maven
- Git
## Utilisation

1. Clonez ce dépôt :

   ```bash
  git clone https://github.com/jfree/jfreechart.git
  Ou vous pouvez directement ouvrir le lien et télécharger les ressources de code directement depuis github

2. Accédez au répertoire du projet :

bash
Copy code
cd Nom du repertoire du project
Exécutez le script de mesure :

3. bash
Copy code
python3 Nom_du_Script.py
Ce script calculera les métriques suivantes :

Rapport entre le code de test et le code principal
Taux de défauts
Nombre moyen de lignes dans les cas de test
Visualisez les résultats :

4. 
Les résultats seront enregistrés dans un fichier HTML nommé tache2_results.html. Ouvrez ce fichier dans un navigateur web pour afficher les métriques.

5. 
Remarques
Cet outil suppose que le projet JFreeChart est situé à /Users/yalin/Desktop/jfreechart-master. Vous pouvez modifier les variables src_directory et test_directory dans le script pour pointer vers votre répertoire de projet JFreeChart.

Assurez-vous qu'Apache Maven est correctement installé et configuré sur votre système avant d'exécuter le script. Vous devriez pouvoir exécuter mvn --version dans votre terminal sans aucun problème.

Si la commande Not found s'affiche après avoir entré mvn --version dans le terminal,
Cela est généralement dû au fait que Maven n'a pas été ajouté à la variable d'environnement PATH de votre système. Les méthodes suivantes peuvent nous aider à résoudre ce problème

5.1 Vérifiez si Maven est installé : confirmez d'abord que vous avez bien installé Maven et que vous pouvez y accéder via le chemin absolu dans le terminal. On peut utiliser "ind / -name mvn 2>/dev/null" pour trouver son chemin absolu.

5.2 Ajoutez ensuite le chemin absolu à la variable PATH. On peut utiliser la commande :
echo 'export PATH=/path/to/maven/bin:$PATH' >> ~/.bash_profile

Notre commande est utilisée comme suit :
echo 'export PATH=/System/Volumes/Data/Users/yalin/Desktop/apache-maven-3.9.5/bin:$PATH' >> ~/.zshrc

5.3 Rechargez ensuite la configuration bash/zshrc
source ~/.bash_profile ou
source ~/.zshrc

Après cela, vous devriez pouvoir exécuter la commande mvn --version et voir les informations sur la version Maven.

6.
Cet outil génère un rapport HTML (tache2_results.html) contenant les résultats de la mesure.


