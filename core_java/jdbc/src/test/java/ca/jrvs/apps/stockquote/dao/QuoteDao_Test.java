package ca.jrvs.apps.stockquote.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.stockquote.dto.Quote;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuoteDao_Test {
  private static Connection connection;
  private static QuoteDao quoteDao;
  private Quote quote;

  @BeforeClass
  public static void setUp() {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "stockquote_test", "postgres", "password");
    try{
      connection = dcm.getConnection();
      connection.setAutoCommit(false);  // Prevent auto-committing changes
      quoteDao = new QuoteDao(connection);
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    if(connection != null)
      connection.close();
  }

  @Before
  public void setUpQuote() {
    quote = new Quote();
    quote.setTicker("MSFT");
    quote.setOpen(424.01);
    quote.setHigh(435.2);
    quote.setLow(423.5);
    quote.setPrice(434.56);
    quote.setVolume(35647805);
    quote.setLatestTradingDay(new Date(System.currentTimeMillis()));
    quote.setPreviousClose(444.06);
    quote.setChange((-9.5));
    quote.setChangePercent("-2.1394%");
    quote.setTimestamp(new Timestamp(System.currentTimeMillis()));
  }

  @After
  public void rollbackTransaction() throws SQLException {
    connection.rollback(); // Roll back the transaction after each test
  }
  @Test
  public void save(){
    Quote testQuote = quoteDao.save(quote);
    assertNotNull(testQuote);
    assertEquals("MSFT", testQuote.getTicker());
    assertEquals(424.01, testQuote.getOpen(), 0.01);
    testQuote.setOpen(444.5);
    Quote testQuote2 = quoteDao.save(testQuote);

    assertEquals(444.5, testQuote2.getOpen(), 0.01);
    assertEquals(435.2, testQuote2.getHigh(), 0.01);
  }

  @Test
  public void findById(){
    quoteDao.save(quote);

    assertTrue(quoteDao.findById(quote.getTicker()).isPresent());
    assertFalse(quoteDao.findById("Non existent symbol").isPresent());
  }

  @Test
  public void findAll(){

    Quote quote2 = new Quote();
    quote2.setTicker("GOOG");
    quote2.setOpen(199.76);
    quote2.setHigh(203.24);
    quote2.setLow(199.47);
    quote2.setPrice(202.63);
    quote2.setVolume(17970000);
    quote2.setLatestTradingDay(new Date(System.currentTimeMillis()));
    quote2.setPreviousClose(197.18);
    quote2.setChange(5.45);
    quote2.setChangePercent("2.7640%");
    quote2.setTimestamp(new Timestamp(System.currentTimeMillis()));

    quoteDao.save(quote);
    quoteDao.save(quote2);

    List<Quote> quoteList = (List<Quote>) quoteDao.findAll();

    assertNotNull(quoteList);
    assertTrue(quoteList.size() > 1);
  }

  @Test
  public void deleteById(){
    quoteDao.save(quote);
    quoteDao.deleteById(quote.getTicker());

    assertFalse(quoteDao.findById(quote.getTicker()).isPresent());
  }

  @Test
  public void deleteAll(){
    Quote quote2 = new Quote();
    quote2.setTicker("GOOG");
    quote2.setOpen(199.76);
    quote2.setHigh(203.24);
    quote2.setLow(199.47);
    quote2.setPrice(202.63);
    quote2.setVolume(17970000);
    quote2.setLatestTradingDay(new Date(System.currentTimeMillis()));
    quote2.setPreviousClose(197.18);
    quote2.setChange(5.45);
    quote2.setChangePercent("2.7640%");
    quote2.setTimestamp(new Timestamp(System.currentTimeMillis()));

    quoteDao.save(quote);
    quoteDao.save(quote2);
    quoteDao.deleteAll();

    List<Quote> quoteList = (List<Quote>) quoteDao.findAll();

    assertTrue(quoteList.isEmpty());
  }
}
