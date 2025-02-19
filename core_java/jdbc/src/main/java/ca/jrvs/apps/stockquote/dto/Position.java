package ca.jrvs.apps.stockquote.dto;

public class Position {

  private String ticker; //id
  private int numOfShares;
  private double valuePaid; //total amount paid for shares

  public String getTicker() { return ticker; }

  public void setTicker(String ticker) { this.ticker = ticker; }

  public int getNumOfShares() { return numOfShares; }

  public void setNumOfShares(int numOfShares) { this.numOfShares = numOfShares; }

  public double getValuePaid() { return valuePaid; }

  public void setValuePaid(double valuePaid) { this.valuePaid = valuePaid; }

  public String toString(){
    StringBuilder string = new StringBuilder();
    string.append("Position\n");
    string.append("    symbol: ").append(ticker).append("\n");
    string.append("    number of shares: ").append(numOfShares).append("\n");
    string.append("    value paid: ").append(valuePaid).append("\n");
    return string.toString();
  }
}

