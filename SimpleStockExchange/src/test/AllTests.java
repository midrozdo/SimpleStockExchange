package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import main.SimpleStockExchange;

import org.junit.Before;
import org.junit.Test;

import repository.Stock;
import repository.Trade;

public class AllTests {

	SimpleStockExchange sse = new SimpleStockExchange();
	
	Stock[] s1 = {new Stock(Stock.stockType.COMMON, 220.02f, "AAA", 20),
			new Stock(Stock.stockType.COMMON, 80.00f, "AAA", 18),
			new Stock(Stock.stockType.COMMON, 100.02f, "ABC", 18),
			new Stock(Stock.stockType.COMMON, 30.33f, "CDE", 8),
			new Stock(Stock.stockType.COMMON, 10.48f, "EFG", 0),
			new Stock(Stock.stockType.COMMON, 199.99f, "HIJ", 23),
			new Stock(Stock.stockType.PREFERRED, 188.73f, "KLM", 2, 250)};
	
	Trade[] trades = {new Trade(s1[0], 200, getDate(6), Trade.transaction.BUY),
			new Trade(s1[0], 100, getDate(20), Trade.transaction.SELL),
			new Trade(s1[1], 50, getDate(2), Trade.transaction.BUY),
			new Trade(s1[4], 200, getDate(14), Trade.transaction.SELL),
			new Trade(s1[6], 20, getDate(16), Trade.transaction.BUY)};
	
	private static final double DELTA = 1e-10;
	
	@Before
	public void setUpTest() {
		sse.setTrades(trades);
		System.out.println(DELTA);
	}
	
	@Test
	public void testTradeArray() {
		assertArrayEquals(trades, sse.getTrades());
	}
	
	@Test
	public void testReturnStockPrice() {
		assertEquals(trades[1].getShares().getStockPrice(), 220.02f, DELTA);
	}
	
	@Test
	public void testCalculateCommonStockDividendYield() {
		assertEquals(trades[0].getShares().calculateDividendYield(), 0.090900827f, DELTA);
	}
	
	@Test
	public void testCalculatePreferredStockDividendYield() {
		assertEquals(trades[4].getShares().calculateDividendYield(), 0.02649287342f, DELTA);
	}
	
	@Test
	public void testCalculatePERatio() {
		assertEquals(trades[1].getShares().calculatePERatio(), 2420.44021165f, DELTA);
	}
	
	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		assertEquals(sse.calculateVolumeWeightedStockPrice(trades, "AAA"), 192.016f, DELTA);
	}
	
	@Test
	public void testCalculateStockIndex() {
		assertEquals(sse.calculateStockIndex(trades), 94.807633454f, DELTA);
	}
	
	public String getDate(int minsAgo) {
		
		String date;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, -minsAgo);

		Date newDate = cal.getTime();
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		date = sdfDate.format(newDate);
				
		return date;
		
	}

}
