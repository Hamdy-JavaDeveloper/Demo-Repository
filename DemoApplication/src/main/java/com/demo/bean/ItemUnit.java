/*package com.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items_units")
public class ItemUnit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long itemId;
	private String unit;
	@Column(columnDefinition="Decimal(11,6) default '1.000000' ")
	private double pieces;
	@Column(columnDefinition="Decimal(13,3) default '0.000' ")
	private double price;

}
*/