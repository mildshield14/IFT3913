#!/bin/bash

#inspo and source of code: https://maven.apache.org/surefire/maven-surefire-plugin/

# Check if the required arguments are provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <repository_url> <test_directory>"
    exit 1
fi

# Get inputs from command line arguments
repo_url="$1"
test_directory="$2"

# Get the directory of the script
script_dir="$(dirname "$0")"


output_file="$script_dir/test_results.html" # Save the output file 

# Clone the repository
git clone $repo_url
cd "$(basename "$repo_url" .git)"

# Run tests in the specified directory and measure execution time
# inspo for time : https://www.masteringunixshell.net/qa26/bash-how-to-measure-execution-time.html#:~:text=You%20can%20measure%20execution%20time,end%20%2D%20%24start%60%20seconds.
# inspo for file search : https://stackoverflow.com/questions/407184/how-to-check-the-extension-of-a-filename-in-a-bash-script

start_time=$(date +%s)
mvn test -Dtest="**/org/jfree/chart/title/*Test.java" surefire-report:report -Dmaven.test.failure.ignore=true
end_time=$(date +%s)
execution_time=$((end_time - start_time))

# Record detailed results in XML format
mvn surefire-report:report-only

# Save execution time and detailed XML results to the output file
echo "Execution Time: $execution_time seconds" > "$output_file"
echo "Detailed Test Results:" >> "$output_file"
cat target/site/surefire-report.html >> "$output_file"

echo "Test results recorded in $output_file"
