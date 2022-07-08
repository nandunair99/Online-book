package com.narola.bookmyseat.jpa;

import com.narola.bookmyseat.jpa.repository.AccountRepository;
import com.narola.bookmyseat.jpa.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/jpa")
public class JpaController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/jpa-saveAccount")
    @Transactional
    public ResponseEntity<String> saveAccountWithBranch(){

        Branch branch = new Branch();
        branch.setBranchCode("101");
        branch.setBranchName("Katargam");
        //branchRepository.save(branch);

        Account account1 = new Account();
        account1.setAccountNumber((int)Math.random()*(9999-1000+1)+1000);
        account1.setFullName("Hardik Prajapati");
        account1.setBalance(1000.0);
        account1.setBranch(branch);

        accountRepository.save(account1);
        return ResponseEntity.ok("accounts added...");
    }
    @GetMapping("/jpa-findAccountbalance")
    public ResponseEntity<String> findAccountbalance(){
        List<Account> accountList = accountRepository.findByBalance(2500.0);
        return ResponseEntity.ok(accountList.size()+"");
    }

    @GetMapping("/jpa-findAccountGreaterThanEqualBalance")
    public ResponseEntity<String> findByBalanceGreaterThanEqual(@RequestParam("balance") Double balance){
        List<Account> accountList = accountRepository.findByBalanceGreaterThanEqual(balance);
        return ResponseEntity.ok(accountList.size()+"");
    }
    @GetMapping("/jpa-findByBranchBranchName")
    public ResponseEntity<String> findByAccountFromBranch(@RequestParam("branchName") String branchName){
        List<Account> accountList = accountRepository.findByBranchBranchName(branchName);
        return ResponseEntity.ok(accountList.size()+"");
    }
    @GetMapping("/jpa-findAccountById")
    public ResponseEntity<String> findAccountById(@RequestParam("accountId") Long accId){
        Account account = accountRepository.findAccountById(accId);
        return ResponseEntity.ok(account.toString());
    }


    @GetMapping("/jpa-findByBranchCodeAndBranchName")
    public ResponseEntity<String> findByBranchCodeAndBranchName(){
        List<Branch> branchList = branchRepository.findTop3ByBranchCodeAndBranchName("102","Varchha");
        return ResponseEntity.ok(branchList.size()+"");
    }

    @GetMapping("/jpa-countByBranchCodeIsStartingWith")
    public ResponseEntity<String> countByBranchCodeIsStartingWith(){
        int count = branchRepository.countByBranchCodeIsStartingWith("10");
        return ResponseEntity.ok(count+"");
    }
    @GetMapping("/jpa-findAccountByBranchNameAndAccId")
    public ResponseEntity<String>  findAccountByBranchNameAndAccId(@RequestParam("bname") String bname,@RequestParam("id") Long id){
        //Account account=accountRepository.findAccountByBranchNameAndAccId(bname,id);
        Query query = entityManager.createQuery("select a from Account a inner join a.branch ab where ab.branchName= '"+bname+"' AND a.accountId ="+id);
        Account account =(Account) query.getSingleResult();
        return ResponseEntity.ok(account.getAccActivationDate()+"");
    }
    @PostMapping("/jpa-getAccountByActivationDate")
    public ResponseEntity<String> getAccountByActivationDate(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate){
        List<Account> accountList = accountRepository.findAccountByAccActivationDateBetween(startDate,endDate);
        return ResponseEntity.ok(accountList.size()+"");
    }

    @PostMapping("/jpa-setFullNameByAccId")
    @Transactional
    public ResponseEntity<String> setFullNameByAccId(@RequestParam("name") String name,@RequestParam("accId") Long Id){
        accountRepository.setFullNameByAccId(name,Id);
        return ResponseEntity.ok("Updated..");
    }
    @PostMapping("/jpa-deleteAccountByAccId")
    @Transactional
    public ResponseEntity<String> deleteAccountByAccId(@RequestParam("accId") Long id){
        accountRepository.deleteAccountByAccId(id);
        return ResponseEntity.ok("deleted..");
    }
}
