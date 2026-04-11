package com.example.models;

import java.time.LocalDate;
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

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "Currency", nullable = false)
    private TypeCurrency currency;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public Account(){
        this.actId = AccountUtils.accountIdGenerator(11);
        this.currency = TypeCurrency.JMD;
        this.accountStatus = TypeAcctStatus.ACTIVE;
        this.accountType = TypeAccount.PERSONAL;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Account(Telephone phone){
        this();
        this.contactInfo = formatPhone(phone);
    }

    private String formatPhone(Telephone phone){
        if(phone == null) return null;

        return "+" + phone.getCountryCode() + "-" +
               phone.getAreaCode() + "-" +
               phone.getExchangeCode() + "-" +
               phone.getSubscriberLine();
    }

    public String getActId() { return actId; }
    public TypeAccount getAccountType() { return accountType; }
    public TypeAcctStatus getAccountStatus() { return accountStatus; }
    public String getContactInfo() { return contactInfo; }
    public TypeCurrency getCurrency() { return currency; }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }

    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }

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