package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @since 5/17/16.
 */
public enum Severity {

    MILD("Mild"),MODERATE("Moderate"), SEVERE("Severe"), ABSENT("Absent");
    private final String label;

    Severity(String value) {
        this.label = value;
    }

    public String getLabel() {
        return label;
    }
}
