package atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import atm.model.BankAccount;
import atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("allBankAccount", bankAccountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String addBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.createBankAccount(bankAccount);
        model.addAttribute("allCustomers", bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

}
