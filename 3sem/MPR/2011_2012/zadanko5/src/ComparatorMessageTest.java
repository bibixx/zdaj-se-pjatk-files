import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class ComparatorMessageTest {

	@Test
	public void test() {
		ComparatorMessage c = new ComparatorMessage();
		Message msg1=new Message(10,10, 1);
		Message msg2=new Message(10,10, 2);
		Message msg3=new Message(10,10, 1);
		Assert.assertTrue(c.compare(msg1, msg2)==1);
		Assert.assertTrue(c.compare(msg1, msg3)==-1);
		Assert.assertTrue(c.compare(msg2, msg1)==-1);
	}

}
