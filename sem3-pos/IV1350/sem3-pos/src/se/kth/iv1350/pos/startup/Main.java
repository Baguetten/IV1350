package se.kth.iv1350.pos.startup;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;

/*
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {

	/**
	 * The main method must be used to start the application.
	 * 
	 * @param args The application does not take any command line parameters. 
	 */
	public static void main(String[] args) {
		Controller contr = new Controller();
		View view = new View(contr);
		view.runFakeExecution();
	}
}
