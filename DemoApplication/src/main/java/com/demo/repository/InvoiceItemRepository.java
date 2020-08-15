package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.bean.Invoice;
import com.demo.bean.InvoiceItem;
import com.demo.bean.Item;
import com.demo.bean.Store;
@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
	
	  
	@Query(value="select i from InvoiceItem i where i.invoice=?1 and i.item=?2 order By i.store")
	public List<InvoiceItem> findByInvoiceAndItem(Invoice invoice, Item item);
	//@Query(value="select i from InvoiceItem i  where i.invoice.invoiceId=?1")
//	public List<InvoiceItem> findByInvoiceId(Long invoiceId );
	
	public List<InvoiceItem> findByItem(Item item);
	@Query(value="select i from InvoiceItem i where i.invoice=?1")
	public List<InvoiceItem> findByInvoice(Invoice invoice );
	
	@Query(value="select i from InvoiceItem i where i.store=?1 and i.item=?2")
	public InvoiceItem findByStoreAndItem(Store store, Item item);
	
}
