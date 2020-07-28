package com.demo.service;

import java.util.List;

import com.demo.bean.Invoice;
import com.demo.bean.InvoiceItem;
import com.demo.generic.GenericService;

public interface InvoiceService extends GenericService<Invoice> {
public Invoice findByInvoiceId(long id);


}
