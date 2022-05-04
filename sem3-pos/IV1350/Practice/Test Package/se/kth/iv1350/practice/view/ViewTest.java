package se.kth.iv1350.practice.view;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import se.kth.iv1350.practice.controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewTest {
	private View InstanceToTest;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut; 

	@BeforeEach
	void setUp() throws Exception {
		Controller contr = new Controller();
		InstanceToTest = new View(contr);
		
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut =  new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		InstanceToTest = null;
		
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}

	@Test
	void testrunFakeExecution() {
		InstanceToTest.runFakeExecution();
		String printout = printoutBuffer.toString();
		String expectedOutput = "started";
		assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
	}

}
