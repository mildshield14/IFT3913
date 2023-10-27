import os
import sys

# Get command-line arguments
repo_url = sys.argv[1]
source_folder = sys.argv[3]
test_folder = sys.argv[2]

# Clone the repository
os.system(f'git clone {repo_url} temp_repo')
os.chdir('temp_repo')

# Create a CSV file for storing the results
csv_file = open('folder_age_results.csv', 'w')
csv_file.write('Source Code Modification Date,Test Modification Date\n')

# Function to get file age from a folder
def get_file_age(folder, file_name):
    file_path = os.path.join(folder, file_name)
    if os.path.isfile(file_path):
        return str(int(os.path.getmtime(file_path)))
    else:
        return 'Not Found in Tests Folder'

# Loop through all files in source folder and find corresponding test files
for source_file in os.listdir(source_folder):
    test_file = source_file.replace(".java", "Test.java")
    source_age = get_file_age(source_folder, source_file)
    test_age = get_file_age(test_folder, test_file)
    csv_file.write(f'{source_age},{test_age}\n')

# Close the CSV file and navigate back to the original directory
csv_file.close()
os.chdir('..')


print('Folder age results saved to folder_age_results.csv')
