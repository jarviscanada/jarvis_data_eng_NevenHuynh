# Databricks notebook source
# MAGIC %md
# MAGIC # Silver Layer - Cleaned and Enriched Customer Data
# MAGIC This notebook processes raw Bronze-level customer data into a refined and analysis-ready Silver table. The main goal of this layer is to clean and enrich the dataset to support downstream insights into customer churn behaviour. 

# COMMAND ----------

df_silver = spark.read.table("bronze_customers")

# COMMAND ----------

from pyspark.sql.functions import *

# COMMAND ----------

# Remove null values and invalid values
df_silver = (df_silver
            .filter((col("balance").isNotNull()) & (col("balance") >= 0))
            .filter((col("age").isNotNull()) & (col("age") >= 18))
            .filter((col("estimated_salary").isNotNull()) & (col("estimated_salary") >= 0))
            .filter((col("point_earned").isNotNull()) & (col("point_earned") >= 0))
            .filter((col("satisfaction_score").isNotNull()) & (col("satisfaction_score") >= 1) & (col("satisfaction_score") <= 5))
            .filter((col("customer_id").isNotNull()))
            .filter((col("geography").isNotNull()))
            .filter((col("gender").isNotNull()))
            .filter((col("tenure").isNotNull()) & (col("tenure") >= 0))
            .filter((col("num_of_products").isNotNull()) & (col("num_of_products") >= 0))
            .filter((col("has_cr_card").isNotNull()) & (col("has_cr_card") >= 0))
            .filter((col("is_active_member").isNotNull()) & (col("is_active_member") >= 0))
            .dropDuplicates(["customer_id", "surname"])
            )

# COMMAND ----------

# Create age brackets for customers, dividing them into 6 groups
df_silver = df_silver.withColumn("age_group", when((col("age") >= 18) & (col("age") <= 24), "18-24")
                                 .when((col("age") >= 25) & (col("age") <= 34), "25-34")
                                 .when((col("age") >= 35) & (col("age") <= 44), "35-44")
                                 .when((col("age") >= 45) & (col("age") <= 54), "45-54")
                                 .when((col("age") >= 55) & (col("age") <= 64), "55-64")
                                 .when((col("age") >= 65), "65+")
                                 )

# COMMAND ----------

# Create credit score brackets for customers, dividing them into 5 groups
df_silver = df_silver.withColumn("credit_score_group", when((col("credit_score") >= 760), "excellent")
                                 .when((col("credit_score") >= 725) & (col("credit_score") <= 759), "very good")
                                 .when((col("credit_score") >= 660) & (col("credit_score") <= 724), "good")
                                 .when((col("credit_score") >= 560) & (col("credit_score") <= 659), "fair")
                                 .when((col("credit_score") <= 559), "poor")
                                 )               

# COMMAND ----------

df_silver.select("balance").summary().show()

# COMMAND ----------

# Divide the balance column into 5 groups
quantiles = df_silver.approxQuantile("balance", [0.2, 0.4, 0.6, 0.8], 0.01)
q1, q2, q3, q4 = quantiles
print(quantiles)
df_silver = df_silver.withColumn(
    "balance_group",
    when(col("balance") == q1, "No Balance")
    .when((col("balance") > q1) & (col("balance") <= q2), "Low")
    .when((col("balance") > q2) & (col("balance") <= q3), "Medium")
    .when((col("balance") > q3) & (col("balance") <= q4), "Medium-High")
    .when((col("balance") > q4), "High")
)

# COMMAND ----------

df_silver.select("estimated_salary").summary().show()

# COMMAND ----------

# Divide the estimated salary into 4 groups
quartiles = df_silver.approxQuantile("estimated_salary", [0.25, 0.5, 0.75], 0.01)
q1, q2, q3 = quartiles
print(quartiles)
df_silver = df_silver.withColumn(
    "estimated_salary_group",
    when(col("estimated_salary") <= q1, "Low")
    .when((col("estimated_salary") > q1) & (col("estimated_salary") <= q2), "Medium-Low")
    .when((col("estimated_salary") > q2) & (col("estimated_salary") <= q3), "Medium-High")
    .when((col("estimated_salary") > q3), "High")
)

# COMMAND ----------

df_silver.select("point_earned").summary().show()

# COMMAND ----------

# Divide the point earned column into 4 groups
df_silver = df_silver.withColumn("point_earned_group", when((col("point_earned") <= 250), "0-250")
                                 .when((col("point_earned") >= 251) & (col("point_earned") <= 500), "251-500")
                                 .when((col("point_earned") >= 501) & (col("point_earned") <= 750), "501-750")
                                 .when((col("point_earned") >= 751) & (col("point_earned") <= 1000), "751-1000")
)

# COMMAND ----------

display(df_silver)

# COMMAND ----------


# Save the silver table as a delta table
df_silver.write.format("delta").mode("overwrite").saveAsTable("silver_customers")