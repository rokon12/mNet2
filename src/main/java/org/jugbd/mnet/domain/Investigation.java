package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.Biopsy;
import org.jugbd.mnet.domain.enums.BloodSugar;
import org.jugbd.mnet.domain.enums.PT;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Bazlur Rahman Rokon
 * @date 12/27/14.
 */
@Entity
public class Investigation extends PersistentObject implements Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "comment", column = @Column(name = "comment_blood_cbc")),
		@AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_blood_cbc"))
	})
	private BloodCbc bloodCbc;

	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "nameOfOrganism", column = @Column(name = "blood_cbc_name_of_organism")),
		@AttributeOverride(name = "sensitiveAntibiotic", column = @Column(name = "blood_cbc_sensitive_antibiotic")),
		@AttributeOverride(name = "comment", column = @Column(name = "comment_blood_cs")),
		@AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_blood_cs")),
	})
	private CultureAndSensitivity bloodCs;

	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "nameOfOrganism", column = @Column(name = "wound_cs_name_of_organism")),
		@AttributeOverride(name = "sensitiveAntibiotic", column = @Column(name = "wound_cs_sensitive_antibiotic")),
		@AttributeOverride(name = "comment", column = @Column(name = "comment_wound_cs")),
		@AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_wound_cs")),
	})
	private CultureAndSensitivity woundCs;

	@AttributeOverrides({
		@AttributeOverride(name = "comment", column = @Column(name = "comment_electrolyte")),
		@AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_electrolyte"))
	})
	private Electrolyte electrolyte;

	//Others

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private BloodSugar bloodSugar;

	@Size(max = 500)
	private String urineRe; // Urine R/E -> urine routine examination

	@Size(max = 500)
	private String sCreatinine; //S. Creatinine

	@Size(max = 500)
	private String cxr;

	@Size(max = 500)
	private String ecg;

	@Size(max = 500)
	private String echo;

	@Size(max = 500)
	private String sCalcium;

	@Size(max = 500)
	private String sMg;

	@Size(max = 500)
	private String dDimer;

	@Size(max = 500)
	private String aptt; //APTT

	@Size(max = 500)
	private String ada; //ADA

	@Size(max = 1500)
	private String other; //Other


	/*********
	 * LFT - Liver Function Test
	 **********/

	@Size(max = 500)
	private String sBilirubin;

	@Size(max = 500)
	private String sgpt; //SGPT

	@Size(max = 500)
	private String sgut; // SGUT

	@Size(max = 500)
	private String alkalinePhosphatase;

	@Size(max = 500)
	private String sTotalProtein;

	@Size(max = 500)
	private String serumAlbumin;

	@Size(max = 500)
	private String albuminGlobulinRatio; // A:G Ration

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private PT pt;

	/************
	 * XRays
	 ************/

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date xRaysDate;

	@Size(max = 500)
	private String plainXRayAbdomen;

	@Size(max = 500)
	private String xRayKub;

	@Size(max = 500)
	private String xRayOthers;

	/*********
	 * Ultrasonogram
	 *********/

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ultrasonogramDate;

	@Size(max = 500)
	private String usgOfWA;

	@Size(max = 500)
	private String usgOfHBS;

	@Size(max = 500)
	private String usgOfLA;

	@Size(max = 500)
	private String usaKub;

	@Size(max = 500)
	private String ultrasonogramOthers;

	/*
	CT Scan
	- Date
	- Name of CT
	- Findings
	*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ctScanDate;

	@Size(max = 200)
	private String nameOfCt;

	@Size(max = 1500)
	private String ctFindings;

	/*
	MRCP
	- Date
	- Findings
	*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date mrcpDate;

	@Size(max = 1500)
	private String mrcpFindings;

	/*
	MRI
	- Date
	- Name of MRi
	- Findings
	* */

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date mriDate;
	@Size(max = 200)
	private String nameOfMri;

	@Size(max = 1500)
	private String mriFindings;

	/*
	ERCP
		- Date
		- Findings
		- Intervention
	*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ercpDate;

	@Size(max = 1500)
	private String ercpFindings;

	@Size(max = 1000)
	private String intervention;

	/*
	Upper GI Endoscopy
		- Date
		- Findings
	*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date upperGiEndoscopyDate;

	@Size(max = 1500)
	private String upperGiEndoscopyFindings;


	/*
	Colonoscopy/Sigmoidoscopy
		Date
		Findings
	*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date colonscopyDate;

	@Size(max = 1500)
	private String colonscopyFindings;


	/*
	FNAC/Biopsy
		Date
		Findings
			Preoperative FNAC
			Preoperative Biopsy
			Postoperative Biopsy
	* */

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date biopsyDate;

	@Size(max = 1500)
	private String biopsyFindings;

	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private Biopsy biopsy;

	/*

	Contrast
		Date
		Name of Contrast Film
		Findings
	*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date contrastDate;

	@Size(max = 150)
	private String nameOfContractFilm;

	@Size(max = 1500)
	private String contrastFindings;

	@Size(min = 0, max = 2000)
	//@Max(2000)
	//@Min(0)
	private String otherInvestigation;

	@JsonIgnore
	@ManyToOne
	private Register register;

	@Override
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

	public BloodCbc getBloodCbc() {
		return bloodCbc;
	}

	public Investigation setBloodCbc(BloodCbc bloodCbc) {
		this.bloodCbc = bloodCbc;
		return this;
	}

	public CultureAndSensitivity getBloodCs() {
		return bloodCs;
	}

	public Investigation setBloodCs(CultureAndSensitivity bloodCs) {
		this.bloodCs = bloodCs;
		return this;
	}

	public CultureAndSensitivity getWoundCs() {
		return woundCs;
	}

	public Investigation setWoundCs(CultureAndSensitivity woundCs) {
		this.woundCs = woundCs;
		return this;
	}

	public Electrolyte getElectrolyte() {
		return electrolyte;
	}

	public Investigation setElectrolyte(Electrolyte electrolyte) {
		this.electrolyte = electrolyte;
		return this;
	}

	public BloodSugar getBloodSugar() {
		return bloodSugar;
	}

	public Investigation setBloodSugar(BloodSugar bloodSugar) {
		this.bloodSugar = bloodSugar;
		return this;
	}

	public String getUrineRe() {
		return urineRe;
	}

	public Investigation setUrineRe(String urineRe) {
		this.urineRe = urineRe;
		return this;
	}

	public String getsCreatinine() {
		return sCreatinine;
	}

	public Investigation setsCreatinine(String sCreatinine) {
		this.sCreatinine = sCreatinine;
		return this;
	}

	public String getCxr() {
		return cxr;
	}

	public Investigation setCxr(String cxr) {
		this.cxr = cxr;
		return this;
	}

	public String getEcg() {
		return ecg;
	}

	public Investigation setEcg(String ecg) {
		this.ecg = ecg;
		return this;
	}

	public String getEcho() {
		return echo;
	}

	public Investigation setEcho(String echo) {
		this.echo = echo;
		return this;
	}

	public String getsCalcium() {
		return sCalcium;
	}

	public Investigation setsCalcium(String sCalcium) {
		this.sCalcium = sCalcium;
		return this;
	}

	public String getsMg() {
		return sMg;
	}

	public Investigation setsMg(String sMg) {
		this.sMg = sMg;
		return this;
	}

	public String getdDimer() {
		return dDimer;
	}

	public Investigation setdDimer(String dDimer) {
		this.dDimer = dDimer;
		return this;
	}

	public String getAptt() {
		return aptt;
	}

	public Investigation setAptt(String aptt) {
		this.aptt = aptt;
		return this;
	}

	public String getAda() {
		return ada;
	}

	public Investigation setAda(String ada) {
		this.ada = ada;
		return this;
	}

	public String getOther() {
		return other;
	}

	public Investigation setOther(String other) {
		this.other = other;
		return this;
	}

	public String getsBilirubin() {
		return sBilirubin;
	}

	public Investigation setsBilirubin(String sBilirubin) {
		this.sBilirubin = sBilirubin;
		return this;
	}

	public String getSgpt() {
		return sgpt;
	}

	public Investigation setSgpt(String sgpt) {
		this.sgpt = sgpt;
		return this;
	}

	public String getSgut() {
		return sgut;
	}

	public Investigation setSgut(String sgut) {
		this.sgut = sgut;
		return this;
	}

	public String getAlkalinePhosphatase() {
		return alkalinePhosphatase;
	}

	public Investigation setAlkalinePhosphatase(String alkalinePhosphatase) {
		this.alkalinePhosphatase = alkalinePhosphatase;
		return this;
	}

	public String getsTotalProtein() {
		return sTotalProtein;
	}

	public Investigation setsTotalProtein(String sTotalProtein) {
		this.sTotalProtein = sTotalProtein;
		return this;
	}

	public String getSerumAlbumin() {
		return serumAlbumin;
	}

	public Investigation setSerumAlbumin(String serumAlbumin) {
		this.serumAlbumin = serumAlbumin;
		return this;
	}

	public String getAlbuminGlobulinRatio() {
		return albuminGlobulinRatio;
	}

	public Investigation setAlbuminGlobulinRatio(String albuminGlobulinRatio) {
		this.albuminGlobulinRatio = albuminGlobulinRatio;
		return this;
	}

	public PT getPt() {
		return pt;
	}

	public Investigation setPt(PT pt) {
		this.pt = pt;
		return this;
	}

	public Date getxRaysDate() {
		return xRaysDate;
	}

	public Investigation setxRaysDate(Date xRaysDate) {
		this.xRaysDate = xRaysDate;
		return this;
	}

	public String getPlainXRayAbdomen() {
		return plainXRayAbdomen;
	}

	public Investigation setPlainXRayAbdomen(String plainXRayAbdomen) {
		this.plainXRayAbdomen = plainXRayAbdomen;
		return this;
	}

	public String getxRayKub() {
		return xRayKub;
	}

	public Investigation setxRayKub(String xRayKub) {
		this.xRayKub = xRayKub;
		return this;
	}

	public String getxRayOthers() {
		return xRayOthers;
	}

	public Investigation setxRayOthers(String xRayOthers) {
		this.xRayOthers = xRayOthers;
		return this;
	}

	public Date getUltrasonogramDate() {
		return ultrasonogramDate;
	}

	public Investigation setUltrasonogramDate(Date ultrasonogramDate) {
		this.ultrasonogramDate = ultrasonogramDate;
		return this;
	}

	public String getUsgOfWA() {
		return usgOfWA;
	}

	public Investigation setUsgOfWA(String usgOfWA) {
		this.usgOfWA = usgOfWA;
		return this;
	}

	public String getUsgOfHBS() {
		return usgOfHBS;
	}

	public Investigation setUsgOfHBS(String usgOfHBS) {
		this.usgOfHBS = usgOfHBS;
		return this;
	}

	public String getUsgOfLA() {
		return usgOfLA;
	}

	public Investigation setUsgOfLA(String usgOfLA) {
		this.usgOfLA = usgOfLA;
		return this;
	}

	public String getUsaKub() {
		return usaKub;
	}

	public Investigation setUsaKub(String usaKub) {
		this.usaKub = usaKub;
		return this;
	}

	public String getUltrasonogramOthers() {
		return ultrasonogramOthers;
	}

	public Investigation setUltrasonogramOthers(String ultrasonogramOthers) {
		this.ultrasonogramOthers = ultrasonogramOthers;
		return this;
	}

	public Date getCtScanDate() {
		return ctScanDate;
	}

	public Investigation setCtScanDate(Date ctScanDate) {
		this.ctScanDate = ctScanDate;
		return this;
	}

	public String getNameOfCt() {
		return nameOfCt;
	}

	public Investigation setNameOfCt(String nameOfCt) {
		this.nameOfCt = nameOfCt;
		return this;
	}

	public String getCtFindings() {
		return ctFindings;
	}

	public Investigation setCtFindings(String ctFindings) {
		this.ctFindings = ctFindings;
		return this;
	}

	public Date getMrcpDate() {
		return mrcpDate;
	}

	public Investigation setMrcpDate(Date mrcpDate) {
		this.mrcpDate = mrcpDate;
		return this;
	}

	public String getMrcpFindings() {
		return mrcpFindings;
	}

	public Investigation setMrcpFindings(String mrcpFindings) {
		this.mrcpFindings = mrcpFindings;
		return this;
	}

	public Date getMriDate() {
		return mriDate;
	}

	public Investigation setMriDate(Date mriDate) {
		this.mriDate = mriDate;
		return this;
	}

	public String getNameOfMri() {
		return nameOfMri;
	}

	public Investigation setNameOfMri(String nameOfMri) {
		this.nameOfMri = nameOfMri;
		return this;
	}

	public String getMriFindings() {
		return mriFindings;
	}

	public Investigation setMriFindings(String mriFindings) {
		this.mriFindings = mriFindings;
		return this;
	}

	public Date getErcpDate() {
		return ercpDate;
	}

	public Investigation setErcpDate(Date ercpDate) {
		this.ercpDate = ercpDate;
		return this;
	}

	public String getErcpFindings() {
		return ercpFindings;
	}

	public Investigation setErcpFindings(String ercpFindings) {
		this.ercpFindings = ercpFindings;
		return this;
	}

	public String getIntervention() {
		return intervention;
	}

	public Investigation setIntervention(String intervention) {
		this.intervention = intervention;
		return this;
	}

	public Date getUpperGiEndoscopyDate() {
		return upperGiEndoscopyDate;
	}

	public Investigation setUpperGiEndoscopyDate(Date upperGiEndoscopyDate) {
		this.upperGiEndoscopyDate = upperGiEndoscopyDate;
		return this;
	}

	public String getUpperGiEndoscopyFindings() {
		return upperGiEndoscopyFindings;
	}

	public Investigation setUpperGiEndoscopyFindings(String upperGiEndoscopyFindings) {
		this.upperGiEndoscopyFindings = upperGiEndoscopyFindings;
		return this;
	}

	public Date getColonscopyDate() {
		return colonscopyDate;
	}

	public Investigation setColonscopyDate(Date colonscopyDate) {
		this.colonscopyDate = colonscopyDate;
		return this;
	}

	public String getColonscopyFindings() {
		return colonscopyFindings;
	}

	public Investigation setColonscopyFindings(String colonscopyFindings) {
		this.colonscopyFindings = colonscopyFindings;
		return this;
	}

	public Date getBiopsyDate() {
		return biopsyDate;
	}

	public Investigation setBiopsyDate(Date biopsyDate) {
		this.biopsyDate = biopsyDate;
		return this;
	}

	public String getBiopsyFindings() {
		return biopsyFindings;
	}

	public Investigation setBiopsyFindings(String biopsyFindings) {
		this.biopsyFindings = biopsyFindings;
		return this;
	}

	public Biopsy getBiopsy() {
		return biopsy;
	}

	public Investigation setBiopsy(Biopsy biopsy) {
		this.biopsy = biopsy;
		return this;
	}

	public Date getContrastDate() {
		return contrastDate;
	}

	public Investigation setContrastDate(Date contrastDate) {
		this.contrastDate = contrastDate;
		return this;
	}

	public String getNameOfContractFilm() {
		return nameOfContractFilm;
	}

	public Investigation setNameOfContractFilm(String nameOfContractFilm) {
		this.nameOfContractFilm = nameOfContractFilm;
		return this;
	}

	public String getContrastFindings() {
		return contrastFindings;
	}

	public Investigation setContrastFindings(String contrastFindings) {
		this.contrastFindings = contrastFindings;
		return this;
	}

	public String getOtherInvestigation() {
		return otherInvestigation;
	}

	public Investigation setOtherInvestigation(String otherInvestigation) {
		this.otherInvestigation = otherInvestigation;
		return this;
	}

	public Register getRegister() {
		return register;
	}

	public Investigation setRegister(Register register) {
		this.register = register;
		return this;
	}
}
