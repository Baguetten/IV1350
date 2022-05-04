package se.kth.iv1350.practice.startup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MainTest {
	private Main InstanceToTest;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut; 

	@BeforeEach
	void setUp() throws Exception {
		InstanceToTest = new Main();
		
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
	void testUIHasStarted() {
		String[] args = null;
		Main.main(args);
		String printout = printoutBuffer.toString();
		String expectedOutput = "started";
		assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
	}

}
