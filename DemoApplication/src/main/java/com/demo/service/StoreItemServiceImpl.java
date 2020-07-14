package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.StoreItem;
import com.demo.repository.StoreItemRepository;

@Service
public class StoreItemServiceImpl implements StoreItemService {
	@Autowired
	StoreItemRepository StoreItemRepository;
	
	
	@Override
	public Long findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreItem save(StoreItem entity) {
		
		return StoreItemRepository.save(entity);
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

}
