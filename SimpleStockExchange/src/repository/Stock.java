package repository;

public class Stock {

	public enum stockType {COMMON, PREFERRED};
	
	private float stockPrice;
	private float dividendYield;
	private float peRatio;
	private int lastDividend;
	private int fixedDividend;
	private int parValue;
	private String stockSymbol;
	private stockType sType;
	
	public Stock() {
		
	}
	
	public Stock(stockType type) {
		
		this.sType = type;
		
	}
	
	public Stock(stockType type, float stockprice, String stocksymbol, int lastdividend) {
		
		this.sType = type;
		this.stockPrice = stockprice;
		this.stockSymbol = stocksymbol;
		this.lastDividend = lastdividend;
		
	}
	
	public Stock(stockType type, float stockprice, String stocksymbol, int fixeddividend, int parvalue) {
		
		this.sType = type;
		this.stockPrice = stockprice;
		this.stockSymbol = stocksymbol;
		this.fixedDividend = fixeddividend;
		this.parValue = parvalue;
		
	}
	
	public float getStockPrice() {
		
		return this.stockPrice;
		
	}
	
	public void setStockPrice(float price) {
		
		this.stockPrice = price;
		
	}
	
	public float getDividendYield() {
		
		return this.calculateDividendYield();
		
	}
	
	public void setDividendYield() {
		
		this.dividendYield = calculateDividendYield();
		
	}
	
	public float getPERatio() {
		
		return this.peRatio;
		
	}
	
	public void setPERatio() {
		
		this.peRatio = calculatePERatio();
		
	}
	
	public int getLastDividend() {
		
		return this.lastDividend;
		
	}
	
	public void setLastDividend(int lastDiv) {
		
		this.lastDividend = lastDiv;
		
	}
	
	public int getFixedDividend() {
		
		return this.fixedDividend;
		
	}
	
	public void setFixedDividend(int fixDiv) {
		
		this.fixedDividend = fixDiv;
		
	}
	
	public void setParValue(int pValue) {
		
		this.parValue = pValue;
		
	}
	
	public int getParValue() {
		
		return this.parValue;
		
	}
	
	public void setStockSymbol(String sSymbol) {
		
		this.stockSymbol = sSymbol;
		
	}
	
	public String getStockSymbol() {
		
		return this.stockSymbol;
		
	}
	
	public float calculateDividendYield() {
		
		float tempDividend = 0f;
		
		switch (sType) {
		
		case COMMON:
			
			tempDividend = this.getLastDividend()/this.getStockPrice();
			
			break;
			
		case PREFERRED:
			
			tempDividend = (this.getFixedDividend()*0.01f*this.getParValue())/this.getStockPrice();
			
			break;
		
		}
		
		return tempDividend;
		
	}
	
	public float calculatePERatio() {
		
		float tempPERatio = 0f;
		
		this.dividendYield = calculateDividendYield();

		if (this.getDividendYield() == 0.0f) {
			
			tempPERatio = 0.0f;
			
		} else {
			
			tempPERatio = this.getStockPrice()/this.getDividendYield();
			
		}
		
		return tempPERatio;
		
	}
	
}
