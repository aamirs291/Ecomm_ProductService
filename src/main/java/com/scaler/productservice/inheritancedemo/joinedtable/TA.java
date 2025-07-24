package com.scaler.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_tas")
@PrimaryKeyJoinColumn(name="id")
public class TA extends User {
    private int numberOfHRs;
}