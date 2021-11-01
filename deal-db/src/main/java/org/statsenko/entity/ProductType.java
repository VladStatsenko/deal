package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "deal",name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {

    @Column(name = "pt_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pt_name")
    private String name;
    @Column(name = "pt_desc")
    private String desc;

    @OneToMany(mappedBy = "productType")
    private List<Product> productList = new ArrayList<>();
}
