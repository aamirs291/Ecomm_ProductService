package com.scaler.ecomm_productservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name="categories")
@NoArgsConstructor
public class Category extends BaseModel implements Serializable {
    @Column(unique=true, nullable=false)
    private String title;

//    @JsonIgnore
//    @JsonBackReference
//    @JsonManagedReference
//    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
//    private List<Product> productList;
}
