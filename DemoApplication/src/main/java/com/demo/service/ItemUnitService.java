package com.demo.service;

import java.util.List;

import com.demo.bean.ItemUnit;
import com.demo.generic.GenericService;

public interface ItemUnitService extends GenericService<ItemUnit> {
	List<String>findDistinctByUnit();
}
