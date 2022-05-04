package se.kth.iv1350.pos.controller;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemInventory;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;

/*
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
	private ItemInventory iteminventory;
	private static Sale sale;
	private Receipt receipt;
	private ItemDTO item;
	
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
	}
	
	/**
	 * Creates a receipt. 
	 * 
	 * @param payAmount The customer specifies how much he wants to pay for the sale  
	 */
	public void createReceipt(double payAmount) {
		receipt = new Receipt();
		receipt.generateReceipt(payAmount);		
	}

	/**
	 * Enters an item to be purchased, firstly checks if it exists and if it does adds it to the sale. 
	 * If the item already exists in the sale, then it just increases the quantity of the specified item in the sale.  
	 * 
	 * @param itemIdentifier The identifier of a specific item.   
	 * @param quantity The quantity of a specified item. 
	 */
	public void enterItem(int itemIdentifier, int quantity){
		boolean notAlreadyInSale = sale.checkIfNotAlreadyInSale(itemIdentifier);
		item = iteminventory.ItemInformation(itemIdentifier);
		if (item == null) printInvalidItem();
		else sale.updateSale(notAlreadyInSale, item, quantity);	
			
	}

	private void printInvalidItem() {
		System.out.println("The item entered does not exist. Error 404");
	}

	/**
	 * Allows other classes to access the sale and its variables. 
	 * 
	 * @return The sale
	 */
	public static Sale getSale() {
		return sale;
	}
}
