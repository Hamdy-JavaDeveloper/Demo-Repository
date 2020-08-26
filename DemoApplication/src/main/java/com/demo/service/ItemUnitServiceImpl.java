package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
import com.demo.repository.ItemUnitRepository;
@Service
@Transactional
public class ItemUnitServiceImpl implements ItemUnitService {

	@Autowired
	ItemUnitRepository itemUnitRepository;
	
	@Override
	public Long findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemUnit save(ItemUnit itemUnit) {
		return itemUnitRepository.saveAndFlush(itemUnit);
		
	}

	@Override
	public ItemUnit update(ItemUnit entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ItemUnit itemUnit) {
		
		itemUnitRepository.delete(itemUnit);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<ItemUnit> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemUnit find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemUnit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findDistinctByUnit() {
		// TODO Auto-generated method stub
		return itemUnitRepository.findDistinctByUnit();
	}

	@Override
	public List<ItemUnit> findByItem(Item item) {
		
		return itemUnitRepository.findByItem(item);
		
	}

}
