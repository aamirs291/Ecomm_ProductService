package com.scaler.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
