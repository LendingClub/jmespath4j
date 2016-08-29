package io.macgyver.jmespath4j;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class JMESPathEngine {

	public abstract JMESPathExpression compile(String expression);
}
