package com.example.models;

import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.*;
import com.example.enums.TypeAccount;
import com.example.enums.TypeAcctStatus;
import com.example.enums.TypeCurrency;
import com.example.utils.AccountUtils;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "AccountID", nullable = false, unique = true)
    private String actId;

    @Column(name = "Type", nullable = false)
    private TypeAccount accountType;

    @Column(name = "Status", nullable = false)
    private TypeAcctStatus accountStatus;

    @Embedded
    @Column(nullable = true, name = "Contact #")
    private Telephone telephone;

    @Column(name = "Currency", nullable = false)
    private TypeCurrency currency;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public Account(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Account(TypeAccount actType, TypeAcctStatus actStatus, Telephone phone, TypeCurrency currency){
        this.actId = AccountUtils.accountIdGenerator(11);
        this.accountType = actType;
        this.accountStatus = actStatus;
        this.telephone = phone;
        this.currency = currency;
    }
    public Account(String actId, TypeAccount accountType, TypeAcctStatus accountStatus, 
                  Telephone telephone, TypeCurrency currency, LocalDate createdAt, LocalDate updatedAt) {
        this.actId = actId;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.telephone = telephone;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Account(Account account){
        this.actId = account.actId;
        this.accountType = account.accountType;
        this.accountStatus = account.accountStatus;
        this.telephone = account.telephone;
        this.currency = account.currency;
        this.createdAt = account.createdAt;
        this.updatedAt = account.updatedAt;
    }





    public String getActId() {
        return actId;
    }

    public TypeAccount getAccountType() {
        return accountType;
    }

    public TypeAcctStatus getAccountStatus() {
        return accountStatus;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public TypeCurrency getCurrency() {
        return currency;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }


    public String getCountryCode() {
        return telephone != null ? telephone.getCountryCode() : null;
    }

    public String getAreaCode() {
        return telephone != null ? telephone.getAreaCode() : null;
    }

    public String getNumber() {
        return telephone != null ? telephone.getNumber() : null;
    }

    public String getFormattedTelephone() {
        return telephone != null ? telephone.toString() : null;
    }

    // Business logic getters
    public boolean isActive() {
        return accountStatus == TypeAcctStatus.ACTIVE;
    }

    public boolean isClosed() {
        return accountStatus == TypeAcctStatus.CLOSED;
    }

    public boolean isFrozen() {
        return accountStatus == TypeAcctStatus.SUSPENDED;
    }








}
