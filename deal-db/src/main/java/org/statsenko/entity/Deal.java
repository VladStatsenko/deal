package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "deal",name = "deal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deal extends AbstractEntity {
    @Column(name = "deal_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "deal_date")
    private Date dealDate;
    @Column(name = "deal_sum")
    private int sum;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

}
