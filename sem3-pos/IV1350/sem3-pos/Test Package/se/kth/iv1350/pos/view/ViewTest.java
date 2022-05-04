package se.kth.iv1350.pos.view;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.controller.Controller;

class ViewTest {
	private View instanceToTest;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;

	@BeforeEach
	void setUp() throws Exception {
		Controller contr = new Controller();
		instanceToTest = new View(contr);
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysout = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysout);
	}

	@AfterEach
	void tearDown() throws Exception {
		instanceToTest = null;
		printoutBuffer = null;
		System.setOut(originalSysOut); 
	}

	@Test
	void testRunFakeExecution() {
		instanceToTest.runFakeExecution();
		String printout = printoutBuffer.toString();
		assertTrue(printout.contains("started"), "UI did not start correctly.");
	}
}
