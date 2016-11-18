package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/18/16.
 */
public enum SystemInvolved {
	SKIN_SUBCUTANEOUS_TISSUE_ABDOMINAL_WALL("Skin and Subcutaneous tissue and Abdominal wall"),
	OESOPHAGUS("Oesophagus"),
	stomach("Stomach"),
	SMALL_INTESTINE("Small intestine"),
	LARGE_INTESTINE("Large intestine"),
	HEPATOBILIARY_AND_PANCREAS("Hepatobiliary and Pancreas\n"),
	RETROPERITONEAL("Retroperitoneal"),
	THYROID("Thyroid"),
	BREAST("Breast");

	private String label;

	SystemInvolved(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
