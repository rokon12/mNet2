package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/20/16.
 */
public enum BloodSugar {

	RBS("RBS"),
	FBS("FBS"),
	TWO_HABF("2HABF");

	private final String label;

	BloodSugar(String value) {
		this.label = value;
	}

	public String getLabel() {
		return label;
	}
}
