package ca.jrvs.apps.stockquote.helper;

import static ca.jrvs.apps.stockquote.helper.JsonParser.toObjectFromJson;
import ca.jrvs.apps.stockquote.dto.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuoteHttpHelper {

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
      Response response = client.newCall(request).execute();
      String responseString = response.body().string();
      return toObjectFromJson(responseString, Quote.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public QuoteHttpHelper(String apiKey, OkHttpClient client){
    this.apiKey = apiKey;
    this.client = client;
  }

}
