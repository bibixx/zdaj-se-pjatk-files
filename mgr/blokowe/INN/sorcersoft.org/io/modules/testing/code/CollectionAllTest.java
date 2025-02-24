import junit.framework.JUnit4TestAdapter;
import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;
import java.util.*;
    
/**
 * A sample test case, testing <code>java.util.ArrayList</code>.
 */
public class CollectionAllTest {
    
    private Collection collection;

    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code        
    }

    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    }

    @Before
    public void setUp() {
        collection = new ArrayList();
    }

    @After
    public void tearDown() {
        collection.clear();
    }

    @Test
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
    }

    @Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(1, collection.size());
    }
    
       /** For compatibility to run a suit with a TestRunner in JUnit 3 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CollectionAllTest.class);
	}
	
	/** For compatibility to run a suit with a TestRunner in JUnit 3 */
	public static void testIt () {
		junit.textui.TestRunner.run (suite());
	}
	
	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("CollectionAllTest");
    }
    
   public static boolean wasSuccessful() {
        // use this invocation for programmatic testing
        Result result = org.junit.runner.JUnitCore.runClasses(CollectionAllTest.class);
        return result.wasSuccessful();
    }
}