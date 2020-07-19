package com.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="items_units")
public class ItemUnit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id_FK",nullable=false)
	private Item item;
	
	private String unit;
	@Column(columnDefinition="Decimal(11,6) default '1.000000' ")
	
	private double pieces;
	@Column(columnDefinition="Decimal(13,3) default '0.000' ")
	private double price;

}
