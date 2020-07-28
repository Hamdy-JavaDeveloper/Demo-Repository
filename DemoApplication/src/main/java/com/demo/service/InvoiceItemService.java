package com.demo.service;

import java.util.List;

import com.demo.bean.Invoice;
import com.demo.bean.InvoiceItem;
import com.demo.bean.Item;
import com.demo.generic.GenericService;

public interface InvoiceItemService extends GenericService<InvoiceItem> {
	public List<InvoiceItem> findByInvoiceAndItem(Invoice invoice, Item item);
	public List<InvoiceItem> findByInvoice(Invoice invoice);
	public List<InvoiceItem> findByItem(Item item);
}
