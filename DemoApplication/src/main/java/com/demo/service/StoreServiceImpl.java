package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Store;
import com.demo.repository.StoreRepository;
@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	StoreRepository storeRepository;
/**
 * initDB() create Primary Store
 */
	
	@PostConstruct
	public void initDB()
	{
	List<Store> stores=new ArrayList<>();
	stores.add(new Store(1L,1L,true,"General Store"));
	stores.add(new Store(2L,1L,true,"Scound Store"));
	stores.add(new Store(3L,1L,true,"Third Store"));
	stores.add(new Store(4L,1L,true,"Fourth Store"));
	stores.add(new Store(5L,1L,true,"Fivth Store"));
	storeRepository.save(stores);
	}
	
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

	@Override
	public List<Store> findAllByActiveIs(boolean active) {
		// TODO Auto-generated method stub
		return storeRepository.findAllByActiveIs(true);
	}

}
