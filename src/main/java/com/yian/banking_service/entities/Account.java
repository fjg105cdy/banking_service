package com.yian.banking_service.entities;

import com.yian.banking_service.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "ACCOUNTS")
public class Account extends AbstractEntity {

    @Column(nullable=false)
    private String iban;

    private boolean locked;

    @OneToOne
    @JoinColumn(name="user id")
    private User user;
}
