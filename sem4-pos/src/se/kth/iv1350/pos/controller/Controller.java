package se.kth.iv1350.pos.controller;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.integration.DatabaseConnectionException;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemIdentifierIsNotInInventoryException;
import se.kth.iv1350.pos.integration.ItemInventory;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.TotalRevenueObserver;
import se.kth.iv1350.pos.util.LogWriter;

/*
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
	private ItemInventory iteminventory;
	private static Sale sale;
	private Receipt receipt;
	private ItemDTO item;
	private String receiptToGetPrinted;
	private LogWriter logWriter = new LogWriter();
	private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<TotalRevenueObserver>();
	
	
	/**
	 * Creates the inventory. This method must be called before entering any items in a sale. 
	 */
	public Controller() {
		this.iteminventory = new ItemInventory();
	}
	
	/**
	 * Starts a new sale. This method must be called before doing anything else during a sale. 
	 */
	public void makeNewSale() {
		sale = new Sale();
		sale.addAlltotalRevenueObservers(totalRevenueObservers);
		
	}
	
	/**
	 * Creates a receipt. 
	 * 
	 * @param payAmount The customer specifies how much he wants to pay for the sale  
	 */
	public void concludeSale(double payAmount) {
		receipt = new Receipt();
		receiptToGetPrinted = receipt.generateReceipt(payAmount);
		Printer.printReceipt(receiptToGetPrinted);
		sale.notifyObservers();
	}

	/**
	 * Enters an item to be purchased, firstly checks if it exists and if it does adds it to the sale. 
	 * If the item already exists in the sale, then it just increases the quantity of the specified item in the sale.  
	 * 
	 * @param itemIdentifier The identifier of a specific item.   
	 * @param quantity The quantity of a specified item. 
	 * @return Either returns that the item entered is invalid or the item information of the item entered if it is valid. 
	 * @throws InvalidItemIdentifierException If the itemIdentifier didn't match any item in the itemInventory.
	 * @throws OperationException If there occurred an error connection with the database.
	 */
	public String enterItem(int itemIdentifier, int quantity) throws InvalidItemIdentifierException, OperationException{
		try {
			boolean notAlreadyInSale = sale.checkIfNotAlreadyInSale(itemIdentifier);
			item = iteminventory.ItemInformation(itemIdentifier);
			return sale.updateSale(notAlreadyInSale, item, quantity);
		}
		catch (ItemIdentifierIsNotInInventoryException e) {
			logWriter.logError(e);
			throw new InvalidItemIdentifierException();
		}
		catch (DatabaseConnectionException e) {
			logWriter.logError(e);
			throw new OperationException();
		}
	}

	/**
	 * Allows other classes to access the sale and its variables. 
	 * 
	 * @return The sale
	 */
	public static Sale getSale() {
		return sale;
	}

	/**
	 * Adds an observer to the observer list in this class.
	 * 
	 * @param observerToGetAdded The specified observer to get added.
	 */
	public void addTotalRevenueObserver(TotalRevenueObserver observerToGetAdded) {
		totalRevenueObservers.add(observerToGetAdded);
	}
	
	
}
