package com.scaler.ecomm_productservice.inheritancedemo.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Internal;

@Getter
@Setter
@Entity(name = "jt_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
