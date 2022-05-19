package se.kth.iv1350.pos.model;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pos.integration.ItemDTO;

/*
 * One single sale made by one single customer and payed with one payment. 
 */
public class Sale {
	private double totalPrice = 0;
	private double totalVAT = 0;
	private String listOfEachItem = "";
	private String itemInformation;
	private List<ItemDTO> saleList = new ArrayList<>();
	private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<TotalRevenueObserver>();
	private final static String NEW_ROW = "\n";
	private final static String SWEDISH_CRONES = "kr";
	private final static String CLOSING_CURLY_BRACKET = "}";
	private final static String OPENING_CURLY_BRACKET = "{";	

	/**
	 * Checks if the specified item is already a part of the sale or not. 
	 * 
	 * @param itemIdentifier The identifier of a specific item.   
	 * @return A boolean value that depends on if the specified item is already a part of the sale or not. 
	 */
	public boolean checkIfNotAlreadyInSale(int itemIdentifier) {
		for (ItemDTO itemToCompareWith : saleList) {
			if (itemToCompareWith.getItemIdentifier() == itemIdentifier) return false;
		}
		return true;
	}	

	/**
	 * Updates the sale with the given item and quantity. 
	 * 
	 * @param notAlreadyInSale A boolean value depending on if the item is already a part of the sale or not. 
	 * @param item The item that is getting added/updating the sale. 
	 * @param quantity The quantity of a specified item. 
	 * @return The information of the item entered.
	 */
	public String updateSale(boolean notAlreadyInSale, ItemDTO item, int quantity) {
		if (notAlreadyInSale) saleList.add(item);
		IncreaseQuantityOfItemAndRunningTotal(item, quantity);
		updateTotalPriceAndTotalVAT(item, quantity);
		return displayItem(item, quantity);
	}


	private String displayItem(ItemDTO item, int quantity) {
		return "Product: " + OPENING_CURLY_BRACKET + "itemName: " + item.getItemName() + 
		", Price: " + item.getPrice() + SWEDISH_CRONES + ", Running Total: " + 
		getTemporaryRunningTotal(item, quantity) + SWEDISH_CRONES + CLOSING_CURLY_BRACKET;
	}

	private double getTemporaryRunningTotal(ItemDTO item, int quantity) {
		return (1+item.getVATRate()) * item.getPrice() * quantity;
	}

	private void IncreaseQuantityOfItemAndRunningTotal(ItemDTO item, int quantity) {
		for(ItemDTO itemToGetIncreased : saleList) {
			if (itemToGetIncreased.getItemIdentifier() == item.getItemIdentifier()) {
				itemToGetIncreased.increaseQuantity(quantity);
				itemToGetIncreased.updateRunningTotal(quantity);
			}
		}	
	}
	
	private void updateTotalPriceAndTotalVAT(ItemDTO item, int quantity) {
		totalPrice += item.getPrice() * quantity;
		totalVAT += item.getVATRate() * item.getPrice() * quantity;	
	}

	/**
	 * Adds every item of a sale to a string. 
	 * 
	 * @return A string with a list of each item in the sale containing the itemName, Price & Quantity.
	 */
	public String getListOfEachItem() {
		for (ItemDTO itemToGetAdded : saleList) {
			itemInformation = "Product: " + OPENING_CURLY_BRACKET + "ItemName: " + 
		    itemToGetAdded.getItemName() + ", Price: " + itemToGetAdded.getPrice() + ", Quantity: " + 
			itemToGetAdded.getQuantity() + CLOSING_CURLY_BRACKET + NEW_ROW;
			listOfEachItem = listOfEachItem +  itemInformation;
		}
		return listOfEachItem;
	}

	/**
	 * Gets the total price excluding VAT, total VAT and total price including VAT.
	 * 
	 * @return A string with the total price excluding VAT, total VAT and total price including VAT. 
	 */
	public String getTotalVATAndPrice() {
		return "Total Price: " + totalPrice + SWEDISH_CRONES + " + " + totalVAT + SWEDISH_CRONES + 
		" VAT" + " = " + (totalPrice + totalVAT);
	}

	/**
	 * Gets how much the customer pays and how much the customer gets in change. 
	 * 
	 * @param payAmount How much the customer pays for the sale
	 * @return How much the customer pays and how much the customer gets in change
	 */
	public String getAmountPaidAndChange(double payAmount) {
		return "Paid Amount: " + payAmount + SWEDISH_CRONES + " Change: " + 
	    (payAmount - totalPrice - totalVAT) + SWEDISH_CRONES;
	}
	
	/**
	 * Responsible for calling every observer.
	 */
	public void notifyObservers() {
		for (TotalRevenueObserver obs: totalRevenueObservers) {
			obs.newPaymentRevenue(totalPrice+totalVAT);
		}
	}

	/**
	 * Adds every observer to the observer list in this class. 
	 * 
	 * @param observersToGetAdded The specified observers to get added.
	 */
	public void addAlltotalRevenueObservers(List<TotalRevenueObserver> observersToGetAdded) {
		totalRevenueObservers.addAll(observersToGetAdded);
		
	}
}
