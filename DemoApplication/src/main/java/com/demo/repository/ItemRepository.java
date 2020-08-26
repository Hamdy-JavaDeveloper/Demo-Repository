package com.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.bean.Item;
@Repository
 	public interface ItemRepository extends JpaRepository<Item, Long> {


	//@Query("SELECT coalesce(max(i.item_id), 0) FROM item i")
	@Query(value= "select coalesce(max(itemId),0) from Item" )
	public	Long findMaxId();
	@Query(value= "select new java.lang.Boolean(count(*)>0) from Item where itemName=?" )
	   public Boolean findItemByItemName(String itemName);
	
	@Query(value= "select distinct i.unit from Item i where i.unit !=''" )
	public List<String>findDistinctByUnit();
	
	@Query(value= "select distinct i.categroy1 from Item i where i.categroy1 !=''" )
	public List<String>findDistinctByCategroy1();
	@Query(value= "select coalesce(min(itemId),0) from Item" )
	public Long findMinItemId();
	 @Query("SELECT i FROM Item i")
	public Page<Item> findAll(Pageable pageable);
	 @Query(value="select * from items i1 where  exists (select * from  items i2 where i1.item_name=i2.item_name and i2.item_name Like %:itemName%  or  i2.code1 Like %:itemName% )and i1.categroy1 Like %:categroy1%",nativeQuery=true)
	 public List<Item> findByText( @Param("itemName")String itemName, @Param("categroy1")String categroy1);
	 @Query("select  i from Item i where i.categroy1 Like %?1% ")
	 public List<Item>findByCategroy1(String categroy1);
	
	
	

}
