package sliit.g01.procurementg01.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Account;
import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.repository.AccountRepository;
import sliit.g01.procurementg01.api.repository.AccountingStaffRepository;
import sliit.g01.procurementg01.api.service.AccountService;
import sliit.g01.procurementg01.api.service.AccountingStaffService;

import java.util.List;
import java.util.UUID;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;


	@Override
	public Account getAccountBySupplierId(String supplierId) {
		return accountRepository.findBySupplierId(supplierId);
	}

	@Override
	public Account addAccount(Account account) {
		return accountRepository.save(account);
	}
}
