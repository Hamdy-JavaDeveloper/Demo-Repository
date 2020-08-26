package com.demo.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
import com.demo.bean.StoreItem;
import com.demo.repository.ItemRepository;

@Service
@Transactional
 class ItemServiceImpl implements ItemService {
	
@Autowired private InvoiceItemService invoiceItemService;
@Autowired private StoreItemService storeItemService;
@Autowired private ItemUnitService itemUnitService;
@Autowired private ItemRepository itemRepo;

	

	@Override
	public Item save(Item item) {
		// write any business logic 
		return itemRepo.saveAndFlush(item);
	}

	@Override
	public Item update(Item entity) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void delete(Item item) {
		Iterator iterator;
		if(item.getQty()>0) {
			List<StoreItem> storeItemList= storeItemService.findByItem(item);
			 iterator=storeItemList.iterator();
			
			while(iterator.hasNext()) {
			invoiceItemService.deleteByStoreItem((StoreItem)iterator.next());
			}
			iterator=storeItemList.iterator();
			while(iterator.hasNext()) {
			storeItemService.delete((StoreItem)iterator.next());
			}
		}
		//if(!item.getUnit().isEmpty()) {
			List<ItemUnit> itemUnitList=itemUnitService.findByItem(item);
				if(itemUnitList.size()>0)
				{
					iterator=itemUnitList.iterator();
					while(iterator.hasNext()) {
						itemUnitService.delete((ItemUnit)iterator.next());
					}
				//}
		}
			
		
		
		itemRepo.delete(item);

	}

	@Override
	public void delete(Long id) {
		

	}

	@Override
	public void deleteInBatch(List<Item> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item find(Long id) {
		return itemRepo.findOne(id);
	}

	@Override
	public List<Item> findAll() {
		
		return itemRepo.findAll();
	}

	@Override
	public Long findMaxId() {
		
		return itemRepo.findMaxId();
	}

	@Override
	public Boolean findItemByItemName(String itemName) {
		
		return itemRepo.findItemByItemName(itemName);
	}

	@Override
	public List<String> findDistinctByUnit() {
		return itemRepo.findDistinctByUnit();
	}

	@Override
	public List<String> findDistinctByCategroy1() {
		return itemRepo.findDistinctByCategroy1();

	}

	@Override
	public Long findMinItemId() {
		
		return itemRepo.findMinItemId();
	}

	@Override
	public Page<Item> findAll(Pageable pageable) {
		
		return itemRepo.findAll(pageable);
	}


	@Override
	public List<Item> findByCategroy1(String categroy1) {
		// TODO Auto-generated method stub
		return itemRepo.findByCategroy1(categroy1);
	}

	@Override
	public List<Item> findByText(String itemName, String categroy1) {
		// TODO Auto-generated method stub
		return itemRepo.findByText(itemName, categroy1);
	}
	


	

	
}