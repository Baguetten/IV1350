package se.kth.iv1350.pos.integration;

/**
 * A class that represents all the data of an item.
 */
public class ItemDTO {
	private final int itemIdentifier;
	private final String itemName;
	private final double price;
	private final double VATRate;
	private double runningTotal = 0;
	private int quantity = 0;
	
	/**
	 * The constructor that creates the item.
	 * 
	 * @param itemIdentifier The identifier of an item
	 * @param itemName The name of an item
	 * @param price The price of an item
	 * @param VATRate The VAT rate of an item
	 * @param runningTotal The running total of an item
	 * @param quantity The quantity of an item
	 */
	public ItemDTO(int itemIdentifier, String itemName, double price, double VATRate, double runningTotal, int quantity) {
		this.itemIdentifier = itemIdentifier;
		this.itemName = itemName;
		this.price = price;
		this.VATRate = VATRate;
		this.runningTotal = runningTotal;
		this.quantity = quantity;
	}
	
	/**
	 * Gets the identifier
	 * 
	 * @return The identifier
	 */
	public int getItemIdentifier() {
		return itemIdentifier;
	}
	
	/**
	 * Gets the name
	 * 
	 * @return The name
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Gets the price
	 * 
	 * @return The price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Gets the VAT rate
	 * 
	 * @return The VAT rate
	 */
	public double getVATRate() {
		return VATRate;
	}
	
	/**
	 * Gets the running total
	 * 
	 * @return The running total
	 */
	public double getRunningTotal() {
		return runningTotal;
	}
	
	/**
	 * Gets the quantity
	 * 
	 * @return The quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Updates the running total of a specific item in a sale.
	 * 
	 * @param quantityToUpdateRunningTotal The amount that gets added to the specified running total in a sale.
	 */
	public void updateRunningTotal(int quantityToUpdateRunningTotal) {
		runningTotal += (1+VATRate) * price * quantityToUpdateRunningTotal;
	}
	
	/**
	 * Updates the quantity of a specific item in a sale.
	 * 
	 * @param quantityToGetAdded The amount that gets added to the quantity in a sale.
	 */
	public void increaseQuantity(int quantityToGetAdded) {
		quantity += quantityToGetAdded;
	}
}
