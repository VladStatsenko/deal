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
@Table(schema = "deal",name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "promotion_id_seq", sequenceName = "promotion_id_seq", initialValue = 1, allocationSize = 1)
public class Promotion extends AbstractEntity {
    @Column(name = "promotion_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_id_seq")
    private int id;
    @Column(name = "promotion_name")
    private String name;
    @Column(name = "promotion_desc")
    private String desc;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "promotion",orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Deal> dealList = new ArrayList<>();
}
