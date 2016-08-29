package io.macgyver.jmespath4j;

import com.fasterxml.jackson.databind.JsonNode;

public interface JMESPathExpression {

	public JsonNode eval(JsonNode input);
}
