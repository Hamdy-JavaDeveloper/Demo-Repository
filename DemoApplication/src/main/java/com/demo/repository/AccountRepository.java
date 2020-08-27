package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

}
