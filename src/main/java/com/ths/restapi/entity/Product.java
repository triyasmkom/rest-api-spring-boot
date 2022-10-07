package com.ths.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@SQLDelete(sql="UPDATE tbl_product SET is_deleted=true WHERE id=?")
// filter yang telah dihapus tidak ditampilkan
//@Where(clause = "is_deleted=false")
// custom filter
@FilterDef(name = "delete_product_filter", parameters = @ParamDef(name = "isDeleted",type = "boolean"))
@Filter(name = "delete_product_filter", condition = "is_deleted=:isDeleted")
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
    //@JsonManagedReference
    private Set<Supplier> suppliers;

    @Column(name = "is_deleted")
    private boolean isDeleted=Boolean.FALSE;

}
