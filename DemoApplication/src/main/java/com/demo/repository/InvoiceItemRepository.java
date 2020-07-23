package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.InvoiceItem;
@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

}
