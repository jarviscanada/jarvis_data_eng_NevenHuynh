package ca.jrvs.apps.stockquote.dao;

import ca.jrvs.apps.stockquote.dto.Quote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteDao implements CrudDao<Quote, String>{

  private final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

  private final Connection connection;

  private static final String INSERT = "INSERT INTO quote (symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String GET_ONE = "SELECT symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote WHERE symbol = ?";

  private static final String GET_ALL = "SELECT symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp FROM quote";

  private static final String UPDATE = "UPDATE quote SET open = ?, high = ?, low = ?, price = ?, volume = ?, latest_trading_day = ?, previous_close = ?, change = ?, change_percent = ?, timestamp = ? WHERE symbol = ?";

  private static final String DELETE = "DELETE FROM quote WHERE symbol = ?";

  private static final String DELETE_ALL = "DELETE FROM quote";

  public QuoteDao(Connection connection){
    this.connection = connection;
  }

  /**
   * Saves a given entity. Will either create or update the entity in the database.
   *
   * @param entity - must not be null
   * @return The saved entity. Will never be null
   * @throws IllegalArgumentException - if id is null
   */
  @Override
  public Quote save(Quote entity) throws IllegalArgumentException {
    if (entity == null) {
      logger.warn("Attempted to save a null Quote entity");
      throw new IllegalArgumentException("Quote entity cannot be null");
    }
    return findById(entity.getTicker()).isPresent() ? update(entity) : create(entity);
  }

  /**
   * Helper function for save(Quote entity) to insert a given entity into the database
   *
   * @param entity - must not be null
   * @return The created entity
   */
  public Quote create(Quote entity) {
    logger.info("creating quote");
    try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
      statement.setString(1, entity.getTicker());
      statement.setDouble(2, entity.getOpen());
      statement.setDouble(3, entity.getHigh());
      statement.setDouble(4, entity.getLow());
      statement.setDouble(5, entity.getPrice());
      statement.setInt(6, entity.getVolume());
      statement.setDate(7, entity.getLatestTradingDay());
      statement.setDouble(8, entity.getPreviousClose());
      statement.setDouble(9, entity.getChange());
      statement.setString(10, entity.getChangePercent());
      statement.setTimestamp(11, entity.getTimestamp());
      statement.execute();
      return entity;
    } catch (SQLException e){
      logger.error("Error creating quote. ", e);
      throw new RuntimeException();
    }
  }

  /**
   * Helper function for save(Quote entity) to update a given entity in the database
   *
   * @param entity - must not be null
   * @return The updated entity
   */
  public Quote update(Quote entity){
    logger.info("updating quote");
    try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
      statement.setDouble(1, entity.getOpen());
      statement.setDouble(2, entity.getHigh());
      statement.setDouble(3, entity.getLow());
      statement.setDouble(4, entity.getPrice());
      statement.setInt(5, entity.getVolume());
      statement.setDate(6, entity.getLatestTradingDay());
      statement.setDouble(7, entity.getPreviousClose());
      statement.setDouble(8, entity.getChange());
      statement.setString(9, entity.getChangePercent());
      statement.setTimestamp(10, entity.getTimestamp());
      statement.setString(11, entity.getTicker());
      statement.execute();
      return entity;
    } catch (SQLException e){
      logger.error("Error updating quote.", e);
      throw new RuntimeException();
    }
  }
  /**
   * Retrieves an entity by its id
   *
   * @param id - must not be null
   * @return Entity with the given id or empty optional if none found
   * @throws IllegalArgumentException - if id is null or blank
   */
  @Override
  public Optional<Quote> findById(String id) throws IllegalArgumentException {
    id = id.toUpperCase();
    Quote quote = null;   //Initialize quote object as null to return an empty quote if id is invalid
    logger.info("finding quote by id");
    try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
      statement.setString(1, id);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()){
        quote = new Quote();
        quote.setTicker(resultSet.getString("symbol"));
        quote.setOpen(resultSet.getDouble("open"));
        quote.setHigh(resultSet.getDouble("high"));
        quote.setLow(resultSet.getDouble("low"));
        quote.setPrice(resultSet.getDouble("price"));
        quote.setVolume(resultSet.getInt("volume"));
        quote.setLatestTradingDay(resultSet.getDate("latest_trading_day"));
        quote.setPreviousClose(resultSet.getDouble("previous_close"));
        quote.setChange(resultSet.getDouble("change"));
        quote.setChangePercent(resultSet.getString("change_percent"));
        quote.setTimestamp(resultSet.getTimestamp("timestamp"));
      }
    } catch (SQLException e){
      logger.error("Error finding quote by id.", e);
      throw new RuntimeException(e);
    }
    return Optional.ofNullable(quote); //returns Optional.empty() if quote is null, returns a quote object otherwise
  }

  /**
   * Retrieves all entities
   *
   * @return All entities
   */
  @Override
  public Iterable<Quote> findAll() {
    List<Quote> quoteList = new ArrayList<>();
    logger.info("finding all quotes");
    try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()){
        Quote quote = new Quote();
        quote.setTicker(resultSet.getString("symbol"));
        quote.setOpen(resultSet.getDouble("open"));
        quote.setHigh(resultSet.getDouble("high"));
        quote.setLow(resultSet.getDouble("low"));
        quote.setPrice(resultSet.getDouble("price"));
        quote.setVolume(resultSet.getInt("volume"));
        quote.setLatestTradingDay(resultSet.getDate("latest_trading_day"));
        quote.setPreviousClose(resultSet.getDouble("previous_close"));
        quote.setChange(resultSet.getDouble("change"));
        quote.setChangePercent(resultSet.getString("change_percent"));
        quote.setTimestamp(resultSet.getTimestamp("timestamp"));
        quoteList.add(quote);
      }
    } catch (SQLException e){
      logger.error("Error finding all quotes.", e);
      throw new RuntimeException(e);
    }
    return quoteList;
  }

  /**
   * Deletes the entity with the given id. If the entity is not found, it is silently ignored
   *
   * @param id - must not be null
   * @throws IllegalArgumentException - if id is null or blank
   */
  @Override
  public void deleteById(String id) throws IllegalArgumentException {
    id = id.toUpperCase();
    logger.info("deleting quote by id");
    try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
      statement.setString(1, id);
      statement.execute();
    } catch (SQLException e){
      logger.error("Error deleting quote by id", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Deletes all entities managed by the repository
   */
  @Override
  public void deleteAll() {
    logger.info("deleting all quotes");
    try(PreparedStatement statement = this.connection.prepareStatement(DELETE_ALL);){
      statement.execute();
    } catch (SQLException e){
      logger.error("Error deleting all quotes", e);
      throw new RuntimeException(e);
    }
  }
}
