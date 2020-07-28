package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Item;
import com.demo.bean.StoreItem;
@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem,Long>{

	public List<StoreItem> findByItem(Item item);
	
}
