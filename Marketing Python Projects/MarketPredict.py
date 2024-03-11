import sqlite3
from sqlite3 import Error
import numpy as np
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

# Create a database connection
def create_connection(db_file):
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Error as e:
        print(e)
    return conn

# Create table
def create_table(conn, create_table_sql):
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
    except Error as e:
        print(e)

# Insert a new campaign
def insert_campaign(conn, campaign):
    sql = ''' INSERT INTO campaigns(campaign_name, budget, sales)
              VALUES(?,?,?) '''
    cur = conn.cursor()
    cur.execute(sql, campaign)
    conn.commit()
    return cur.lastrowid

# Query the average sales
def select_avg_sales(conn):
    cur = conn.cursor()
    cur.execute("SELECT AVG(sales) FROM campaigns")
    rows = cur.fetchall()
    for row in rows:
        print("Average Sales:", row[0])

# Main function
def main():
    database = "pythonsqlite.db"
    sql_create_sales_table = """ CREATE TABLE IF NOT EXISTS campaigns (
                                    id integer PRIMARY KEY,
                                    campaign_name text NOT NULL,
                                    budget real,
                                    sales real
                                ); """

    # Create a database connection
    conn = create_connection(database)

    # Create table
    if conn is not None:
        create_table(conn, sql_create_sales_table)
        campaigns = [('Campaign A', 1000, 300),
                     ('Campaign B', 1500, 400),
                     ('Campaign C', 2000, 600)]
        with conn:
            for campaign in campaigns:
                insert_campaign(conn, campaign)
            select_avg_sales(conn)

        # Fetch data for model
        cur = conn.cursor()
        cur.execute("SELECT budget, sales FROM campaigns")
        data = cur.fetchall()
        budgets = np.array([x[0] for x in data]).reshape(-1, 1)
        sales = np.array([x[1] for x in data])

        # Build and fit the model
        model = LinearRegression()
        model.fit(budgets, sales)

        # Predict and plot
        plt.scatter(budgets, sales, color='black')
        plt.plot(budgets, model.predict(budgets), color='blue', linewidth=3)
        plt.xlabel('Budget')
        plt.ylabel('Sales')
        plt.title('Budget vs Sales Prediction')
        plt.show()
    else:
        print("Error! cannot create the database connection.")

if __name__ == '__main__':
    main()
