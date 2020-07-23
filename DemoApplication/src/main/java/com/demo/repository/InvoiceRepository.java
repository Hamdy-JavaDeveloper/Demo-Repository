package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.bean.Invoice;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query(value="select i from Invoice i where invoiceId=?1")
	public Invoice findById(long id); 
}
