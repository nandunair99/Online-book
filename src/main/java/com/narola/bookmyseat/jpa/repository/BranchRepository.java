package com.narola.bookmyseat.jpa.repository;

import com.narola.bookmyseat.jpa.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch,Long> {
    List<Branch> findTop3ByBranchCodeAndBranchName(String branchCode, String branchName);
    int countByBranchCodeIsStartingWith(String ex);


}
