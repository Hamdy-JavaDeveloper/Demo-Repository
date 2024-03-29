package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
@Repository
public interface ItemUnitRepository extends JpaRepository<ItemUnit, Long> {
		@Query(value= "select distinct iu.unit from ItemUnit iu where iu.unit!=''")
		public List<String>findDistinctByUnit();
		@Query(value="select iu from ItemUnit iu join fetch iu.item where iu.item =?")
		public List<ItemUnit> findByItem(Item item);
}
