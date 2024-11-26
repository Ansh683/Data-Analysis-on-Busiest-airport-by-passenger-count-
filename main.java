import matplotlib.pyplot as plt
import pandas as pd

# Load the dataset
file_path = 'C:\\Users\\akshat.v\\Downloads\\List of busiest airports by passenger traffic 2018.csv'
df = pd.read_csv(file_path)

# Step 1: Inspect the data
print("Initial DataFrame:")
print(df.head())  # Display the first few rows of the DataFrame
print("\nColumns in the DataFrame:")
print(df.columns)  # Display the column names
print("\nDataFrame Info:")
print(df.info())  # Display information about the DataFrame, including data types

# Step 2: Check for missing values
print("\nMissing Values in Each Column:")
print(df.isnull().sum())  # Count missing values in each column

# Step 3: Handle missing values
# Option 1: Drop rows with missing values
df_cleaned = df.dropna()  # Remove rows with any missing values
# Option 2: Fill missing values (if applicable)
# df.fillna(value={'ColumnName': 'DefaultValue'}, inplace=True)  # Example of filling missing values

# Step 4: Check for duplicates
print("\nNumber of Duplicate Rows:")
print(df_cleaned.duplicated().sum())  # Count duplicate rows

# Remove duplicates
df_cleaned = df_cleaned.drop_duplicates()  # Remove duplicate rows

# Step 5: Data type conversion
# Example: Ensure 'Passenger Count' is an integer
# Check the column names to find the right one
print("\nData Types Before Conversion:")
print(df_cleaned.dtypes)  # Display data types before conversion

# Convert 'Passenger Count' to integer if it's not already
# Replace 'Passenger Count' with the actual column name if different
if 'Passenger Count' in df_cleaned.columns:
    df_cleaned['Passenger Count'] = df_cleaned['Passenger Count'].astype(int)

print("\nData Types After Conversion:")
print(df_cleaned.dtypes)  # Display data types after conversion

# Step 6: Filter data
# Example: Filter for airports with more than 50 million passengers
filtered_df = df_cleaned[df_cleaned['Passenger_Count'] > 50000000]  # Adjust the column name as necessary

# Step 7: Final output
print("\nFiltered DataFrame (Airports with more than 50 million passengers):")
print(filtered_df.head())  # Show the first few rows of the filtered DataFrame
print("\nTotal Airports in Filtered DataFrame:")
print(len(filtered_df))  # Show the number of airports in the filtered DataFrame

# Optionally, save the cleaned and filtered DataFrame to a new CSV file
filtered_df.to_csv('C:\\Users\\akshat.v\\Downloads\\Filtered_Airports_2018.csv', index=False)
print("Initial DataFrame:")
print(df.head())  # Display the first few rows of the DataFrame
print("\nColumns in the DataFrame:")
print(df.columns)  # Display the column names

# Step 2: Rename columns for better readability
# Assuming the original columns might have spaces or are not user-friendly
df.rename(columns={
    'Airport Name': 'Airport_Name',
    'City': 'City_Name',
    'Country': 'Country_Name',
    'Passenger Count': 'Passenger_Count'
}, inplace=True)

print("\nColumns after renaming:")
print(df.columns)  # Display the new column names

# Step 3: Change data types
# Convert 'Passenger_Count' to integer if it's not already
if 'Passenger_Count' in df.columns:
    df['Passenger_Count'] = df['Passenger_Count'].astype(int)

# Check data types after conversion
print("\nData Types After Conversion:")
print(df.dtypes)  # Display data types after conversion

# Step 4: Create a new column for passenger density (assuming area data is available)
# For demonstration, let's assume we have a fixed area for each airport (in square kilometers)
# You would typically have this data in another column or a separate dataset
area_in_sq_km = 1000  # Example fixed area for all airports
df['Passenger_Density'] = df['Passenger_Count'] / area_in_sq_km  # Passengers per square kilometer

