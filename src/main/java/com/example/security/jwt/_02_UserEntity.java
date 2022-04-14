package com.example.security.jwt;


import com.example.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

//Register icin kullanacagım
@Getter
@Setter

@Entity
@Table(name = "user_entity")
public class _02_UserEntity extends BaseEntity {

    private String userName;

    @JsonIgnore
    private String password;

    @Column(name = "system_auto_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

}
