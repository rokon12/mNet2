package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @date 12/27/14.
 */

public enum PT {
	CONTROL("Control"),
	PATIENT("Patient"),
	INR("INR");

	private String label;

	PT(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
