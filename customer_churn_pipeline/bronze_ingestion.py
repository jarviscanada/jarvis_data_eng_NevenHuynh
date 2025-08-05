# Databricks notebook source
# MAGIC %md
# MAGIC #Bronze Layer - Raw Data Ingestion
# MAGIC
# MAGIC This notebook handles the ingestion of raw customer churn data into the Bronze layer of the medallion architecture. The dataset is a CSV file containing customer demographic, financial, and engagement details from a European bank. It defines and applies an explicit schema for data consistency and type safety, performs column renaming to ensure naming consistency, and writes the raw data to a Delta table (`bronze_customers`) for further processing in the Silver layer. 
# MAGIC
# MAGIC

# COMMAND ----------

from pyspark.sql.functions import *
from pyspark.sql.types import *

file_path = "/Volumes/workspace/default/volumes/Customer-Churn-Records.csv"

# Define the schema for the dataset
schema = StructType([
    StructField("RowNumber", IntegerType(), True),
    StructField("CustomerId", IntegerType(), True),
    StructField("Surname", StringType(), True),
    StructField("CreditScore", IntegerType(), True),
    StructField("Geography", StringType(), True),
    StructField("Gender", StringType(), True),
    StructField("Age", IntegerType(), True),
    StructField("Tenure", IntegerType(), True),
    StructField("Balance", FloatType(), True),
    StructField("NumOfProducts", IntegerType(), True),
    StructField("HasCrCard", IntegerType(), True),
    StructField("IsActiveMember", IntegerType(), True),
    StructField("EstimatedSalary", FloatType(), True),
    StructField("Exited", IntegerType(), True),
    StructField("Complain", IntegerType(), True),
    StructField("Satisfaction Score", IntegerType(), True),
    StructField("Card Type", StringType(), True),
    StructField("Point Earned", IntegerType(), True),
])

# Read the CSV file 
df_bronze = (
    spark.read.format("csv")
    .option("header", "true")
    .schema(schema)
    .load(file_path)
    .withColumn("time_ingested", current_timestamp())
)

# Rename the columns in the dataset to ensure consistency and remove spaces to ensure compatibility with Delta Lake
df_bronze = (df_bronze
             .withColumnRenamed("RowNumber", "row_number")
             .withColumnRenamed("CustomerId", "customer_id")
             .withColumnRenamed("Surname", "surname")
             .withColumnRenamed("CreditScore", "credit_score")
             .withColumnRenamed("Geography", "geography")
             .withColumnRenamed("Gender", "gender")
             .withColumnRenamed("Age", "age")
             .withColumnRenamed("Tenure", "tenure")
             .withColumnRenamed("NumOfProducts", "num_of_products")
             .withColumnRenamed("HasCrCard", "has_cr_card")
             .withColumnRenamed("IsActiveMember", "is_active_member")
             .withColumnRenamed("EstimatedSalary", "estimated_salary")
             .withColumnRenamed("Exited", "exited")
             .withColumnRenamed("Complain", "complain")
             .withColumnRenamed("Satisfaction Score", "satisfaction_score")
             .withColumnRenamed("Card Type", "card_type")
             .withColumnRenamed("Point Earned", "point_earned")
             )

# Save the bronze table as a delta table
df_bronze.write.format("delta").mode("overwrite").saveAsTable("bronze_customers")


# COMMAND ----------

# Display the first 10 records of the table
display(df_bronze.limit(10))