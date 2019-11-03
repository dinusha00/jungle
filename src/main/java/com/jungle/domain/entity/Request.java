package com.jungle.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String sourceIp;

	@Column(nullable = false)
	private String destinationIp;

	@Column(nullable = false)
	private String status;

	protected Request() {
	}

	public Request(final Long id, final String sourceIp, final String destinationIp, final String status) {
		this.id = id;
		this.sourceIp = sourceIp;
		this.destinationIp = destinationIp;
		this.status = status;
	}

	public Request(final String sourceip, final String destinationip, final String status) {
		this.sourceIp = sourceip;
		this.destinationIp = destinationip;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", sourceIp=" + sourceIp + ", destinationIp=" + destinationIp + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationIp == null) ? 0 : destinationIp.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sourceIp == null) ? 0 : sourceIp.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Request other = (Request) obj;
		if (destinationIp == null) {
			if (other.destinationIp != null)
				return false;
		} else if (!destinationIp.equals(other.destinationIp))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sourceIp == null) {
			if (other.sourceIp != null)
				return false;
		} else if (!sourceIp.equals(other.sourceIp))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public String getDestinationIp() {
		return destinationIp;
	}

	public String getStatus() {
		return status;
	}
}