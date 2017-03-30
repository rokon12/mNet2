package org.jugbd.mnet.json;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

/**
 * @author Bazlur Rahman Rokon
 * @since 3/31/17.
 */
public class JsonBinaryType extends AbstractSingleColumnStandardBasicType<Object> implements DynamicParameterizedType {

	public JsonBinaryType() {
		super(
			JsonBinarySqlTypeDescriptor.INSTANCE,
			new JsonTypeDescriptor()
		);
	}

	public String getName() {
		return "jsonb";
	}

	@Override
	public void setParameterValues(Properties parameters) {
		((JsonTypeDescriptor) getJavaTypeDescriptor())
			.setParameterValues(parameters);
	}

}