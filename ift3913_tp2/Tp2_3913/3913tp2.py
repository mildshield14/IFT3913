import os

def count_lines(directory, suffix=".java"):
    "Compter le nombre de lignes dans le fichier de suffixe spécifié"
    total = 0
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith(suffix):
                with open(os.path.join(root, file), "r", encoding="utf-8") as f:
                    total += len(f.readlines())
    return total

# 1. Testez le rapport code/code principal
src_directory = "/Users/yalin/Desktop/jfreechart-master/src/main"
test_directory = "/Users/yalin/Desktop/jfreechart-master/src/test"
src_lines = count_lines(src_directory)
test_lines = count_lines(test_directory, "Test.java")  #calculer tous les fichiers qui doivent être testés
ratio = test_lines / src_lines if src_lines != 0 else 0
print(f"1: Rapport entre le code de test et le code principal: {ratio:.2f}")

# 2. Taux de défauts
def run_tests_and_count_failures():
    "Exécutez des tests et comptez les échecs"
    result = os.popen("mvn test").read()
    failures = result.count("FAILED")
    total_tests = result.count("Tests run:")
    return failures, total_tests

failures, total_tests = run_tests_and_count_failures()
defect_rate = failures / total_tests if total_tests != 0 else 0
print(f"2:Taux de défauts: {defect_rate:.2f}")

# 3. Nombre moyen de lignes de cas de test
def average_test_case_length(directory):
    "Calculer la longueur moyenne du cas de test"
    total_lines = 0
    total_files = 0
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith("Test.java"):  
                with open(os.path.join(root, file), "r", encoding="utf-8") as f:
                    total_files += 1
                    total_lines += len(f.readlines())
    return total_lines / total_files if total_files != 0 else 0

avg_length = average_test_case_length(test_directory)
print(f"3:Nombre moyen de lignes de cas de test: {avg_length:.2f}")

 #creer un fichier html et mettre des resultats dans ce fichier
html_document = """
<!DOCTYPE html>
<html>
<head>
    <title>Tache2 Results</title>
</head>
<body>
    <h1>Tache2 Results</h1>
    <p>1: Rapport code de test/code principal: {}</p>
    <p>2: Taux de defauts: {:.2f}</p>
    <p>3: Nombre moyen de lignes de cas de test: {:.2f}</p>
</body>
</html>
""".format(ratio, defect_rate, avg_length)

#Écrire un document HTML dans un fichier
with open("tache2_results.html", "w", encoding="utf-8") as html_file:
    html_file.write(html_document)

print("Les résultats HTML sont écrits dans le fichier tache2_results.html")





