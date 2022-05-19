package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

/**
 * Responsible for creating logs as an fileoutput server
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
	private PrintWriter logStream;
	
	/**
	 * Creates the log file for the total revenue.
	 */
	public TotalRevenueFileOutput() {
		try {
			logStream = new PrintWriter(new FileWriter("totalRevenue.txt"), true);
		}
		catch (IOException ioe) {
			System.out.println("CANNOT LOG");
			ioe.printStackTrace();
		}
	}

	/**
	 * Adds the specified amount to the total revenue log.
	 * 
	 * @param amount The total price for the revenue.
	 */
	@Override
	public void newPaymentRevenue(double amount) {
		logStream.println("Total revenue: " + amount);
	}
}
