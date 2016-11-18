package org.jugbd.mnet.domain.enums;

/**
 * @author Bazlur Rahman Rokon
 * @date 10/3/15.
 */
public enum RegistrationType {
    //OUTDOOR("Outpatient"), INDOOR("Inpatient"),

    EMERGENCY("Emergency Indoor"), GENERAL("General Indoor");

    private String label;

    RegistrationType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
