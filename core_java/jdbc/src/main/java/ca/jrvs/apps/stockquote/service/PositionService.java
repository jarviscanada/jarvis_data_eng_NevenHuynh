package ca.jrvs.apps.stockquote.service;

import ca.jrvs.apps.stockquote.dao.PositionDao;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dto.Position;
import ca.jrvs.apps.stockquote.dto.Quote;

public class PositionService {

  private PositionDao positionDao;
  private QuoteDao quoteDao;

  public PositionService(PositionDao positionDao, QuoteDao quoteDao){
    this.positionDao = positionDao;
    this.quoteDao = quoteDao;
  }
  /**
   * Processes a buy order and updates the database accordingly
   * @param ticker
   * @param numberOfShares
   * @param price
   * @return The position in our database after processing the buy
   */
  public Position buy(String ticker, int numberOfShares, double price) {

    return null;
  }

  /**
   * Sells all shares of the given ticker symbol
   * @param ticker
   */
  public void sell(String ticker) {
    //TO DO
  }
}
