package com.exemple.testIsands.controller;

import com.exemple.testIsands.domain.Bank;
import com.exemple.testIsands.repos.BankRepos;
import com.exemple.testIsands.repos.EmployeeRepos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    private final BankRepos bankRepos;

    private final EmployeeRepos employeeRepos;

    public BankController(BankRepos bankRepos, EmployeeRepos employeeRepos) {
        this.bankRepos = bankRepos;
        this.employeeRepos = employeeRepos;
    }

    @GetMapping("/bank")
    public String getBanksList(Model model) {
        model.addAttribute("banks", bankRepos.findAll());

        return "bank";
    }

    @GetMapping("/addBank")
    public String newBank(Model model){
        model.addAttribute("bank", new Bank());

        return "addBank";
    }

    @PostMapping("/addBank")
    public String add(Bank bank){
        bankRepos.save(bank);

        return "redirect:/bank";
    }

    @GetMapping("/editBank/{bank_id}")
    public String getBank(@PathVariable("bank_id") Integer id, Model model){
        Bank bank = bankRepos.getOne(id);

        model.addAttribute("bank", bank);
        model.addAttribute("employees", employeeRepos.findByBank(bank));

        return "editBank";
    }

    @PostMapping("/editBank/{bank_id}")
    public String editBank(@RequestParam(required = false) String bik,
                           @RequestParam (required = false) String city,
                           @RequestParam (required = false) String street,
                           @RequestParam (required = false) String houseNumber,
                           @PathVariable("bank_id") Integer id){
        Bank bank = bankRepos.getOne(id);

        if(bik != null && !bik.isEmpty()){
            bank.setBik(bik);
        }

        if(city != null && !city.isEmpty()){
            bank.setCity(city);
        }

        if(street != null && !street.isEmpty()){
            bank.setStreet(street);
        }

        if(houseNumber != null && !houseNumber.isEmpty()){
            bank.setHouseNumber(houseNumber);
        }

        bankRepos.save(bank);

        return "redirect:/bank";
    }
}
