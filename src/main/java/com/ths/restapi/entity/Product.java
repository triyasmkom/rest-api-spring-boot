package com.ths.restapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    @Column(name = "product_description", length = 500)
    private String description;

    private Double prices;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "tbl_product_suplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<Supplier> suppliers;

}
