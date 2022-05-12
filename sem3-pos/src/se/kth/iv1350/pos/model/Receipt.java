package se.kth.iv1350.pos.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import se.kth.iv1350.pos.controller.Controller;

/*
 * Represents one receipt, which proves the payment of one sale. 
 */
public class Receipt {
	private String saleTime; 
	private String listOfEachItem;
	private String totalVATAndPrice;
	private String amountPaidAndChange;
	private Sale sale;
	private final static String NEW_ROW = "\n";

	/**
	 * This method consists of multiple string methods to gather all the information for the receipt.
	 * 
	 * @param payAmount How much the customer pays for the sale.
	 * @return 
	 */
	public String generateReceipt(double payAmount) {
		sale = Controller.getSale();
		saleTime = getSaleTime();
		listOfEachItem = sale.getListOfEachItem();
		totalVATAndPrice = sale.getTotalVATAndPrice(); 
		amountPaidAndChange = sale.getAmountPaidAndChange(payAmount);
		return createFinalReceipt();
	}

	private String createFinalReceipt() {
		return saleTime + NEW_ROW + listOfEachItem + 
		NEW_ROW + totalVATAndPrice + NEW_ROW + amountPaidAndChange;
	}

	private String getSaleTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		return "Sale started at: " + dtf.format(now);
	}
}
