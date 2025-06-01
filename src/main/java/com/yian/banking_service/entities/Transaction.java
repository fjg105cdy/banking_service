package com.yian.banking_service.entities;

import com.yian.banking_service.common.AbstractEntity;
import com.yian.banking_service.common.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="TRANSACTIONS")

public class Transaction extends AbstractEntity {

    private String description;
    private BigDecimal amount;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String destinationIban;
    private String sourceIban;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
