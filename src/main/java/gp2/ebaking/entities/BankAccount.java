package gp2.ebaking.entities;

import gp2.ebaking.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType = DiscriminatorType.STRING,length = 4)
//public  abstract class BankAccount { ici la on cree par la table grace a l' abstract class mais faudrait sortir les discrnatovalue vers les sous classes
//@Inheritance(strategy =InheritanceType.TABLE_PER_CLASS )
//@Inheritance(strategy =InheritanceType.JOINED)
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperations> accountOperations;
}
