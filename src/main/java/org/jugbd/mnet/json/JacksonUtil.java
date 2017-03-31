package org.jugbd.mnet.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;

/**
 * @author Bazlur Rahman Rokon
 * @since 3/31/17.
 */
public class JacksonUtil {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		OBJECT_MAPPER.registerModule(new JodaModule());
		OBJECT_MAPPER.registerModule(new Hibernate4Module());
		OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	public static <T> T fromString(String string, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(string, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException("The given string value: "
				+ string + " cannot be transformed to Json object", e);
		}
	}

	public static String toString(Object value) {
		try {
			return OBJECT_MAPPER.writer().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("The given Json object value: "
				+ value + " cannot be transformed to a String" , e);
		}
	}

	public static JsonNode toJsonNode(String value) {
		try {
			return OBJECT_MAPPER.readTree(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T clone(T value) {
		return fromString(toString(value), (Class<T>) value.getClass());
	}

}
