package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bean.Item;
import com.demo.bean.StoreItem;
import com.demo.repository.StoreItemRepository;

@Service
@Transactional
public class StoreItemServiceImpl implements StoreItemService {
	@Autowired
	StoreItemRepository storeItemRepository;
	
	
	@Override
	public Long findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public StoreItem save(StoreItem entity) {
		
		return storeItemRepository.save(entity);
	}

	@Override
	public StoreItem update(StoreItem entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(StoreItem entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<StoreItem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StoreItem find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreItem> findByItem(Item item) {
		
		return storeItemRepository.findByItem(item);
	}

}
