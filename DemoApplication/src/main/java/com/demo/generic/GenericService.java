package com.demo.generic;

import java.util.List;

import com.demo.bean.Item;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

public interface GenericService<T extends Object> {

	Long findMaxId();
	T save(T entity);
	
    T update(T entity);
  
    void delete(T entity);
  
    void delete(Long id);
    
    void deleteInBatch(List<T> entities);
  
    T find(Long id); //or T get(Long id);
  
    List<T> findAll();  // or List<T> getAll();


}
