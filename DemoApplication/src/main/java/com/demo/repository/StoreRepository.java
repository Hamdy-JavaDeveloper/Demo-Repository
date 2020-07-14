package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Store;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	
	

}
