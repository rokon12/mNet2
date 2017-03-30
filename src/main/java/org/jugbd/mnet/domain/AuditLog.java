package org.jugbd.mnet.domain;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jugbd.mnet.domain.enums.AuditAction;
import org.jugbd.mnet.json.JsonBinaryType;
import org.jugbd.mnet.json.JsonStringType;

import javax.persistence.*;

/**
 * @author Bazlur Rahman Rokon
 * @date 11/21/2014.
 */
@TypeDefs({
	@TypeDef(name = "json", typeClass = JsonStringType.class),
	@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Entity
public class AuditLog extends PersistentObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long auditLogId;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private AuditAction action;

	//@Column(columnDefinition = "TEXT")

	@Type(type = "json")
	@Column(columnDefinition = "json")
	private Auditable detail;
	private Long entityId;
	private String entityName;

	public Long getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(Long auditLogId) {
		this.auditLogId = auditLogId;
	}

	public AuditAction getAction() {
		return action;
	}

	public void setAction(AuditAction action) {
		this.action = action;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Auditable getDetail() {
		return detail;
	}

	public void setDetail(Auditable detail) {
		this.detail = detail;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