print("\nDataFrame after adding Passenger Density:")
print(df[['Airport_Name', 'Passenger_Count', 'Passenger_Density']].head())  # Show relevant columns

# Step 5: Group data by country and aggregate passenger counts
country_passenger_count = df.groupby('Country_Name')['Passenger_Count'].sum().reset_index()
country_passenger_count.rename(columns={'Passenger_Count': 'Total_Passengers'}, inplace=True)

print("\nTotal Passengers by Country:")
print(country_passenger_count.head())  # Show aggregated passenger counts by country

# Step 6: Sort the aggregated data
sorted_country_passenger_count = country_passenger_count.sort_values(by='Total_Passengers', ascending=False)

print("\nSorted Total Passengers by Country:")
print(sorted_country_passenger_count.head())  # Show sorted data

# Step 7: Save the transformed DataFrame to a new CSV file
# df.to_csv('C:\\Users\\akshat.v\\Downloads\\Transformed_Airports_2018.csv', index=False)
# country_passenger_count.to_csv('C:\\Users\\akshat.v\\Downloads\\Country_Passenger_Count_2018.csv', index=False)

print("\nData transformation complete.")
# Sample country codes dataset (you would typically load this from a CSV file)
country_codes_data = {
    'Country_Code': ['US', 'CN', 'IN', 'GB', 'FR'],
    'Country_Name': ['United States', 'China', 'India', 'United Kingdom', 'France']
}
country_codes_df = pd.DataFrame(country_codes_data)

# Step 1: Inspect the airports dataset
print("Airports DataFrame:")
print(df.head())  # Display the first few rows
print("\nColumns in Airports DataFrame:")
print(df.columns)  # Display the column names

# Step 2: Rename columns for merging (if necessary)
# Assuming the airports dataset has a 'Country' column that matches 'Country_Name' in the country codes dataset
df.rename(columns={'Country': 'Country_Name'}, inplace=True)

# Step 3: Merge the two DataFrames
# Perform a left join to combine the airports data with country codes
merged_df = pd.merge(df, country_codes_df, on='Country_Name', how='left')

# Step 4: Inspect the merged DataFrame
print("\nMerged DataFrame:")
print(merged_df.head())  # Display the first few rows of the merged DataFrame

# Step 5: Check for any missing values in the merged DataFrame
print("\nMissing Values in Merged DataFrame:")
print(merged_df.isnull().sum())  # Count missing values in each column

# Step 6: Filter the merged DataFrame for specific conditions
# For example, filter for airports located in the United States
us_airports = merged_df[merged_df['Country_Name'] == 'United States']

print("\nUS Airports:")
print(us_airports[['Airport_Name', 'Location', 'Passenger_Count']].head())  # Show relevant columns

# Optional: Save the merged DataFrame to a new CSV file
# merged_df.to_csv('C:\\Users\\akshat.v\\Downloads\\Merged_Airports_Country_Codes.csv', index=False)

print("\nMerging and joining complete.")


# Step 1: Inspect the data
print("Initial DataFrame:")
print(df.head())  # Display the first few rows of the DataFrame
print("\nColumns in the DataFrame:")
print(df.columns)  # Display the column names

# Step 2: Check the data types
print("\nData Types:")
print(df.dtypes)  # Display data types of the DataFrame

# Step 3: Clean the data if necessary
# Check for missing values
print("\nMissing Values in Each Column:")
print(df.isnull().sum())  # Count missing values in each column

# Optionally drop rows with missing values (if applicable)
df_cleaned = df.dropna()  # Remove rows with any missing values

# Step 4: Sort the DataFrame by Passenger Count in descending order
sorted_by_passenger_count = df_cleaned.sort_values(by='Passenger_Count', ascending=False)

print("\nSorted DataFrame by Passenger Count (Descending):")
print(sorted_by_passenger_count[['Airport_Name', 'Passenger_Count']].head(10))  # Show top 10 airports

# Step 5: Sort the DataFrame by Airport Name in ascending order
sorted_by_airport_name = df_cleaned.sort_values(by='Airport_Name')

