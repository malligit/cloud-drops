package com.malli.labs.crdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
public class Account {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    String name;
    String type;
    BigDecimal balance;
    Timestamp updated;

public Account(long id,String name,String type,BigDecimal balance,Timestamp updated){
    this.id= id;
    this.name= name;
    this.type=  type;
    this.balance= balance;
    this.updated= updated;

}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
