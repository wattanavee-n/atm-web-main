package atm.service;

import org.springframework.stereotype.Service;

import atm.model.BankAccount;

import java.util.ArrayList;

@Service
public class BankAccountService {

    private final ArrayList<BankAccount> bankAccountList = new ArrayList<>();

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccountList;
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

}
