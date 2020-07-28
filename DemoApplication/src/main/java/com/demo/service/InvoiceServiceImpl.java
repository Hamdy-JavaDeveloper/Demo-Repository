package com.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bean.Invoice;
import com.demo.bean.InvoiceItem;
import com.demo.bean.Store;
import com.demo.repository.InvoiceItemRepository;
import com.demo.repository.InvoiceRepository;
import com.demo.util.InvoiceKind;


@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired InvoiceRepository invoiceRepo ;
	
	@PostConstruct
	public void initDB()
	{
		Invoice invoice=invoiceRepo.findById(0);
		  if(invoice==null) {
				invoice=new Invoice(); invoice.setInvoiceId(0);
				invoice.setKind(InvoiceKind.OPEN);
		  
		  invoiceRepo.save(invoice);
		  }
	}
	@Override
	public Long findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice save(Invoice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice update(Invoice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Invoice entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(List<Invoice> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Invoice find(Long id) {
		
		return null;
	}

	@Override
	public List<Invoice> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Invoice findByInvoiceId(long id) {
		return invoiceRepo.findById(id) ;
	}
	

}
