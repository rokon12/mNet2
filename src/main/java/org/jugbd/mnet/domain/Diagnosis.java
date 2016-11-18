package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Raqibul Islam on 7/1/14.
 */
@Entity
public class Diagnosis extends PersistentObject implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Size(max = 1000)
    private String diagnosis;

    @Size(max = 1000)
    private String comment;

    @Size(max = 100)
    private String icd10;

    @JsonIgnore
    @OneToOne(mappedBy = "diagnosis")
    private Register register;

    @Override
    public Long getId() {
        return id;
    }

    public Diagnosis setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Diagnosis setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Diagnosis setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Diagnosis setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getIcd10() {
        return icd10;
    }

    public Diagnosis setIcd10(String icd10) {
        this.icd10 = icd10;
        return this;
    }

    public Register getRegister() {
        return register;
    }

    public Diagnosis setRegister(Register register) {
        this.register = register;
        return this;
    }
}
