import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

# Generate synthetic data
np.random.seed(42)  # for reproducibility
data = {
    'CustomerID': range(1, 101),  # 100 customers
    'TotalSpent': np.random.gamma(shape=2., scale=1000., size=100),  # Total amount spent
    'Transactions': np.random.poisson(lam=5, size=100),  # Number of transactions
    'DaysSinceLastPurchase': np.random.randint(1, 365, size=100)  # Days since last purchase
}

df = pd.DataFrame(data)

# Display the first few rows of the dataframe
print(df.head())

# Exploratory Data Analysis (EDA) - Visualizing the data
sns.pairplot(df)
plt.show()

# Predictive Modeling

# Define features and target
X = df[['Transactions', 'DaysSinceLastPurchase']]
y = df['TotalSpent']

# Split the data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Initialize and train the linear regression model
model = LinearRegression()
model.fit(X_train, y_train)

# Predictions
y_pred = model.predict(X_test)

# Evaluate the model
mse = mean_squared_error(y_test, y_pred)
print(f"Mean Squared Error: {mse}")
