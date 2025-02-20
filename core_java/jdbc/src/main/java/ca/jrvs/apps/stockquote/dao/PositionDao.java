package ca.jrvs.apps.stockquote.dao;

import ca.jrvs.apps.stockquote.dto.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionDao implements CrudDao<Position, String>{

    private final Logger logger = LoggerFactory.getLogger(PositionDao.class);

    private Connection connection;

    private final String INSERT = "INSERT INTO position (symbol, number_of_shares, value_paid) VALUES (?, ?, ?)";

    private final String GET_ONE = "SELECT symbol, number_of_shares, value_paid FROM position WHERE symbol = ?";

    private final String GET_ALL = "SELECT symbol, number_of_shares, value_paid FROM position";

    private final String UPDATE = "UPDATE position SET number_of_shares = ?, value_paid = ? WHERE symbol = ?";

    private final String DELETE = "DELETE FROM position WHERE symbol = ?";

    private final String DELETE_ALL = "DELETE FROM position";

    public PositionDao(Connection connection){
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
  public Position save(Position entity) throws IllegalArgumentException {
    if(entity == null) {
      logger.warn("Attempted to save a null Position entity");
      throw new IllegalArgumentException("Position entity cannot be null");
    }
    return findById(entity.getTicker()).isPresent()? update(entity) : create(entity);
  }

  /**
   * Helper function for save(Position entity) to insert a given entity into the database
   *
   * @param entity - must not be null
   * @return The created entity
   */
  public Position create(Position entity){
    logger.info("creating position");
    try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
      statement.setString(1, entity.getTicker());
      statement.setInt(2, entity.getNumOfShares());
      statement.setDouble(3, entity.getValuePaid());
      statement.execute();
      return entity;
    } catch (SQLException e){
      logger.error("Error creating position", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Helper function for save(Position entity) to update a given entity in the database
   *
   * @param entity - must not be null
   * @return The created entity
   */
  public Position update(Position entity){
    logger.info("updating position");
    try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
      statement.setInt(1, entity.getNumOfShares());
      statement.setDouble(2, entity.getValuePaid());
      statement.setString(3, entity.getTicker());
      statement.execute();
      return entity;
    } catch (SQLException e){
      logger.error("Error updating position", e);
      throw new RuntimeException(e);
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
  public Optional<Position> findById(String id) throws IllegalArgumentException {
    id = id.toUpperCase();
    logger.info("finding position by id");
    Position position = null;   //Initialize position object as null to return an empty position if id is invalid
    try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
      statement.setString(1, id);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()){
        position = new Position();
        position.setTicker(resultSet.getString("symbol"));
        position.setNumOfShares(resultSet.getInt("number_of_shares"));
        position.setValuePaid(resultSet.getDouble("value_paid"));
      }
    } catch (SQLException e){
      logger.error("error finding position by id", e);
      throw new RuntimeException(e);
    }
    return Optional.ofNullable(position); //returns Optional.empty() if position is null, returns a position object otherwise
  }

  /**
   * Retrieves all entities
   *
   * @return All entities
   */
  @Override
  public Iterable<Position> findAll() {
    logger.info("finding all positions");
    List<Position> positionList = new ArrayList<>();
    try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);){
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()){
        Position position = new Position();
        position.setTicker(resultSet.getString("symbol"));
        position.setNumOfShares(resultSet.getInt("number_of_shares"));
        position.setValuePaid(resultSet.getDouble("value_paid"));
        positionList.add(position);
      }
    } catch (SQLException e){
      logger.error("Error finding all positions", e);
      throw new RuntimeException(e);
    }
    return positionList;
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
    logger.info("deleting position by id");
    try(PreparedStatement statement = this.connection.prepareStatement(DELETE);) {
      statement.setString(1, id);
      statement.execute();
    } catch (SQLException e){
      logger.error("error deleting position by id", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Deletes all entities managed by the repository
   */
  @Override
  public void deleteAll() {
    logger.info("deleting all positions");
    try(PreparedStatement statement = this.connection.prepareStatement(DELETE_ALL);){
      statement.execute();
    } catch (SQLException e){
      logger.error("error deleting all positions", e);
      throw new RuntimeException(e);
    }
  }
}
