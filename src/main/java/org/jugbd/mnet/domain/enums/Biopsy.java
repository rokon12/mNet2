package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/20/16.
 */
public enum Biopsy {
	PREOPERATIVE_FNAC("Preoperative FNAC"),
	PREOPERATIVE_BIOPSY("Preoperative Biopsy"),
	POSTOPERATIVE_BIOPSY("Postoperative Biopsy");

	private final String label;

	Biopsy(String value) {
		this.label = value;
	}

	public String getLabel() {
		return label;
	}
}
