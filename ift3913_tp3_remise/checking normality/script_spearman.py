import pandas as pd
from scipy.stats import spearmanr

# Read the CSV file into a Pandas DataFrame
df = pd.read_csv('jfreechart-test-stats.csv')

column1_data = df[' WMC']
column2_data = df[' TASSERT']

# Calculate Spearman rank correlation coefficient and p-value
correlation_coefficient, p_value = spearmanr(column1_data, column2_data)

print(f"Spearman Rank Correlation Coefficient: {correlation_coefficient}")
print(f"P-Value: {p_value}")


