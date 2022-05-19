package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

/**
 * Responsible for showing the total revenue to the user interface.
 */
public class TotalRevenueView implements TotalRevenueObserver {

	/**
	 * Adds the specified amount to the total revenue printout.
	 * 
	 * @param amount The total price for the revenue.
	 */
	@Override
	public void newPaymentRevenue(double amount) {
		System.out.println("Total revenue: " + amount);
	}
}
