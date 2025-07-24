package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name="products")
@NoArgsConstructor
public class Product extends BaseModel implements Serializable{

//    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER, cascade = jakarta.persistence.CascadeType.MERGE)
    @JoinColumn
//    @JsonManagedReference
//    @JsonBackReference
    private Category category;

}
