package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @since 5/17/16.
 */
public enum LymphNode {
    NOT_PALPABLE("Not Palpable"),
    PALPABLE("Palpable");

    private final String label;

    LymphNode(String value) {
        this.label = value;
    }

    public String getLabel() {
        return label;
    }
}
