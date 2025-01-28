package ca.jrvs.apps.stockquote.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.stockquote.dto.Quote;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuoteHttpHelperTest {

  private QuoteHttpHelper quoteHttpHelper;
  @Mock
  private OkHttpClient mockClient;
  @Mock
  private Response mockResponse;
  @Mock
  private ResponseBody mockBody;
  @Mock
  private Call mockCall;

  @Before
  public void setup(){
    quoteHttpHelper = new QuoteHttpHelper("test-api-key", mockClient);
  }

  @Test
  public void fetchQuoteInfo() throws IllegalArgumentException, IOException {

    String mockJson = "{\n" +
      "    \"Global Quote\": {\n"+
      "        \"01. symbol\": \"MFST\",\n"+
      "        \"02. open\": \"424.01\",\n"+
      "        \"03. high\": \"435.2\",\n"+
      "        \"04. low\": \"423.5\",\n"+
      "        \"05. price\": \"434.56\",\n"+
      "        \"06. volume\": \"35647805\",\n"+
      "        \"07. latest trading day\": \"2025-01-26\",\n"+
      "        \"08. previous close\": \"444.06\",\n"+
      "        \"09. change\": \"-9.5\",\n"+
      "        \"10. change percent\": \"-2.1394%\"\n"+
      "    }\n"+
      "}";

    when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
    when(mockCall.execute()).thenReturn(mockResponse);
    when(mockResponse.body()).thenReturn(mockBody);
    when(mockBody.string()).thenReturn(mockJson);

    Quote quote = quoteHttpHelper.fetchQuoteInfo("MFST");

    assertNotNull(quote);
    assertEquals("MFST", quote.getTicker());
    assertEquals(424.01, quote.getOpen(), 0.01);
    assertEquals(435.2, quote.getHigh(), 0.01);

  }
}
