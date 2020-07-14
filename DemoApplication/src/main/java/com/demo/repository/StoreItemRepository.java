package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.StoreItem;
@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem,Long>{

}
