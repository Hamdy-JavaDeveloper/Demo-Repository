package com.demo.service;

import java.util.List;

import com.demo.bean.Item;
import com.demo.bean.StoreItem;
import com.demo.generic.GenericService;

public interface StoreItemService extends GenericService<StoreItem> {
	public List<StoreItem>findByItem(Item item);
	
}
