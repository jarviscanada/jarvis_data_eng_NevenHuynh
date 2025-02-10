package ca.jrvs.apps.stockquote;

import ca.jrvs.apps.stockquote.controller.StockQuoteController;
import ca.jrvs.apps.stockquote.dao.DatabaseConnectionManager;
import ca.jrvs.apps.stockquote.dao.PositionDao;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.helper.QuoteHttpHelper;
import ca.jrvs.apps.stockquote.service.PositionService;
import ca.jrvs.apps.stockquote.service.QuoteService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;

public class Main {

  public static void main(String[] args){
    Map<String, String> properties = new HashMap<>();
    try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/properties.txt"))){
      String line;
      while((line = br.readLine()) != null){
        String[] tokens = line.split(":");
        properties.put(tokens[0], tokens[1]);
      }
    } catch (FileNotFoundException e){
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    }
    try {
      Class.forName(properties.get("db-class"));
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    }
    OkHttpClient client = new OkHttpClient();
    DatabaseConnectionManager dcm = new DatabaseConnectionManager(properties.get("server"), properties.get("database"), properties.get("username"), properties.get("password"));
    try (Connection connection = dcm.getConnection()){
      QuoteDao qRepo = new QuoteDao(connection);
      PositionDao pRepo = new PositionDao(connection);
      QuoteHttpHelper rcon = new QuoteHttpHelper(properties.get("api-key"), client);
      QuoteService sQuote = new QuoteService(qRepo, rcon);
      PositionService sPos = new PositionService(pRepo, qRepo);
      StockQuoteController con = new StockQuoteController(sQuote, sPos);
      con.initClient();
    } catch (SQLException e){
      e.printStackTrace();
    }
  }
}
