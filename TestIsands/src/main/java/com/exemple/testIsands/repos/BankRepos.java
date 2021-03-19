package com.exemple.testIsands.repos;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemple.testIsands.domain.Bank;

@SpringBootApplication
public interface BankRepos extends JpaRepository<Bank, Integer> {
}
