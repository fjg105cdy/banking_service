package com.yian.banking_service.entities;

import com.yian.banking_service.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "USERS")
public class User extends AbstractEntity {


    @Column (nullable=false)
    private String username;

    @Column (unique=true,nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @OneToOne(mappedBy="user")
    private Address address;

    @OneToOne
    private Account account;

    @OneToMany(mappedBy="user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy="user")
    private List<Contact> contacts;



}
