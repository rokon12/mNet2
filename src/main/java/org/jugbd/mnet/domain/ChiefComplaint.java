package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @date 11/26/2014.
 */
@Entity
public class ChiefComplaint extends PersistentObject implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(length = 1000)
    private List<String> complaints = new ArrayList<>();

    @Size(max = 1000)
    private String presentIllness;

    @JsonIgnore
    @OneToOne(mappedBy = "chiefComplaint")
    private Register register;

    @Size(max = 1000)
    private String comments;

    @Override
    public Long getId() {
        return id;
    }

    public ChiefComplaint setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public ChiefComplaint setVersion(Long version) {
        this.version = version;
        return this;
    }

    public List<String> getComplaints() {
        return complaints;
    }

    public ChiefComplaint setComplaints(List<String> complaints) {
        this.complaints = complaints;
        return this;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public ChiefComplaint setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
        return this;
    }

    public Register getRegister() {
        return register;
    }

    public ChiefComplaint setRegister(Register register) {
        this.register = register;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public ChiefComplaint setComments(String comments) {
        this.comments = comments;
        return this;
    }
}
