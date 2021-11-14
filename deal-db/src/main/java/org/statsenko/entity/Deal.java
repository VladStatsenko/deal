package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(schema = "deal",name = "deal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "deal_id_seq", sequenceName = "deal_id_seq", initialValue = 1, allocationSize = 1)
public class Deal extends AbstractEntity {
    @Column(name = "deal_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deal_id_seq")
    private int id;
    @Column(name = "deal_date")
    private Date dealDate;
    @Column(name = "deal_sum")
    private int sum;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

}
