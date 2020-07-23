package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.InvoiceItem;
import com.demo.repository.InvoiceItemRepository;
@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {

	
	@Autowired	
	InvoiceItemRepository invoiceItemRepo;
	@Override
	public Long findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvoiceItem save(InvoiceItem invoiceItem) {
		
		return invoiceItemRepo.save(invoiceItem);
	}

	@Override
	public InvoiceItem update(InvoiceItem entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(InvoiceItem entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInBatch(List<InvoiceItem> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public InvoiceItem find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
