package com.zhenzhigu.mvc.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class IdEntity {
	
	@Id
	@GenericGenerator(name="uuid",strategy="uuid")
	@GeneratedValue(generator="uuid")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