print("\nSorted DataFrame by Airport Name (Ascending):")
print(sorted_by_airport_name[['Airport_Name', 'Passenger_Count']].head(10))  # Show top 10 airports

# Step 6: Sort the DataFrame by City and then by Passenger Count
sorted_by_city_and_passenger_count = df_cleaned.sort_values(by=['Location', 'Passenger_Count'], ascending=[True, False])

print("\nSorted DataFrame by City (Ascending) and Passenger Count (Descending):")
print(sorted_by_city_and_passenger_count[['Location', 'Airport_Name', 'Passenger_Count']].head(10))  # Show top 10 airports

# Step 7: Reset index after sorting (optional)
sorted_by_passenger_count.reset_index(drop=True, inplace=True)

# Step 8: Save the sorted DataFrame to a new CSV file
# sorted_by_passenger_count.to_csv('C:\\Users\\akshat.v\\Downloads\\Sorted_Airports_2018.csv', index=False)

print("\nSorting complete.")
# Bar Plot: Top 10 Airports by Passenger Count
print("\nGenerating a bar chart for the top 10 airports by passenger count...")

# Sort and get the top 10 airports by passenger count
top_10_airports = df_cleaned.sort_values(by='Passenger_Count', ascending=False).head(10)

# Create a bar plot
plt.figure(figsize=(12, 6))
plt.bar(top_10_airports['Airport_Name'], top_10_airports['Passenger_Count'], color='skyblue')
plt.xticks(rotation=45, ha='right')
plt.title('Top 10 Airports by Passenger Count (2018)')
plt.xlabel('Airport Name')
plt.ylabel('Passenger Count (Millions)')
plt.tight_layout()
plt.show()

# Pie Chart: Passenger Distribution Across Top 5 Countries
print("\nGenerating a pie chart for passenger distribution across the top 5 countries...")

# Group by country and sum passenger counts
country_passenger_count = df_cleaned.groupby('Country_Name')['Passenger_Count'].sum().reset_index()
country_passenger_count.rename(columns={'Passenger_Count': 'Total_Passengers'}, inplace=True)

# Sort and get the top 5 countries
top_5_countries = country_passenger_count.sort_values(by='Total_Passengers', ascending=False).head(5)

# Create a pie chart
plt.figure(figsize=(8, 8))
plt.pie(
    top_5_countries['Total_Passengers'],
    labels=top_5_countries['Country_Name'],
    autopct='%1.1f%%',
    startangle=140,
    colors=['gold', 'lightcoral', 'skyblue', 'lightgreen', 'orange']
)
plt.title('Passenger Distribution Across Top 5 Countries (2018)')
plt.axis('equal')  # Equal aspect ratio ensures the pie is drawn as a circle.
plt.show()

# (Optional) Save the plots to a file
# plt.savefig('C:\\Users\\akshat.v\\Downloads\\Top_10_Airports_Bar_Chart.png')
# plt.savefig('C:\\Users\\akshat.v\\Downloads\\Top_5_Countries_Pie_Chart.png')

print("\nVisualizations complete.")
# Line Chart: Passenger Count Trend for Airports in the United States
print("\nGenerating a line chart for passenger counts of airports in the United States...")

# Filter data for the United States
us_airports = df_cleaned[df_cleaned['Country_Name'] == 'United States']

# Sort airports by passenger count for better visualization
us_airports_sorted = us_airports.sort_values(by='Passenger_Count', ascending=False)

# Create a line chart
plt.figure(figsize=(12, 6))
plt.plot(
    us_airports_sorted['Airport_Name'],
    us_airports_sorted['Passenger_Count'],
    marker='o', linestyle='-', color='blue'
)
plt.xticks(rotation=45, ha='right')
plt.title('Passenger Counts for Airports in the United States (2018)')
plt.xlabel('Airport Name')
plt.ylabel('Passenger Count (Millions)')
plt.tight_layout()
plt.show()

print("\nLine chart generated successfully.")
