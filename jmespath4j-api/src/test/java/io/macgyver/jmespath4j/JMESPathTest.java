package io.macgyver.jmespath4j;
import org.junit.Test;

public class JMESPathTest {

	
	@Test(expected=IllegalStateException.class)
	public void testIt() {
		
		JMESPath.getGlobalEngine();
	}
}
