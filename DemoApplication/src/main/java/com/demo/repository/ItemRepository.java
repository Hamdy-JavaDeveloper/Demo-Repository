package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.bean.Item;
@Repository
 	public interface ItemRepository extends JpaRepository<Item, Long> {


	//@Query("SELECT coalesce(max(i.item_id), 0) FROM item i")
	@Query(value= "select coalesce(max(itemId),0) from Item" )
		Long findMaxId();
	@Query(value= "select new java.lang.Boolean(count(*)>0) from Item where itemName=?" )
	   Boolean findItemByItemName(String itemName);
	

}
