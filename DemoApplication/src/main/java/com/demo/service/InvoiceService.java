package com.demo.service;

import com.demo.bean.Invoice;
import com.demo.generic.GenericService;

public interface InvoiceService extends GenericService<Invoice> {
public Invoice findByInvoiceId(long id);

}
