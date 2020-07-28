package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bean.Item;
import com.demo.repository.ItemRepository;

@Service
@Transactional
 class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepo;

	

	@Override
	public Item save(Item item) {
		// write any business logic 
		return itemRepo.saveAndFlush(item);
	}

	@Override
	public Item update(Item entity) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void delete(Item entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInBatch(List<Item> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item find(Long id) {
		return itemRepo.findOne(id);
	}

	@Override
	public List<Item> findAll() {
		
		return itemRepo.findAll();
	}

	@Override
	public Long findMaxId() {
		
		return itemRepo.findMaxId();
	}

	@Override
	public Boolean findItemByItemName(String itemName) {
		
		return itemRepo.findItemByItemName(itemName);
	}

	@Override
	public List<String> findDistinctByUnit() {
		return itemRepo.findDistinctByUnit();
	}

	@Override
	public List<String> findDistinctByCategroy1() {
		return itemRepo.findDistinctByCategroy1();

	}
	


	

	
}