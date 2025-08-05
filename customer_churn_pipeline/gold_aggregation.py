# Databricks notebook source
# MAGIC %md
# MAGIC # Gold Layer: Aggregations
# MAGIC The Gold Layer consists of aggregated tables to support business intelligence use cases and decision-making. Data in this layer is derived from the cleaned and transformed Silver layer, and is structured to provide insights.
# MAGIC This notebook focuses on 
# MAGIC - Customer Churn trends segmented by key dimensions such as `age_group`, `gender`, and `geography`.
# MAGIC - Aggregated metrics such a churn percentages, total customer counts, and group-specific statistics.
# MAGIC
# MAGIC

# COMMAND ----------

df_gold = spark.read.table("silver_customers")

# COMMAND ----------

from pyspark.sql.functions import *

# COMMAND ----------

display(df_gold.limit(10))

# COMMAND ----------

# MAGIC %md
# MAGIC ## Margin of Error
# MAGIC To ensure that these aggregated metrics are statistically reliable, we employ the concept of **Margin of Error** (MOE) and determine a minimum group size `n`.
# MAGIC
# MAGIC The Margin of Error quantifies the precision of our calculated metrics for each customer segment. For instance, an average churn rate of 20% with a 2% MOE at a 95% confidence level means we are 95% confident the true churn rate falls between 18% and 22%. 
# MAGIC
# MAGIC Calculating `n` gives us the minimum number of customers that must be present within an aggregated segment for its reported metrics. We derive `n` using a statistical formula that considers:
# MAGIC
# MAGIC 1. Our desired Confidence level 95%
# MAGIC 2. Overall churn rate observed in the dataset
# MAGIC
# MAGIC Margin of error = Z * √(p(1-p)/n)
# MAGIC <br>
# MAGIC Z = Z-score for selected confidence level (1.96 for 95% confidence)
# MAGIC <br>
# MAGIC p = sample proportion (churn rate)
# MAGIC <br>
# MAGIC n = sample size
# MAGIC

# COMMAND ----------

import math 

churn_df = df_gold.agg(avg("exited"))
churn = churn_df.collect()[0][0]
print(f"churn rate: {churn}")
Z = 1.96
margin_of_error = 0.05
n = (Z**2) * churn * (1-churn) / (margin_of_error**2)
n = math.ceil(n)
print(f"minimum sample size: {n}")

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Account Balance

# COMMAND ----------

df_gold_balance = df_gold.groupBy("balance_group").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_balance = df_gold_balance.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_balance.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Credit Score

# COMMAND ----------

df_gold_credit = df_gold.groupBy("credit_score_group").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_credit = df_gold_credit.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_credit.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Salary

# COMMAND ----------

df_gold_salary = df_gold.groupBy("estimated_salary_group").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_salary = df_gold_salary.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_salary.orderBy(desc("churn_rate")))


# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Satisfaction Score

# COMMAND ----------

df_gold_satisfaction = df_gold.groupBy("satisfaction_score").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_satisfaction = df_gold_satisfaction.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_satisfaction.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Points Earned

# COMMAND ----------

df_gold_points = df_gold.groupBy("point_earned_group").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_points = df_gold_points.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_points.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Tenure

# COMMAND ----------

df_gold_tenure = df_gold.groupBy("tenure").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_tenure = df_gold_tenure.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_tenure.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Card Type

# COMMAND ----------

df_gold_card_type = df_gold.groupBy("card_type").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_card_type = df_gold_card_type.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_card_type.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Credit Card Ownership

# COMMAND ----------

df_gold_credit_card = df_gold.groupBy("has_cr_card").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_credit_card = df_gold_credit_card.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_credit_card.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Geography

# COMMAND ----------

df_gold_geo = df_gold.groupBy("geography").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_geo = df_gold_geo.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_geo)

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Age 

# COMMAND ----------

df_gold_age = df_gold.groupBy("age_group").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_age = df_gold_age.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_age.orderBy(desc("churn_rate")))


# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Number of Products

# COMMAND ----------

df_gold_number_of_products = df_gold.groupBy("num_of_products").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_number_of_products = df_gold_number_of_products.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_number_of_products.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Account Activity

# COMMAND ----------

df_gold_active = df_gold.groupBy("is_active_member").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_active = df_gold_active.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_active.orderBy(desc("churn_rate")))


# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Customer Complaint

# COMMAND ----------

df_gold_complain = df_gold.groupBy("complain").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_complain = df_gold_complain.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_complain.orderBy(desc("churn_rate")))


# COMMAND ----------

# MAGIC %md
# MAGIC ### Calculating Churn Rates by Gender

# COMMAND ----------

df_gold_gender = df_gold.groupBy("gender").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_gender = df_gold_gender.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
display(df_gold_gender.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Columns of significance for churn rate
# MAGIC - geography
# MAGIC - age groups
# MAGIC - number of products
# MAGIC - is active
# MAGIC - gender
# MAGIC
# MAGIC

# COMMAND ----------

# MAGIC %md
# MAGIC ## Dashboard Tables

# COMMAND ----------

# MAGIC %md
# MAGIC ### Churn Rates by Age and Gender

# COMMAND ----------

df_gold_age_gender = df_gold.groupBy("age_group", "gender").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_age_gender = df_gold_age_gender.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
#df_gold_age_gender = df_gold_age_gender.filter((col("exited") + col("not_exited")) >= (n))
df_gold_age_gender = df_gold_age_gender.withColumn("age_gender", concat(col("age_group"), lit(" "), col("gender")))
display(df_gold_age_gender.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Churn Rates by Age and Products

# COMMAND ----------

df_gold_age_products = df_gold.groupBy("age_group", "num_of_products").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_age_products = df_gold_age_products.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
df_gold_age_products = df_gold_age_products.filter((col("exited") + col("not_exited")) >= (n))
df_gold_age_products = df_gold_age_products.withColumn("age_products", concat(col("age_group"), lit(" "), col("num_of_products")))
display(df_gold_age_products.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Churn Rates by Age and Activity

# COMMAND ----------

df_gold_age_active = df_gold.groupBy("age_group", "is_active_member").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_age_active = df_gold_age_active.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
df_gold_age_active = df_gold_age_active.filter((col("exited") + col("not_exited")) >= (n))
df_gold_age_active = df_gold_age_active.withColumn("age_active", concat(col("age_group"), lit(" "), col("is_active_member")))
display(df_gold_age_active.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Churn Rates By Geography and Activity

# COMMAND ----------

df_gold_geo_active = df_gold.groupBy("geography", "is_active_member").agg(count(when(col("exited")==1, 1)).alias("exited"), count(when(col("exited")==0, 1)).alias("not_exited"))
df_gold_geo_active = df_gold_geo_active.withColumn("churn_rate", round(col("exited")/(col("exited")+col("not_exited")),2))
df_gold_geo_active = df_gold_geo_active.filter((col("exited") + col("not_exited")) >= (n))
df_gold_geo_active = df_gold_geo_active.withColumn("geo_active", concat(col("geography"), lit(" "), col("is_active_member")))
display(df_gold_geo_active.orderBy(desc("churn_rate")))

# COMMAND ----------

# MAGIC %md
# MAGIC ### Save the Tables

# COMMAND ----------

df_gold_age_gender.write.format("delta").mode("overwrite").saveAsTable("gold_age_gender")
df_gold_age_products.write.format("delta").mode("overwrite").saveAsTable("gold_age_products")
df_gold_age_active.write.format("delta").mode("overwrite").saveAsTable("gold_age_active")
df_gold_geo_active.write.format("delta").mode("overwrite").saveAsTable("gold_geo_active")
df_gold_age.write.format("delta").mode("overwrite").saveAsTable("gold_age")
df_gold_geo.write.format("delta").mode("overwrite").saveAsTable("gold_geo")