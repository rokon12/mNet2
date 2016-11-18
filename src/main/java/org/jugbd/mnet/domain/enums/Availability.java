package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @since 5/17/16.
 */
public enum Availability {
    PRESENT("Present"), ABSENT("Absent");
    private final String label;

    Availability(String value) {
        this.label = value;
    }

    public String getLabel() {
        return label;
    }
}
