package ca.jrvs.apps.stockquote.controller;

import ca.jrvs.apps.stockquote.dto.Position;
import ca.jrvs.apps.stockquote.dto.Quote;
import ca.jrvs.apps.stockquote.service.PositionService;
import ca.jrvs.apps.stockquote.service.QuoteService;
import java.util.Optional;
import java.util.Scanner;

public class StockQuoteController {

  private QuoteService quoteService;
  private PositionService positionService;

  public StockQuoteController(QuoteService quoteService, PositionService positionService){
    this.quoteService = quoteService;
    this.positionService = positionService;
  }
  public void initClient(){
    Scanner reader = new Scanner(System.in);
    int i;
    System.out.println("Initializing Stock Quote App");
    while(true) {
      System.out.println("Please enter a number whether you would like to");
      System.out.println("1: View your positions");
      System.out.println("2: View a stock");
      System.out.println("3: Buy a stock");
      System.out.println("4: Sell a stock");
      System.out.println("5: Exit");
      i = reader.nextInt();
      reader.nextLine();

      // View Positions
      if(i == 1) {
        System.out.println(positionService.viewPosition());
      }
      // View a stock quote
      else if(i == 2) {
        System.out.println("Enter the ticker symbol of the stock");
        String ticker = reader.nextLine();
        Optional<Quote> quote = quoteService.fetchQuoteDataFromAPI(ticker);
        if(quote.isEmpty())
          System.out.println("Ticker symbol is invalid.\n");
        else
          System.out.println(quote.get());
      }
      // Buy a stock
      else if(i == 3) {
        System.out.println("Enter the ticker symbol of the stock");
        String ticker = reader.nextLine();

        System.out.println("Enter the number of shares you would like to purchase");
        int numberOfShares = reader.nextInt();

        System.out.println("Enter the price of a share");
        double price = reader.nextDouble();

        Optional<Quote> quote = quoteService.fetchQuoteDataFromAPI(ticker);
        if(quote.isEmpty()) {
          System.out.println("Ticker symbol is invalid. \n");
          continue;
        }
        try {
          Position position = positionService.buy(ticker, numberOfShares, price);
          System.out.println(position);
        } catch (RuntimeException e){
          System.out.println(e.getMessage());
        }
      }
      // Sell a stock
      else if(i == 4){
        System.out.println("Enter the ticker symbol of the stock you would like to sell");
        String ticker = reader.nextLine();

        try{
          positionService.sell(ticker);
          System.out.println(ticker + " sold successfully");
        } catch (RuntimeException e){
          System.out.println(e.getMessage());
        }
      }
      // Exit Application
      else if(i == 5){
        System.out.println("Exiting Stock Quote Application.");
        break;
      }
      // If input is not a number between 1-5
      else{
        System.out.println("The number you entered is invalid. Please enter a number between 1-5");
      }
    }
    reader.close();
  }
}
