package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.Availability;
import org.jugbd.mnet.domain.enums.LymphNode;
import org.jugbd.mnet.domain.enums.Severity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * @author Bazlur Rahman Rokon
 * @date 11/26/2014.
 */
@Entity
public class Examination extends PersistentObject implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    //General Examination
    private Severity anaemia;
    private Severity jaundice;
    private LymphNode accessibleLymphNode;
    private Severity dehydration;
    private Availability oedema; // translate to Present/Absent

    private Integer pulse; //Pulse per minute

    //@NotNull(message = "Systolic pressure is required")
    @Digits(integer = 3, fraction = 0)
    private Integer systolic;  //Blood Pressure

    //@NotNull(message = "Diastolic pressure is required")
    @Digits(integer = 3, fraction = 0)
    private Integer diastolic;
    private Integer respiratoryRate;//Respiratory rate per minute
    private Double temperature; //Temperature (F)

    @Size(max = 1000)
    private String gExaminationComments;

    @Column(length = 1000)
    @Size(max = 1000)
    private String listeningExamination;
    //Systemic Examination
    @Size(max = 1000)
    private String respiratorySystem;

    @Size(max = 1000)
    private String dre;

    @Size(max = 1000)
    private String gISystem;

    @Size(max = 1000)
    private String cardiovascularSystem;

    @Size(max = 1000)
    private String urogenitalSystem;

    @Size(max = 1000)
    private String nervousSystem;

    @JsonIgnore
    @OneToOne(mappedBy = "examination")
    private Register register;

    @JsonIgnore
    @OneToOne(mappedBy = "examination")
    private OutdoorRegister outdoorRegister;

    @Size(max = 1000)
    private String comments;

    @Override
    public Long getId() {
        return id;
    }

    public Examination setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Examination setVersion(Long version) {
        this.version = version;
        return this;
    }

    public Severity getAnaemia() {
        return anaemia;
    }

    public Examination setAnaemia(Severity anaemia) {
        this.anaemia = anaemia;
        return this;
    }

    public Severity getJaundice() {
        return jaundice;
    }

    public Examination setJaundice(Severity jaundice) {
        this.jaundice = jaundice;
        return this;
    }

    public LymphNode getAccessibleLymphNode() {
        return accessibleLymphNode;
    }

    public Examination setAccessibleLymphNode(LymphNode accessibleLymphNode) {
        this.accessibleLymphNode = accessibleLymphNode;
        return this;
    }

    public Severity getDehydration() {
        return dehydration;
    }

    public Examination setDehydration(Severity dehydration) {
        this.dehydration = dehydration;
        return this;
    }

    public Availability getOedema() {
        return oedema;
    }

    public Examination setOedema(Availability oedema) {
        this.oedema = oedema;
        return this;
    }

    public Integer getPulse() {
        return pulse;
    }

    public Examination setPulse(Integer pulse) {
        this.pulse = pulse;
        return this;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public Examination setSystolic(Integer systolic) {
        this.systolic = systolic;
        return this;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public Examination setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
        return this;
    }


    public Double getTemperature() {
        return temperature;
    }

    public Examination setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getgExaminationComments() {
        return gExaminationComments;
    }

    public Examination setgExaminationComments(String gExaminationComments) {
        this.gExaminationComments = gExaminationComments;
        return this;
    }

    public String getListeningExamination() {
        return listeningExamination;
    }

    public Examination setListeningExamination(String listeningExamination) {
        this.listeningExamination = listeningExamination;
        return this;
    }

    public String getRespiratorySystem() {
        return respiratorySystem;
    }

    public Examination setRespiratorySystem(String respiratorySystem) {
        this.respiratorySystem = respiratorySystem;
        return this;
    }

    public String getDre() {
        return dre;
    }

    public Examination setDre(String dre) {
        this.dre = dre;
        return this;
    }

    public String getgISystem() {
        return gISystem;
    }

    public Examination setgISystem(String gISystem) {
        this.gISystem = gISystem;
        return this;
    }

    public String getCardiovascularSystem() {
        return cardiovascularSystem;
    }

    public Examination setCardiovascularSystem(String cardiovascularSystem) {
        this.cardiovascularSystem = cardiovascularSystem;
        return this;
    }

    public String getUrogenitalSystem() {
        return urogenitalSystem;
    }

    public Examination setUrogenitalSystem(String urogenitalSystem) {
        this.urogenitalSystem = urogenitalSystem;
        return this;
    }

    public String getNervousSystem() {
        return nervousSystem;
    }

    public Examination setNervousSystem(String nervousSystem) {
        this.nervousSystem = nervousSystem;
        return this;
    }

    public Register getRegister() {
        return register;
    }

    public Examination setRegister(Register register) {
        this.register = register;
        return this;
    }

    public OutdoorRegister getOutdoorRegister() {
        return outdoorRegister;
    }

    public Examination setOutdoorRegister(OutdoorRegister outdoorRegister) {
        this.outdoorRegister = outdoorRegister;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public Examination setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    public Examination setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
        return this;
    }
}
