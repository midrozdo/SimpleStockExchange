package repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trade {

	public enum transaction {BUY, SELL};
	
	private Date timeStamp;
	private int shareQuantity;
	private float tradedPrice;
	private Stock shares;
	private transaction t;
	
	public Trade() {
		
	}
	
	public Trade(Stock shares, int qty, String d, transaction t) {
		
		this.shares = shares;
		
		this.shareQuantity = qty;
		
		DateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		
		try {
			
			Date newdate = format.parse(d);
			
			this.timeStamp = newdate;
			
		} catch (ParseException e) {

			System.exit(0);
			
		}
		
		this.t = t;
		
		setTradedPrice();
		
	}
	
	public void setTransactionType(transaction t) {
		
		this.t = t;
		
	}
	
	public transaction getTransactionType() {
		
		return this.t;
		
	}
	
	public void setTimeStamp(Date tStamp) {
		
		this.timeStamp = tStamp;
		
	}
	
	public Date getTimeStamp() {
		
		return this.timeStamp;
		
	}
	
	public void setShareQuantity(int qty) {
		
		this.shareQuantity = qty;
		
	}
	
	public int getShareQuantity() {
		
		return this.shareQuantity;
		
	}
	
	public void setTradedPrice() {

		this.tradedPrice = this.shareQuantity * this.shares.getStockPrice();
		
	}
	
	public float getTradedPrice() {
		
		return this.tradedPrice;
		
	}
	
	public void setShares(Stock shrs) {
		
		this.shares = shrs;
		
	}
	
	public Stock getShares() {
		
		return this.shares;
		
	}
	
}
