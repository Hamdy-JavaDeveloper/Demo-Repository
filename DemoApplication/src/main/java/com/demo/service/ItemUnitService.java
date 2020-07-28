package com.demo.service;

import java.util.List;

import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
import com.demo.generic.GenericService;

public interface ItemUnitService extends GenericService<ItemUnit> {
	List<String>findDistinctByUnit();
	public List<ItemUnit> findByItem(Item item);
}
