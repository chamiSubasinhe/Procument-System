package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.Account;
import sliit.g01.procurementg01.api.model.AccountingStaff;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

	Account findBySupplierId(String supplierId);


}
