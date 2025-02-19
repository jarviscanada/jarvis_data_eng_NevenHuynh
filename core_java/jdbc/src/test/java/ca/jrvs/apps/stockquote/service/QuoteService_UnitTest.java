package ca.jrvs.apps.stockquote.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dto.Quote;
import ca.jrvs.apps.stockquote.helper.QuoteHttpHelper;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuoteService_UnitTest {

  private QuoteService quoteService;
  @Mock
  private QuoteDao mockDao;
  @Mock
  private QuoteHttpHelper mockHelper;

  @Before
  public void setUp(){
    quoteService = new QuoteService(mockDao, mockHelper);
  }

  @Test
  public void fetchQuoteDataFromAPITest1(){
    Quote mockQuote = new Quote();
    mockQuote.setTicker("MSFT");
    mockQuote.setOpen(400);

    when(mockHelper.fetchQuoteInfo("MSFT")).thenReturn(mockQuote);
    when(mockDao.save(mockQuote)).thenReturn(mockQuote);

    Optional<Quote> testQuote = quoteService.fetchQuoteDataFromAPI(mockQuote.getTicker());

    assertTrue(testQuote.isPresent());
    assertEquals(400, testQuote.get().getOpen(), 0.01);
  }

  @Test
  public void fetchQuoteDataFromAPITest2(){
    Quote mockQuote = new Quote();
    mockQuote.setTicker(null);

    when(mockHelper.fetchQuoteInfo("Non existent symbol")).thenReturn(mockQuote);

    Optional<Quote> testQuote = quoteService.fetchQuoteDataFromAPI("Non existent symbol");

    assertFalse(testQuote.isPresent());
    verify(mockDao, never()).save(mockQuote);
  }
}
