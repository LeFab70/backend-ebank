package gp2.ebaking.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SAC")//SAC=saving account pour la strategy single table
@Data
@AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends  BankAccount {
    private double interestRate;
}
