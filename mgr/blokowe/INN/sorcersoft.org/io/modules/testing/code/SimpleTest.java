import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;

/**
 * Some simple tests.
 * To run the test from the console, type: 
 * <code>java org.junit.runner.JUnitCore SimpleTest</code> 
 * To run the test with the test runner used in main(), type: 
 * <code>java SimpleTest</code>
 */
public class SimpleTest  {
	protected int fValue1;
	protected int fValue2;

	@Before public void setUp() {
		fValue1= 2;
		fValue2= 3;
	}
	
	@Test public void divideByZero() {
		int zero= 0;
		int result= 8/zero;
		result++; // avoid warning for not using result
	}
	@Test public void testEquals() {
		assertEquals(12, 12);
		assertEquals(12L, 12L);
		assertEquals(new Long(12), new Long(12));

		assertEquals("Size", 12, 13);
		assertEquals("Capacity", 12.0, 11.99, 0.0);
	}

	/** For compatibility to run a suit with a TestRunner in JUnit 3 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SimpleTest.class);
	}
	
	/** For compatibility to run a suit with a TestRunner in JUnit 3 */
	public static void testIt () {
		junit.textui.TestRunner.run (suite());
	}
	
	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("SimpleTest");
    }
    
   public static boolean wasSuccessful() {
        // use this invocation for programmatic testing
        Result result = org.junit.runner.JUnitCore.runClasses(SimpleTest.class);
        return result.wasSuccessful();
    }
}