package com.exemple.testIsands.controller;

import com.exemple.testIsands.domain.Bank;
import com.exemple.testIsands.domain.Employee;
import com.exemple.testIsands.domain.Post;
import com.exemple.testIsands.repos.BankRepos;
import com.exemple.testIsands.repos.EmployeeRepos;
import com.exemple.testIsands.repos.PostRepos;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class EmployeeController {

    private final EmployeeRepos employeeRepos;

    private final PostRepos postRepos;

    private final BankRepos bankRepos;

    public EmployeeController(EmployeeRepos employeeRepos, PostRepos postRepos, BankRepos bankRepos) {
        this.employeeRepos = employeeRepos;
        this.postRepos = postRepos;
        this.bankRepos = bankRepos;
    }

    @GetMapping("/employee")
    public String getEmployeesList(Model model) {
        model.addAttribute("employees", employeeRepos.findAll());

        return "employee";
    }

    @GetMapping("/employeeNotArchived")
    public String getEmployeeNotArchived(Model model){
        model.addAttribute("employees", employeeRepos.findByIsArchived(false));

        return "employee";
    }

    @GetMapping("/employeeArchived")
    public String getEmployeeArchived(Model model){
        model.addAttribute("employees", employeeRepos.findByIsArchived(true));

        return "employee";
    }

    @GetMapping("/addEmployee")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("posts", postRepos.findAll());
        model.addAttribute("banks", bankRepos.findAll());

        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String add(Employee employee) {
        employee.setArchived(false);

        employeeRepos.save(employee);

        return "redirect:/employee";
    }

    @GetMapping("/editEmployee/{employee_id}")
    public String getEmployee(@PathVariable(name = "employee_id") Integer id, Model model) {
        model.addAttribute("employee", employeeRepos.getOne(id));
        model.addAttribute("posts", postRepos.findAll());
        model.addAttribute("banks", bankRepos.findAll());

        return "editEmployee";
    }

    @PostMapping("/editEmployee/{employee_id}")
    public String editEmployee(@RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String patronymic,
                               @RequestParam(required = false) String gender,
                               @RequestParam(required = false)
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
                               @RequestParam(required = false) Integer postId,
                               @RequestParam(required = false)
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfEmployment,
                               @RequestParam(required = false) Integer bankId,
                               @RequestParam(required = false) Integer salary,
                               @RequestParam(required = false) String mobilePhoneNumber,
                               @PathVariable(name = "employee_id") Integer id) {
        Employee employee = employeeRepos.getOne(id);

        if (lastName != null && !lastName.isEmpty()) {
            employee.setLastName(lastName);
        }

        if (firstName != null && !firstName.isEmpty()) {
            employee.setFirstName(firstName);
        }

        if (patronymic != null && !patronymic.isEmpty()) {
            employee.setPatronymic(patronymic);
        }

        if (gender != null && !gender.isEmpty()) {
            employee.setGender(gender);
        }

        if (dateOfBirth != null) {
            employee.setDateOfBirth(dateOfBirth);
        }

        if (postId != null) {
            employee.setPost(postRepos.getOne(postId));
        }

        if (dateOfEmployment != null) {
            employee.setDateOfEmployment(dateOfEmployment);
        }

        if (bankId != null) {
            employee.setBank(bankRepos.getOne(bankId));
        }

        if (salary != null) {
            employee.setSalary(salary);
        }

        if(mobilePhoneNumber != null && !mobilePhoneNumber.isEmpty()){
            employee.setMobilePhoneNumber(mobilePhoneNumber);
        }

        employeeRepos.save(employee);

        return "redirect:/employee";
    }

    @PostMapping("/archivedEmployee/{employee_id}")
    public String archivedPost(@PathVariable("employee_id") Integer id) {
        Employee employee = employeeRepos.getOne(id);

        employee.setArchived(!employee.isArchived());

        employeeRepos.save(employee);

        return "redirect:/employee";
    }
}
