#!/bin/bash

#got idea of code churn by asking chatgpt about age metrics here is answer:
#Code Age: This metric calculates the age of specific code files or modules in the software project. It is typically measured in terms of the time elapsed since the creation or last modification of the code.
#Project Age: Project age refers to the overall duration since the initiation of the software project. It provides an indication of the project's longevity and can be useful for assessing the project's historical context and evolution.
#Code Churn: Code churn measures the frequency of changes made to a particular code file or module over time. High code churn might indicate ongoing development or maintenance activities, whereas low churn could suggest stable or dormant code.
#Code Decay: Code decay measures the extent to which code quality deteriorates over time. It can include metrics related to outdated libraries, coding standards violations, or deprecated technologies.
#Bug Age: Bug age calculates the duration between the identification of a bug or issue and its resolution. Monitoring bug age helps in understanding the efficiency of the bug-fixing process.
#Legacy Code Analysis: Legacy code metrics assess the age and quality of older code within a software system. This is particularly relevant in large, long-lived projects where understanding and maintaining legacy components are essential.

# Check if required arguments are provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <GitHub_Repo_Link> <Folder_Path>"
    exit 1
fi

# Parse arguments
repo_link=$1
folder_path=$2
output_file="../../../../../../../../output_codechurn.txt"
temp_dir=$(mktemp -d)

# Clone the repository to a temporary directory outside of the repo
git clone "$repo_link" temp_dir

# Navigate to the specified folder within the cloned repository
cd "temp_dir/$folder_path" || exit

# Calculate code churn for each file in the folder and save to output file
# inspo -> https://gist.github.com/coreyhaines/829932
git log --pretty=format: --name-only | grep "$folder_path" | sort | uniq -c | sort -nr > "$output_file"

echo "Code churn analysis for the folder '$folder_path' has been saved in: $output_file"


