package com.jungle.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audit")
public class Audit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Long requestId;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private String message;

	protected Audit() {
	}

	public Audit(final Long id, final Long requestId, final Date date, final int type, final String message) {
		this.id = id;
		this.requestId = requestId;
		this.date = date;
		this.type = type;
		this.message = message;
	}

	public Audit(final Long requestId, final Date date, final int type, final String message) {
		this.requestId = requestId;
		this.date = date;
		this.type = type;
		this.message = message;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", requestId=" + requestId + ", date=" + date + ", type=" + type + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Audit other = (Audit) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public Long getRequestId() {
		return requestId;
	}

	public Date getDate() {
		return date;
	}

	public int getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
}