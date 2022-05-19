package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.InvalidItemIdentifierException;
import se.kth.iv1350.pos.controller.OperationException;
import se.kth.iv1350.pos.util.TotalRevenueFileOutput;

/*
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 */
public class View {
	private Controller contr;
	private double payAmount;
	private ErrorMessagePrinter errorMessagePrinter = new ErrorMessagePrinter();
	private final String NEW_ROW = "\n";

	/**
	 * Creates a new instance, that used the specified controller for all calls to other layers.
	 * 
	 * @param contr The controller to use for all the calls to other layers. 
	 */
	public View(Controller contr) {
		this.contr = contr;
		contr.addTotalRevenueObserver(new TotalRevenueView());
		contr.addTotalRevenueObserver(new TotalRevenueFileOutput());
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
		try {
			System.out.println(contr.enterItem(1002, 3));
			} 
		catch (InvalidItemIdentifierException e) {
			errorMessagePrinter.printErrorMessage(e);
			}
		catch (OperationException e) {
			errorMessagePrinter.printErrorMessage(e);
		}
	}

	private void endSale() {
		payAmount = 500;
		System.out.println(NEW_ROW + "Printing Receipt:");
		contr.concludeSale(payAmount);	
	}
}
