package com.narola.bookmyseat.jpa.repository;

import com.narola.bookmyseat.jpa.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {
    List<Account> findByBalance(double balance);
    List<Account> findByBalanceGreaterThanEqual(double balance);
    List<Account> findByBranchBranchName(String branchName);

    @Query("select a from Account a where a.accountId =?1")
    Account findAccountById(Long Id);
//    @Query("select a from Account a where a.accountId =:Id")
//    Account findAccountById(@Param("Id") Long Id);

    @Query("select a from Account a inner join a.branch ab where ab.branchName=?1 AND a.accountId =?2")
    Account findAccountByBranchNameAndAccId(String branchName,Long Id);

    List<Account> findAccountByAccActivationDateBetween(Date startDate, Date endDate);

    @Modifying
    @Query("update Account a set a.fullName=?1 where a.accountId=?2")
    int setFullNameByAccId(String name,Long Id);

    @Modifying
    @Query("delete from Account a where a.accountId=?1")
    int deleteAccountByAccId(Long Id);

}

