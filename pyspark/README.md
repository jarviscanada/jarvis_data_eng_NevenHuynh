# Introduction
The Retail Data Analytics notebook explores various aspects of retail data for London Gift Shop using Databricks. 
Databricks is used in this project to take advantage of its scalable big data processing capabilities by utilising distributed computing clusters.
The purpose of the notebook is to provide a detailed analytical view of the shop's sales data, growth, and users, helping to uncover patterns
and trends that can inform business decisions.

## Technologies
- Databricks
- Apache Spark
- PySpark
- Spark SQL
- Pandas
- Delta Lake
- Azure

# Databricks Implementation
## Dataset
The [Online Retail II Dataset](https://raw.githubusercontent.com/jarviscanada/jarvis_data_eng_demo/feature/data/python_data_wrangling/data/online_retail_II.csv) consists of thousands of user invoices made at London Gift Shop. The columns for the dataset are as follows:
- Invoice       - String
- StockCode     - String
- Description   - String
- Quantity      - Integer
- InvoiceDate   - Timestamp
- Price         - Float
- Customer ID   - Float
- Country       - String

## Analytics
This notebook performs a range of exploratory data analyses to uncover key business insights for the London Gift Shop. The analyses focus on understanding sales performance, customer behaviour, and growth trends. 

#### Invoice-Level Analysis
The total dollar amount per invoice was calculated to assess purchase sizes, computing descriptive statistics (mean, median, mode) for invoice totals. The distribution of invoice totals was then visualized to identify patterns and skewness.

#### Order Tracking
Monthly trends were analysed by tracking the amount of placed and cancelled orders, visualizing the data to highlight sesasonal patterns. 

#### Sales Performance
Monthly sales were aggregated to monitor revenue trends over time. Monthly growth rates were calculated by comparing one month to the previous, measuring business momentum and fluctuations.

#### Customer Engagement
Tracked monthly active users to gauge customer engagement and retention. Customers were segmented into new vs. returning users to better understand customer loyalty.

#### Customer Value Analysis (RFM)
Conducted RFM (Recency, Frequency, Monetary) analysis to segment customers based on how recently and frequently they purchase, and how much they spend, providing a foundation for identifying high-value customer segments and tailoring marketing strategies.

## Architecture

### Description
The analytics solution is built on top of the **Azure Databricks platform**, which provides a unified environment for big data processing, storage, and analytics. It simplifies the management of Spark clsuters and integrates tightly with Azure services. 

**Apache Spark** is the main data processing engine behind Databricks. It enables scalable, parallel processing of large datasets across a cluster of machines. It supports multiple APIs, and this project uses PySpark (Spark's Python API) for data transformation, analysis, and visualization. 

For storage, the solution relies on **Azure Storage** as the primary data source, providing secure, scalable cloud storage for customer data. Within Databricks, data is accessed through the **Databricks File System (DBFS)**, a distributed file system that allows seamless reading, writing, and management of data stored in Azure. DBFS acts as the working layer between cloud storage and the Spark engine.

The **Hive Metastore**, integrated into Databricks, tracks table schemas and metadata, enabling the registration of tables created with saveAsTable() and making them accessible across notebooks and SQL queries. Most tables are stored using **Delta Lake**, the default storage format in Databricks, which adds ACID transaction support to ensure data reliability and consistency.

The overall data flow begins with data ingestion from DBFS, where raw data is read and prepared for analysis. Using PySpark, the data is cleaned, transformed, and aggregated across the Spark cluster, leveraging distributed compute power to handle large-scale processing efficiently. Once processed, the data is saved as managed tables, in Delta format, in the Hive Metastore, ensuring it is available for further analysis and querying. Finally, analytical insights and visualizations are generated within the Databricks notebook, providing a comprehensive and interactive environment for exploring business performance and customer behaviour. 

### Diagram
![Azure Databricks Diagram](./assets/Retail%20Data%20Analytics%20Architecture.png)

# Future Improvement

- Instead of using simple thresholds for detecting outliers, apply statistical techniques (e.g., Z-score)
- Use Databricks dashboards or connect to Power BI for live visual reports
- Implement data partitioning by month to speed up queries
- Build a simple machine learning model to predict monthly sales or customer churn using the Apache Spark machine learning library (MLlib)

