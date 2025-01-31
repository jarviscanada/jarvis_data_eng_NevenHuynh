package ca.jrvs.apps.stockquote.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.sql.Date;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "open",
    "high",
    "low",
    "price",
    "volume",
    "latest trading day",
    "previous close",
    "change",
    "change percent"
})

public class Quote {

  @JsonProperty("01. symbol")
  private String ticker; //id
  @JsonProperty("02. open")
  private double open;
  @JsonProperty("03. high")
  private double high;
  @JsonProperty("04. low")
  private double low;
  @JsonProperty("05. price")
  private double price;
  @JsonProperty("06. volume")
  private int volume;
  @JsonProperty("07. latest trading day")
  private Date latestTradingDay;
  @JsonProperty("08. previous close")
  private double previousClose;
  @JsonProperty("09. change")
  private double change;
  @JsonProperty("10. change percent")
  private String changePercent;

  private Timestamp timestamp; //time when the info was pulled

  public String getTicker() { return ticker; }

  public void setTicker(String ticker) { this.ticker = ticker; }

  public double getOpen() { return open; }

  public void setOpen(double open) { this.open = open; }

  public double getHigh() { return high; }

  public void setHigh(double high) { this.high = high; }

  public double getLow() { return low; }

  public void setLow(double low) { this.low = low; }

  public double getPrice() { return price; }

  public void setPrice(double price) { this.price = price; }

  public int getVolume() { return volume; }

  public void setVolume(int volume){ this.volume = volume; }

  public Date getLatestTradingDay() { return latestTradingDay; }

  public void setLatestTradingDay(Date latestTradingDay) { this.latestTradingDay = latestTradingDay; }

  public double getPreviousClose() { return previousClose; }

  public void setPreviousClose(double previousClose) { this.previousClose = previousClose; }

  public double getChange() { return change; }

  public void setChange(double change) { this.change = change; }

  public String getChangePercent() { return changePercent; }

  public void setChangePercent(String changePercent) { this.changePercent = changePercent; }

  public Timestamp getTimestamp() { return timestamp; }

  public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

  public String toString(){
    StringBuilder string = new StringBuilder();
    string.append("{\n");
    string.append("    \"Global Quote\": {\n");
    string.append("        \"01. symbol\": \"").append(ticker).append("\",\n");
    string.append("        \"02. open\": \"").append(open).append("\",\n");
    string.append("        \"03. high\": \"").append(high).append("\",\n");
    string.append("        \"04. low\": \"").append(low).append("\",\n");
    string.append("        \"05. price\": \"").append(price).append("\",\n");
    string.append("        \"06. volume\": \"").append(volume).append("\",\n");
    string.append("        \"07. latest trading day\": \"").append(latestTradingDay).append("\",\n");
    string.append("        \"08. previous close\": \"").append(previousClose).append("\",\n");
    string.append("        \"09. change\": \"").append(change).append("\",\n");
    string.append("        \"10. change percent\": \"").append(changePercent).append("\"\n");
    string.append("        \"11. Timestamp\": \"").append(timestamp).append("\"\n");
    string.append("    }\n");
    string.append("}");
    return string.toString();
  }
}
