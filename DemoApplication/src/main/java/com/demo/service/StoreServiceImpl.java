package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Store;
import com.demo.repository.StoreRepository;
@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	StoreRepository storeRepository;

	@Override
	public Long findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store save(Store entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store update(Store entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Store entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<Store> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Store find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> findAll() {
		
		return storeRepository.findAll();
	}

}
