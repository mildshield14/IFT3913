import pandas as pd
from scipy.stats import shapiro

# Read the CSV file into a Pandas DataFrame
df = pd.read_csv('jfreechart-test-stats.csv')

columns_to_test = ['TLOC', ' WMC', ' TASSERT'] 

# Perform Shapiro-Wilk test for each column
for column in columns_to_test:
    data = df[column].dropna()  # Remove NaN values if any
    stat, p = shapiro(data)
    
    # Print the results for each column
    print(f"Shapiro-Wilk test for {column}:")
    print("Test Statistic:", stat)
    print("P-Value:", p)
    
    # Determine the result
    alpha = 0.05  # Significance level
    if p > alpha:
        print("Sample looks ND (fail to reject H0)")
    else:
        print("Sample does not look ND (reject H0)")
    print()
