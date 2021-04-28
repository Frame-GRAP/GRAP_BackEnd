package com.grap.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id; //auto_increment

    //access id 추가해야함

    @Column(length = 30, nullable = false, name = "login_id", unique = true)
    private String loginId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String telephone;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public User(String loginId, String password, String name, String telephone, Timestamp createDate) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.createDate = createDate;
    }
}