package io.macgyver.jmespath4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

public class JMESPath {

	static JMESPathEngine globalEngine;

	static Logger logger = LoggerFactory.getLogger(JMESPath.class);

	public static JMESPathEngine getGlobalEngine() {

		if (globalEngine == null) {
			globalEngine = buildEngine();
		}
		return globalEngine;

	}

	public static void setGlobalEngine(JMESPathEngine engine) {
		globalEngine = engine;
	}

	@SuppressWarnings("unchecked")
	private static Class<? extends JMESPathEngine> getEngineClass() throws ClassNotFoundException {

		List<Class<? extends JMESPathEngine>> list = getProviderClassList();
		if (list.isEmpty()) {
			throw new IllegalStateException("no JMESPathEngine implementations found");
		}
		return list.get(0);
	

	}

	private static JMESPathEngine buildEngine() {
		try {
			getProviderClassList();
			JMESPathEngine engine = (JMESPathEngine) getEngineClass().newInstance();
			return engine;
		} catch (ClassNotFoundException  | IllegalAccessException | InstantiationException e) {
			throw new IllegalStateException("jmespath4j default engine not set", e);
		}
	}

	public static JsonNode eval(String expression, JsonNode n) {
		if (n == null) {
			return NullNode.getInstance();
		}
		return getGlobalEngine().compile(expression).eval(n);
	}

	static java.util.List<Class<? extends JMESPathEngine>> getProviderClassList()  {
		
		
		ArrayList<Class<? extends JMESPathEngine>> list = new ArrayList<>();

		Enumeration<URL> t = null;
		
		try {
			t = Thread.currentThread().getContextClassLoader()
		
				.getResources("jmespath4j-provider.properties");
		}
		catch (IOException e) {
			throw new JMESPathException(e);
		}
		while (t.hasMoreElements()) {

			try {
				URL url = t.nextElement();
				logger.debug("loading {}", url);
				try (InputStream is = url.openStream()) {
					Properties p = new Properties();
					p.load(is);

					String providerEngineClass = p.getProperty("providerEngineClass");
					if (providerEngineClass != null) {
		
						logger.debug("found engine: {}", providerEngineClass);
						Class<? extends JMESPathEngine> clazz = (Class<? extends JMESPathEngine>) Class
								.forName(providerEngineClass);
						list.add(clazz);
						
					}
				}
			} catch (Exception e) {
				logger.warn("problem loading JMESPathEngine config",e);
			}
		}
		return list;
	}
}
