package com.demo.service;

import java.util.Optional;

import com.demo.bean.Item;
import com.demo.generic.GenericService;
import java.lang.Boolean;

public interface ItemService extends GenericService<Item> {
	Boolean findItemByItemName(String itemName);
	

}
