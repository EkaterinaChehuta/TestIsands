package com.exemple.testIsands.repos;

import com.exemple.testIsands.domain.Bank;
import com.exemple.testIsands.domain.Employee;
import com.exemple.testIsands.domain.Post;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootApplication
public interface EmployeeRepos extends JpaRepository<Employee,Integer> {
    List<Employee> findByBank(Bank bank);
    List<Employee> findByIsArchived(boolean isArchived);
    List<Employee> findByPost(Post post);
}
