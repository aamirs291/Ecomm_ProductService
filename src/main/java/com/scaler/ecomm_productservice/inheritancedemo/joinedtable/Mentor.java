package com.scaler.ecomm_productservice.inheritancedemo.joinedtable;

import com.scaler.ecomm_productservice.inheritancedemo.joinedtable.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_mentors")
@PrimaryKeyJoinColumn(name="mentor_id")
public class Mentor extends User {
    private String companyName;
}
