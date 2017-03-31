package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.RequiredNotRequired;
import org.jugbd.mnet.domain.enums.YesNo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Bazlur Rahman Rokon
 * @date 12/26/14.
 */
@Entity
@JsonIgnoreProperties({"register"})
public class OperationalDetail extends PersistentObject implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm a")
    private Date dateTime;

    @Size(max = 512)
    private String anaesthesia;

    @NotEmpty
    @Size(max = 200)
    private String nameOfOperation;

    @Size(max = 1000)
    private String indication;

    @Size(max = 1000)
    private String findings;

    @Size(max = 1000)
    private String incision;

    @Size(max = 1000)
    @Column(name = "operation_procedure") // procedure is mysql reserved keyword
    private String procedure;

    @Size(max = 1000)
    private String perOperativeComplication;

    @Size(max = 1000)
    private String nameOfSurgeon;

    @Size(max = 1000)
    private String nameOfAnaesthetist;
    @Size(max = 1000)

    private String plasty;
    @Size(max = 1000)
    private String recipientSite;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private RequiredNotRequired bloodTransfusion;

    @Column(length = 5)
    @Enumerated(EnumType.STRING)
    private YesNo drain;

    @Size(max = 1000)
    private String comment;

    @ManyToOne
    private Register register;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getAnaesthesia() {
        return anaesthesia;
    }

    public void setAnaesthesia(String anaesthesia) {
        this.anaesthesia = anaesthesia;
    }

    public String getNameOfOperation() {
        return nameOfOperation;
    }

    public void setNameOfOperation(String nameOfOperation) {
        this.nameOfOperation = nameOfOperation;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getIncision() {
        return incision;
    }

    public void setIncision(String incision) {
        this.incision = incision;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getPerOperativeComplication() {
        return perOperativeComplication;
    }

    public void setPerOperativeComplication(String perOperativeComplication) {
        this.perOperativeComplication = perOperativeComplication;
    }

    public String getNameOfSurgeon() {
        return nameOfSurgeon;
    }

    public void setNameOfSurgeon(String nameOfSurgeon) {
        this.nameOfSurgeon = nameOfSurgeon;
    }

    public String getNameOfAnaesthetist() {
        return nameOfAnaesthetist;
    }

    public void setNameOfAnaesthetist(String nameOfAnaesthetist) {
        this.nameOfAnaesthetist = nameOfAnaesthetist;
    }

    public String getPlasty() {
        return plasty;
    }

    public void setPlasty(String plasty) {
        this.plasty = plasty;
    }

    public String getRecipientSite() {
        return recipientSite;
    }

    public void setRecipientSite(String recipientSite) {
        this.recipientSite = recipientSite;
    }

    public RequiredNotRequired getBloodTransfusion() {
        return bloodTransfusion;
    }

    public void setBloodTransfusion(RequiredNotRequired bloodTransfusion) {
        this.bloodTransfusion = bloodTransfusion;
    }

    public YesNo getDrain() {
        return drain;
    }

    public void setDrain(YesNo drain) {
        this.drain = drain;
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

    public void setRegister(Register register) {
        this.register = register;
    }
}
