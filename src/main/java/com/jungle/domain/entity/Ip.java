package com.jungle.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ip")
public class Ip implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String app;

	@Column(nullable = false)
	private String tag;

	@Column(nullable = false)
	private String zone;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String cluster;

	@Column(nullable = false)
	private String ip;

	protected Ip() {
	}

	public Ip(final Long id, final String location, final String app, final String tag, final String zone, final String type, final String cluster, final String ip) {
		this.id = id;
		this.location = location;
		this.app = app;
		this.tag = tag;
		this.zone = zone;
		this.type = type;
		this.cluster = cluster;
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Ip [id=" + id + ", location=" + location + ", app=" + app + ", tag=" + tag + ", zone=" + zone + ", type=" + type + ", cluster=" + cluster + ", ip=" + ip + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result + ((cluster == null) ? 0 : cluster.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
		Ip other = (Ip) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (cluster == null) {
			if (other.cluster != null)
				return false;
		} else if (!cluster.equals(other.cluster))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public String getApp() {
		return app;
	}

	public String getTag() {
		return tag;
	}

	public String getZone() {
		return zone;
	}

	public String getType() {
		return type;
	}

	public String getCluster() {
		return cluster;
	}

	public String getIp() {
		return ip;
	}
}