package com.demo.service;

import java.util.List;

import com.demo.bean.Store;
import com.demo.generic.GenericService;

public interface StoreService extends GenericService<Store>{
	public List<Store> findAllByActiveIs(boolean active);
}
