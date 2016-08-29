package io.macgyver.jmespath4j.burt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

import io.burt.jmespath.Expression;
import io.macgyver.jmespath4j.JMESPathExpression;

public class BurtJMESPathExpression implements JMESPathExpression {

	Expression<JsonNode> expression;
	
	public BurtJMESPathExpression(Expression<JsonNode> expression) {
		this.expression = expression;
	}
	
	@Override
	public JsonNode eval(JsonNode input) {
		if (input==null) {
			return NullNode.getInstance();
		}
		return expression.search(input);
	}

}
