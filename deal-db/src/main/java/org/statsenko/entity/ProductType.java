package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "deal",name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "pt_id_seq", sequenceName = "pt_id_seq", initialValue = 1, allocationSize = 1)
public class ProductType implements Serializable {

    @Column(name = "pt_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pt_id_seq")
    private int id;
    @Column(name = "pt_name")
    private String name;
    @Column(name = "pt_desc")
    private String desc;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @OneToMany(mappedBy = "productType")
    private List<Product> productList = new ArrayList<>();
}
