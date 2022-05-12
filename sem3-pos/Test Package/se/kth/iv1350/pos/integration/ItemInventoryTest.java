package se.kth.iv1350.pos.integration;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemInventoryTest {
	private ItemInventory itemInventory;
	private ItemDTO itemFromItemInventory;
	private ItemDTO itemTest;
	private int validItemIdentifier;
	
	@BeforeEach
	void setUp() throws Exception {
		itemInventory = new ItemInventory();
		itemTest = new ItemDTO(1001, "Vanilla Ice Cream", 23, 0.25, 0, 0);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		itemInventory = null;
		itemTest = null;
	}

	@Test
	void testItemInformation() {
		validItemIdentifier = 1001;
		itemFromItemInventory = itemInventory.ItemInformation(validItemIdentifier);
		boolean expectedResult = true;
		boolean result = checkIfValidItemIdentifierRetrievesTheExpectedResult(itemFromItemInventory, itemTest);
		assertEquals(expectedResult, result, "The correct item was not returned from the itemInventory.");
	}
	
	private boolean checkIfValidItemIdentifierRetrievesTheExpectedResult(ItemDTO itemFromItemInventory, ItemDTO itemTest) {
		if(itemTest.getItemIdentifier() == itemFromItemInventory.getItemIdentifier() && 
		itemTest.getItemName() == itemFromItemInventory.getItemName() && 
		itemTest.getPrice() == itemFromItemInventory.getPrice() &&
		itemTest.getVATRate() == itemFromItemInventory.getVATRate()) {
			return true;
		}
		else return false;
	}
}
