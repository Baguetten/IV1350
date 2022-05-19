package se.kth.iv1350.pos.model;

/**
 * The interface used for the revenue observers.
 */
public interface TotalRevenueObserver {
	
	void newPaymentRevenue(double payAmount);
}
