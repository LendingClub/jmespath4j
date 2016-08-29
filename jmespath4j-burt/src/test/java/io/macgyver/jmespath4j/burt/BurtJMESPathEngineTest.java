package io.macgyver.jmespath4j.burt;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.macgyver.jmespath4j.JMESPath;
import io.macgyver.jmespath4j.JMESPathEngine;
import io.macgyver.jmespath4j.test.JMESPathTestCase;

public class BurtJMESPathEngineTest extends JMESPathTestCase {

	
	@Test
	public void testIt() {
		ObjectNode n = mapper.createObjectNode().put("a", "foo").put("b", "bar");
		
		Assertions.assertThat(new BurtJMESPathEngine().compile("a").eval(n).asText()).isEqualTo("foo");
		Assertions.assertThat(new BurtJMESPathEngine().compile("a").eval(NullNode.getInstance()).isNull()).isTrue();
		Assertions.assertThat(new BurtJMESPathEngine().compile("a").eval(NullNode.getInstance())).isInstanceOf(NullNode.class);
		Assertions.assertThat(new BurtJMESPathEngine().compile("a").eval(MissingNode.getInstance())).isInstanceOf(NullNode.class);
		Assertions.assertThat(new BurtJMESPathEngine().compile("a").eval(MissingNode.getInstance()).isNull()).isTrue();
		Assertions.assertThat(new BurtJMESPathEngine().compile("a").eval(null).isNull()).isTrue();
	}
	
	@Test
	public void testDefault() {
		Assertions.assertThat(JMESPath.getGlobalEngine()).isInstanceOf(BurtJMESPathEngine.class).isSameAs(JMESPath.getGlobalEngine());		
	}
	
	
	


	@Override
	public JMESPathEngine getJMESPathEngine() {
		return new BurtJMESPathEngine();
	}
}
