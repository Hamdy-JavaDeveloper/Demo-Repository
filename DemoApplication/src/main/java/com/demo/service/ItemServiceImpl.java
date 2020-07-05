package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Item;
import com.demo.repository.ItemRepository;

@Service
 class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepo;

	

	@Override
	public Item save(Item item) {
		// write any business logic 
		return itemRepo.save(item);
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
	


	

	
}