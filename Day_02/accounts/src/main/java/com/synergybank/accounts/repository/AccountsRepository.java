package com.synergybank.accounts.repository;

import com.synergybank.accounts.entity.Accounts;
import com.synergybank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
