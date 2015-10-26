package main;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import repository.Stock;
import repository.Trade;

public class SimpleStockExchange {

		Trade[] trades;
	
		public static void main(String[] args) {
		
			SimpleStockExchange sse = new SimpleStockExchange();
			
			sse.run();
			
		}
		
		public void run() {
				
		}

		public void setTrades(Trade[] t) {
			
			this.trades = t;
			
		}
		
		public Trade[] getTrades() {
			
			return this.trades;
			
		}
		
		public float calculateVolumeWeightedStockPrice(Trade[] trades, String symbol) {
			
			float tempVolWeightStockPrice;
			
			int qtysum = 0;

			float sumprice = 0f;
			
			for (Trade trade: trades) {
				
				Stock shares = trade.getShares();
					
				if ((shares.getStockSymbol().equals(symbol))&&(getDateDiff(getCurrentTimeStamp(), trade.getTimeStamp(), TimeUnit.MINUTES)<15)) {

						sumprice += (trade.getShareQuantity() * shares.getStockPrice());
					
						qtysum += trade.getShareQuantity();
						
					}
				
			}
			
			tempVolWeightStockPrice = sumprice/qtysum;
			
			return tempVolWeightStockPrice;
			
		}
		
		public float calculateStockIndex(Trade[] trades) {
			
			float tempIndex = 0f;

			float tempprice = 0f;
			
			for (Trade trade: trades) {
				
				Stock shares = trade.getShares();
				
				if (tempprice > 0) {
					
					tempprice *= shares.getStockPrice();
					
				} else {
					
					tempprice = shares.getStockPrice();
					
				}
				
			}

			double pricesum = Math.pow(tempprice, 1.0/trades.length);
			
			tempIndex = (float) pricesum;
			
			return tempIndex;
		}
		
		public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {

			long diffInMillies = date1.getTime() - date2.getTime();
			
		    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		    
		}
		
		public Date getCurrentTimeStamp() {
			
		    return new Date();
		    
		}
		
	}
