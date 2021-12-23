package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "deal",name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 1, allocationSize = 1)
public class Product extends AbstractEntity {
    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_desc")
    private String desc;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;


    @OneToMany(mappedBy = "product",orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Promotion> promotionList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Deal> dealList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pt_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
