package ca.jrvs.apps.stockquote.service;

import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dto.Quote;
import ca.jrvs.apps.stockquote.helper.QuoteHttpHelper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteService {

  private final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao dao;
  private QuoteHttpHelper httpHelper;

  public QuoteService(QuoteDao dao, QuoteHttpHelper httpHelper){
    this.dao = dao;
    this.httpHelper = httpHelper;
  }
  /**
   * Fetches latest quote data from endpoint
   * @param ticker
   * @return Latest quote information or empty optional if ticker symbol not found
   */
  public Optional<Quote> fetchQuoteDataFromAPI(String ticker) {
    logger.info("fetching quote data from API");
    Quote quote = httpHelper.fetchQuoteInfo(ticker);
    if(quote.getTicker() == null)
      return Optional.empty();
    dao.save(quote);
    return Optional.of(quote);
  }
}
