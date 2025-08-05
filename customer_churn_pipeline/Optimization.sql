-- Databricks notebook source
OPTIMIZE gold_age_gender;
OPTIMIZE gold_age_products;
OPTIMIZE gold_age_active;
OPTIMIZE gold_geo_active;
OPTIMIZE gold_age;
OPTIMIZE gold_geo;

-- COMMAND ----------


VACUUM gold_age_gender;
VACUUM gold_age_products;
VACUUM gold_age_active;
VACUUM gold_geo_active;
VACUUM gold_age;
VACUUM gold_geo;