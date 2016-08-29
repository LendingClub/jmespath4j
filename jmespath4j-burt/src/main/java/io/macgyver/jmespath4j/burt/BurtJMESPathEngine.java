package io.macgyver.jmespath4j.burt;

import com.fasterxml.jackson.databind.JsonNode;

import io.burt.jmespath.Expression;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.jackson.JacksonRuntime;
import io.macgyver.jmespath4j.JMESPathEngine;
import io.macgyver.jmespath4j.JMESPathException;
import io.macgyver.jmespath4j.JMESPathExpression;

public class BurtJMESPathEngine extends JMESPathEngine {

	JmesPath<JsonNode> jmespath = new JacksonRuntime();

	@Override
	public JMESPathExpression compile(String expression) {
		try {
			Expression<JsonNode> x = jmespath.compile(expression);
			return new BurtJMESPathExpression(x);
		} catch (io.burt.jmespath.parser.ParseException e) {
			throw new JMESPathException(e);
		}
	}

}
