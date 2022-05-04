package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.controller.Controller;

/*
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 */
public class View {
	private Controller contr;
	private double payAmount;
	private final String NEW_ROW = "\n";

	/**
	 * Creates a new instance, that used the specified controller for all calls to other layers.
	 * 
	 * @param contr The controller to use for all the calls to other layers. 
	 */
	public View(Controller contr) {
		this.contr = contr;
	}
	
	/**
	 * Performs a fake sale, by calling all system operations in the controller
	 */
	public void runFakeExecution() {
		startSale();
		enterItems();
		endSale();
	}
	
	private void startSale() {
		System.out.println("A new sale has started" + NEW_ROW);
		contr.makeNewSale();
	}

	private void enterItems() {
		System.out.println("Entering the following items:");
		contr.enterItem(1000, 3);
		contr.enterItem(1001, 2);
		contr.enterItem(1005, 1);
		contr.enterItem(1004, 4);
		contr.enterItem(1004, 3);
	}

	private void endSale() {
		payAmount = 500;
		System.out.println(NEW_ROW + "Printing Receipt:");
		contr.createReceipt(payAmount);	
	}
}
