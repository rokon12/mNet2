package org.jugbd.mnet.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.domain.enums.Status;
import org.jugbd.mnet.domain.enums.Unit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/4/14.
 */
@Entity
public class Register extends PersistentObject implements Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @NotNull
    @NotEmpty(message = "Register id can not be empty")
    private String registrationId;

    @NotNull(message = "Admission date can not be empty")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date admissionDate;

    @NotNull
    @Column(length = 200)
    private String ward;

    @NotNull(message = "Registration type is required")
    @Column(length = 20)
    @Enumerated(value = EnumType.STRING)
    private RegistrationType registrationType;

    @Size(max = 100)
    @Column(length = 100)
    private String wardOther;

    @NotEmpty
    @Size(max = 32)
    @Column(length = 32)
    private String bedNumber;

    @NotNull
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @JsonIgnore
    @ManyToOne
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date stopDatetime;

    @JsonIgnore
    @OneToMany(mappedBy = "register")
    private Set<Vital> vitals = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "register")
    private Set<OperationalDetail> operationalDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "register")
    private Set<Visit> visits;

    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "chief_complaint_id")
    private ChiefComplaint chiefComplaint;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "diagnosis_final_id")
    private DiagnosisFinal diagnosisFinal;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;

    @JsonIgnore
    @OneToOne(orphanRemoval = false)
    @JoinColumn(name = "complication_management")
    private ComplicationManagement complicationManagement;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "life_style_id")
    private LifeStyle lifeStyle;

    @JsonIgnore
    @OneToMany(mappedBy = "register")
    private Set<Investigation> investigation = new HashSet<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_information_id")
    private PictureInformation pictureInformation;

    @Valid
    @Embedded
    private PatientContact patientContact;

    @Column(columnDefinition = "LONGTEXT" )
    private String outcome;

    @Column(columnDefinition = "LONGTEXT" )
    private String remarks;

    @Lob
    private String followUpAdvice;

    @Column(name = "outdoor_register_blnk", nullable = true)
    private Long outdoorRegister; //back reference

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getWardOther() {
        return wardOther;
    }

    public void setWardOther(String wardOther) {
        this.wardOther = wardOther;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getStopDatetime() {
        return stopDatetime;
    }

    public void setStopDatetime(Date stopDatetime) {
        this.stopDatetime = stopDatetime;
    }

    public Set<Vital> getVitals() {
        return vitals;
    }

    public void setVitals(Set<Vital> vitals) {
        this.vitals = vitals;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public Set<OperationalDetail> getOperationalDetails() {
        return operationalDetails;
    }

    public void setOperationalDetails(Set<OperationalDetail> operationalDetails) {
        this.operationalDetails = operationalDetails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public ChiefComplaint getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(ChiefComplaint chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public TreatmentPlan getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(TreatmentPlan treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public PatientContact getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(PatientContact patientContact) {
        this.patientContact = patientContact;
    }

    public ComplicationManagement getComplicationManagement() {
        return complicationManagement;
    }

    public void setComplicationManagement(ComplicationManagement complicationManagement) {
        this.complicationManagement = complicationManagement;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public Set<Investigation> getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Set<Investigation> investigation) {
        this.investigation = investigation;
    }

    public PictureInformation getPictureInformation() {
        return pictureInformation;
    }

    public void setPictureInformation(PictureInformation pictureInformation) {
        this.pictureInformation = pictureInformation;
    }

    public Long getOutdoorRegister() {
        return outdoorRegister;
    }

    public Register setOutdoorRegister(Long outdoorRegister) {
        this.outdoorRegister = outdoorRegister;
        return this;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public Register setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
        return this;
    }

    public String getOutcome() {
        return outcome;
    }

    public Register setOutcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public Register setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getFollowUpAdvice() {
        return followUpAdvice;
    }

    public Register setFollowUpAdvice(String followUpAdvice) {
        this.followUpAdvice = followUpAdvice;
        return this;
    }

    public DiagnosisFinal getDiagnosisFinal() {
        return diagnosisFinal;
    }

    public void setDiagnosisFinal(DiagnosisFinal diagnosisFinal) {
        this.diagnosisFinal = diagnosisFinal;
    }

    @Override
    public String toString() {

        return "Register{" +
                "registrationId='" + registrationId + '\'' +
                ", id=" + id +
                ", version=" + version +
                ", ward=" + ward +
                ", admissionDate=" + admissionDate +
                ", wardOther='" + wardOther + '\'' +
                ", bedNumber='" + bedNumber + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
