package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Store;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	
	List<Store> findAllByActiveIs(boolean active);

}
