import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Read the CSV file into a Pandas DataFrame
df = pd.read_csv('jfreechart-test-stats.csv')

# Ask user to input the column names for y-axis and x-axis
y_axis_column = input("Enter the column name for the y-axis (dependent variable): ").strip()
x_axis_column = input("Enter the column name for the x-axis (independent variable): ").strip()

# Check if the input column names exist with or without a space and handle both cases (basically second and third elements had a space before in title name)
if y_axis_column not in df.columns:
    if f" {y_axis_column}" in df.columns:
        y_axis_column = f" {y_axis_column}"
    else:
        print(f"Column '{y_axis_column}' not found in the DataFrame.")
        exit()

if x_axis_column not in df.columns:
    if f" {x_axis_column}" in df.columns:
        x_axis_column = f" {x_axis_column}"
    else:
        print(f"Column '{x_axis_column}' not found in the DataFrame.")
        exit()

# Extract the specified columns from the DataFrame
y = df[y_axis_column].values.reshape(-1)  # Reshape to 1D array #(help from chatgpt after copy pasting error 'raise TypeError("expected 1D vector for x" TypeError: expected 1D vector for x' i was getting)
x = df[x_axis_column].values.reshape(-1)  # Reshape to 1D array

# Calculate the slope and intercept of the line
slope, intercept = np.polyfit(x, y, 1)

print(intercept)

# Plot the scatter plot
plt.scatter(x, y, color='blue', label='Data Points')

# Plot the line of best fit
plt.plot(x, slope * x + intercept, color='red', linewidth=2, label=f'Line of Best Fit: {y_axis_column} = {slope:.2f} * {x_axis_column} + {intercept:.2f}')

# Add labels and legend
plt.xlabel(x_axis_column)
plt.ylabel(y_axis_column)
plt.legend()

# Show the plot
plt.show()