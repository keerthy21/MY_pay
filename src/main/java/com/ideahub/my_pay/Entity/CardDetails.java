package com.ideahub.my_pay.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class CardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String  cardHolderName;
    private String CardNumber;
    private String CardType;
    private Date ExpireryDate;
    private String csv;
    private String BankName;
    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public CardDetails() {

    }

    public Integer getId() {
        return id;
    }

    public CardDetails setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public CardDetails setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public CardDetails setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
        return this;
    }

    public String getCardType() {
        return CardType;
    }

    public CardDetails setCardType(String cardType) {
        CardType = cardType;
        return this;
    }

    public Date getExpireryDate() {
        return ExpireryDate;
    }

    public CardDetails setExpireryDate(Date expireryDate) {
        ExpireryDate = expireryDate;
        return this;
    }

    public String getCsv() {
        return csv;
    }

    public CardDetails setCsv(String csv) {
        this.csv = csv;
        return this;
    }

    public String getBankName() {
        return BankName;
    }

    public CardDetails setBankName(String bankName) {
        BankName = bankName;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CardDetails setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public CardDetails(Integer id, String cardHolderName, String cardNumber, String cardType, Date expireryDate, String csv, String bankName, Customer customer) {
        this.id = id;
        this.cardHolderName = cardHolderName;
        CardNumber = cardNumber;
        CardType = cardType;
        ExpireryDate = expireryDate;
        this.csv = csv;
        BankName = bankName;
        this.customer = customer;
    }
}
