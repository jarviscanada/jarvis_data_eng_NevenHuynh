package ca.jrvs.apps.stockquote.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.stockquote.dao.DatabaseConnectionManager;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dto.Quote;
import ca.jrvs.apps.stockquote.helper.QuoteHttpHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import okhttp3.OkHttpClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class QuoteService_IntTest {

  private static QuoteService quoteService;
  private static QuoteDao quoteDao;
  private static Connection connection;

  @BeforeClass
  public static void setUp() throws SQLException {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "stock_quote",
        "postgres", "password");
    connection = dcm.getConnection();
    connection.setAutoCommit(false);
    quoteDao = new QuoteDao(connection);
    OkHttpClient client = new OkHttpClient();
    String apiKey = "cc5c24148dmsh6d6d9644eb647ccp183ebbjsn3f9c5aa36327";
    QuoteHttpHelper httpHelper = new QuoteHttpHelper(apiKey, client);
    quoteService = new QuoteService(quoteDao, httpHelper);
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    if(connection != null)
      connection.close();
  }

  @After
  public void rollbackTransaction() throws SQLException {
    connection.rollback();
  }

  @Test
  public void fetchQuoteFromAPITest1(){
    Optional<Quote> quote = quoteService.fetchQuoteDataFromAPI("MSFT");

    assertTrue(quote.isPresent());
    assertTrue(quoteDao.findById("MSFT").isPresent());
    assertFalse(quoteDao.findById("Non existent symbol").isPresent());
  }

  @Test
  public void fetchQuoteFromAPITest2(){
    Optional<Quote> quote = quoteService.fetchQuoteDataFromAPI("Non existent symbol");

    assertFalse(quote.isPresent());
  }
}
