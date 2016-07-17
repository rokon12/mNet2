package org.jugbd.mnet.domain.enums;

/**
 * Created by rokon on 7/17/16.
 */
public enum Unit {
    UNIT_I("Unit-I"),
    UNIT_II("Unit-II"),
    UNIT_III("Unit-III"),
    UNIT_IV("Unit-IV"),
    UNIT_V("Unit-V"),
    UNIT_VI("Unit-VI");

    private String label;

    Unit(String value) {
        this.label = value;
    }

    public String getLabel() {
        return label;
    }
}
