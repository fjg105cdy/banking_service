package com.yian.banking_service.entities;

import com.yian.banking_service.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

//profile 테이블 (어떤 user의 프로필인가 (항목: 자유 (취미,등등)), one to one
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "PROFILES")
public class Profile extends AbstractEntity {
    private String job;
    private Integer age;
    private String gender;
    private String hobby;
    private String qualification;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
