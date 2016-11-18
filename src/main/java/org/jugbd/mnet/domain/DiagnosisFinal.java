package org.jugbd.mnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.SystemInvolved;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/18/16.
 */
@Entity
public class DiagnosisFinal extends PersistentObject implements Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@Size(max = 3000)
	private String diagnosis;

	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private SystemInvolved systemInvolved;

	@JsonIgnore
	@OneToOne(mappedBy = "diagnosisFinal")
	private Register register;

	@Override
	public Long getId() {
		return id;
	}

	public DiagnosisFinal setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getVersion() {
		return version;
	}

	public DiagnosisFinal setVersion(Long version) {
		this.version = version;
		return this;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public DiagnosisFinal setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
		return this;
	}

	public SystemInvolved getSystemInvolved() {
		return systemInvolved;
	}

	public DiagnosisFinal setSystemInvolved(SystemInvolved systemInvolved) {
		this.systemInvolved = systemInvolved;
		return this;
	}

	public Register getRegister() {
		return register;
	}

	public DiagnosisFinal setRegister(Register register) {
		this.register = register;
		return this;
	}
}
