package io.macgyver.jmespath4j.test;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.macgyver.jmespath4j.JMESPathEngine;

public abstract class JMESPathTestCase {

	protected ObjectMapper mapper = new ObjectMapper();
	
	public JMESPathTestCase() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract JMESPathEngine getJMESPathEngine();

	@Test(expected=io.macgyver.jmespath4j.JMESPathException.class)
	public void testInvalidExpression() {
		getJMESPathEngine().compile("peo[");
	}
	@Test
	public void testIt2() throws IOException, JsonProcessingException{
		String x = "{\n" + 
				"  \"people\": [\n" + 
				"    {\"first\": \"James\", \"last\": \"d\"},\n" + 
				"    {\"first\": \"Jacob\", \"last\": \"e\"},\n" + 
				"    {\"first\": \"Jayden\", \"last\": \"f\"},\n" + 
				"    {\"missing\": \"different\"}\n" + 
				"  ],\n" + 
				"  \"foo\": {\"bar\": \"baz\"}\n" + 
				"}";
		JsonNode out = getJMESPathEngine().compile("people[*].first").eval(mapper.readTree(x));
		
		Assertions.assertThat(out.get(0).asText()).isEqualTo("James");
		Assertions.assertThat(out.get(1).asText()).isEqualTo("Jacob");
		Assertions.assertThat(out.get(2).asText()).isEqualTo("Jayden");
	}
}
