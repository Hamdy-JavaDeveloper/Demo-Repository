package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.bean.Item;
import com.demo.generic.GenericService;
import java.lang.Boolean;

public interface ItemService extends GenericService<Item> {
	Boolean findItemByItemName(String itemName);
	public List<String> findDistinctByUnit();
	public List<String> findDistinctByCategroy1();
	public Long findMinItemId();
	 public List<Item> findByText(String itemName,String categroy1);
	 public Page<Item> findAll(Pageable pageable);
	 public List<Item> findByCategroy1(String categroy1);
	

}
