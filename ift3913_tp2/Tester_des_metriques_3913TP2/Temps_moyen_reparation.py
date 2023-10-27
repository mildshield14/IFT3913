#！/usr/bin.env python
import os
import subprocess
from datetime import datetime, timedelta

# Obtenez l'heure de création du fichier et de la dernière modification à partir du
#  journal git pour calculer le décalage horaire, ajoutez la somme de tous les décalages horaires et divisez-la par le nombre de décalages horaires. 
# Nous pouvons obtenir approximativement une heure pour réparer le fichier.

def calculate_average_fix_time(repoPath):
    os.chdir(repoPath)  # Passer au chemin d'accès à l'entrepôt

    # Extraire les informations de date des journaux git
    log = subprocess.check_output(['git', 'log', '--pretty=format:%cd'], universal_newlines=True)
    dates = [datetime.strptime(date, '%c %z') for date in log.splitlines()]  # Convertir les informations récupérées au format de date

    if len(dates) <= 1:
        return None  # S'il n'y a qu'une seule date ou aucune date, retourner rien

    timeDiffs = []  # Créer une liste pour stocker le décalage horaire

    # Parcourer chaque elemenys dans liste de dates et calculer le décalage horaire entre deux dates
    for i in range(1, len(dates)):
        dateCurrent = dates[i - 1]
        datePrevious = dates[i]
        diff = dateCurrent - datePrevious
        hoursDiff = diff.total_seconds() / 3600  # Convertir les données de temps en heures
        timeDiffs.append(hoursDiff)

    return sum(timeDiffs) / len(timeDiffs)  # Calculer les heures moyennes

average_fix_time = calculate_average_fix_time("jfreechart")  # Votre chemin du répertoire JFreeCHart

print(f"Le temps moyen de réparation est: {average_fix_time:.2f} heur/s")





