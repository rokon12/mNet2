package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.TreatmentPlanType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by rokonoid on 12/25/14.
 */

@Entity
public class TreatmentPlan extends PersistentObject implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    private TreatmentPlanType treatmentPlanType;

    @Size(max = 500)
    private String otherTreatmentPlanType;

    @Size(max = 500)
    private String typeOfConservativeTreatment;

    @Size(max = 500)
    private String typeOfOperativeTreatment;

    @Size(max = 1000)
    private String comment;

    @JsonIgnore
    @OneToOne(mappedBy = "treatmentPlan")
    private Register register;

    @JsonIgnore
    @OneToOne(mappedBy = "treatmentPlan")
    private OutdoorRegister outdoorRegister;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TreatmentPlanType getTreatmentPlanType() {
        return treatmentPlanType;
    }

    public void setTreatmentPlanType(TreatmentPlanType treatmentPlanType) {
        this.treatmentPlanType = treatmentPlanType;
    }

    public String getOtherTreatmentPlanType() {
        return otherTreatmentPlanType;
    }

    public void setOtherTreatmentPlanType(String otherTreatmentPlanType) {
        this.otherTreatmentPlanType = otherTreatmentPlanType;
    }

    public String getTypeOfConservativeTreatment() {
        return typeOfConservativeTreatment;
    }

    public TreatmentPlan setTypeOfConservativeTreatment(String typeOfConservativeTreatment) {
        this.typeOfConservativeTreatment = typeOfConservativeTreatment;
        return this;
    }

    public String getTypeOfOperativeTreatment() {
        return typeOfOperativeTreatment;
    }

    public void setTypeOfOperativeTreatment(String typeOfOperativeTreatment) {
        this.typeOfOperativeTreatment = typeOfOperativeTreatment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Register getRegister() {
        return register;
    }

    public TreatmentPlan setRegister(Register register) {
        this.register = register;
        return this;
    }

    public OutdoorRegister getOutdoorRegister() {
        return outdoorRegister;
    }

    public TreatmentPlan setOutdoorRegister(OutdoorRegister outdoorRegister) {
        this.outdoorRegister = outdoorRegister;
        return this;
    }
}
