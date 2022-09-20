package com.ths.restapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String name;

    @Column(name = "product_description", length = 500)
    private String description;

    private Double prices;
}
