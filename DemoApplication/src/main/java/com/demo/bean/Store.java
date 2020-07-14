package com.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
@Entity
@Table(name="stores")
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id;
	
	@Column(columnDefinition="int(11) default '0' ")
	private Long sn;
	
	@Column(nullable=true)
	private String storeName;
	
	@Column(columnDefinition="tinyint(1) default '0' ")
	private boolean active;
	

}
