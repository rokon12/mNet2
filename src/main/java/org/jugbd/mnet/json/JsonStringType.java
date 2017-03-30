package org.jugbd.mnet.json;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

/**
 * @author Bazlur Rahman Rokon
 * @since 3/31/17.
 */
public class JsonStringType
	extends AbstractSingleColumnStandardBasicType<Object>
	implements DynamicParameterizedType {

	public JsonStringType() {
		super(JsonStringSqlTypeDescriptor.INSTANCE, new JsonTypeDescriptor());
	}

	public String getName() {
		return "json";
	}

	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}

	@Override
	public void setParameterValues(Properties parameters) {
		((JsonTypeDescriptor) getJavaTypeDescriptor())
			.setParameterValues(parameters);
	}
}
