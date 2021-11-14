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
@Table(schema = "deal",name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq", initialValue = 1, allocationSize = 1)
public class Currency implements Serializable {
    @Column(name = "currency_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_seq")
    private int id;
    @Column(name = "cur_name")
    private String name;
    @Column(name = "cur_code")
    private String code;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @OneToMany(mappedBy = "currency")
    private List<Product> productList = new ArrayList<>();


}