package ca.jrvs.apps.stockquote.service;

import ca.jrvs.apps.stockquote.dao.PositionDao;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dto.Position;
import ca.jrvs.apps.stockquote.dto.Quote;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionService {

  private final Logger logger = LoggerFactory.getLogger(PositionService.class);

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
    logger.info("buying stock");
    try {
      Optional<Quote> quote = quoteDao.findById(ticker);
      Optional<Position> positionEntity = positionDao.findById(ticker);

      if (quote.isPresent()) {
        if (quote.get().getVolume() < numberOfShares)
          throw new IllegalArgumentException("Number of shares cannot exceed volume");
      } else {
        throw new IllegalArgumentException("Invalid ticker symbol");
      }

      Position position;
      if (positionEntity.isPresent()) {
        position = positionEntity.get();
        position.setNumOfShares(positionEntity.get().getNumOfShares() + numberOfShares);
        position.setValuePaid(positionEntity.get().getValuePaid() + price * numberOfShares);
      } else {
        position = new Position();
        position.setTicker(ticker.toUpperCase());
        position.setNumOfShares(numberOfShares);
        position.setValuePaid(price * numberOfShares);
      }
      positionDao.save(position);
      return position;
    } catch (Exception e){
      logger.error("Error buying stock", e);
      throw new RuntimeException();
    }
  }

  /**
   * Sells all shares of the given ticker symbol
   * @param ticker
   */
  public void sell(String ticker) {
    logger.info("selling stock");
    try{
      Optional<Position> position = positionDao.findById(ticker);
      if(position.isPresent()){
        positionDao.deleteById(ticker);
      }else {
        throw new IllegalArgumentException("You do not own "+ ticker + "\n");
      }
    } catch (Exception e){
      logger.error("Error selling stock", e);
      throw new RuntimeException();
    }
  }

  /**
   * Finds all positions in the database
   * @return A list of positions in the database
   */
  public List<Position> viewPosition(){
    logger.info("viewing positions");
    return (List<Position>) positionDao.findAll();
  }
}
