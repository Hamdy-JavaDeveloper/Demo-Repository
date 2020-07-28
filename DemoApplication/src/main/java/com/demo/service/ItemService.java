package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.bean.Item;
import com.demo.generic.GenericService;
import java.lang.Boolean;

public interface ItemService extends GenericService<Item> {
	Boolean findItemByItemName(String itemName);
	public List<String> findDistinctByUnit();
	public List<String> findDistinctByCategroy1();
	
	

}
