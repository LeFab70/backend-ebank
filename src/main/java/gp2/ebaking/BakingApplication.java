package gp2.ebaking;

import gp2.ebaking.entities.AccountOperations;
import gp2.ebaking.entities.CurrentAccount;
import gp2.ebaking.entities.Customer;
import gp2.ebaking.entities.SavingAccount;
import gp2.ebaking.enums.AccountStatus;
import gp2.ebaking.enums.OperationType;
import gp2.ebaking.repositories.AccountOperationsRepository;
import gp2.ebaking.repositories.BankAccountRepository;
import gp2.ebaking.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakingApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, AccountOperationsRepository accountOperationsRepository) {
		return args ->{
			Stream.of("Fabrice","Regine","Ethane").forEach(name->{
				Customer customer=new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				 customerRepository.save(customer);
			});


			customerRepository.findAll().forEach(customer -> {
				CurrentAccount currentAccount=new CurrentAccount();
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);
				currentAccount.setId(UUID.randomUUID().toString());
				bankAccountRepository.save(currentAccount);


				SavingAccount savingAccount=new SavingAccount();
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(10.5);
				savingAccount.setId(UUID.randomUUID().toString());
				bankAccountRepository.save(savingAccount);
			});
			bankAccountRepository.findAll().forEach(bankAccount -> {
				for (int i = 0; i <10 ; i++) {
					AccountOperations accountOperations=new AccountOperations();
					accountOperations.setBankAccount(bankAccount);
					accountOperations.setOperationDate(new Date());
					accountOperations.setType(Math.random()>0.5?OperationType.CREDIT:OperationType.DEBIT);
					accountOperations.setAmount(Math.random()*5000);
					accountOperationsRepository.save(accountOperations);
				}



			});
		};

	}
}
