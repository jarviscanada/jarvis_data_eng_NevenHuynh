package ca.jrvs.apps.stockquote.helper;

import static ca.jrvs.apps.stockquote.helper.JsonParser.toObjectFromJson;
import ca.jrvs.apps.stockquote.dto.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.sql.Timestamp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteHttpHelper {

  private final Logger logger = LoggerFactory.getLogger(QuoteHttpHelper.class);

  private String apiKey;
  private OkHttpClient client;

  /**
   * Fetch latest quote data from Alpha Vantage endpoint
   * @param symbol
   * @return Quote with latest data
   * @throws IllegalArgumentException - if no data was found for the given symbol
   */
  public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {
    Request request = new Request.Builder()
        .url(
            "https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol=" + symbol
                + "&datatype=json")
        .header("X-RapidAPI-Key", apiKey)
        .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
        .build();
    try {
      Response response = client.newCall(request).execute();          // get HTTP response
      String responseString = response.body().string();               // Convert the response to a Json string
      Quote quote = toObjectFromJson(responseString, Quote.class);    // Convert the Json string to a quote object
      quote.setTimestamp(new Timestamp(System.currentTimeMillis()));  // Set timestamp for the quote
      logger.info(quote.toString());
      return quote;
    } catch (JsonMappingException e) {
      logger.error(e.getMessage());
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  public QuoteHttpHelper(String apiKey, OkHttpClient client){
    this.apiKey = apiKey;
    this.client = client;
  }
}
