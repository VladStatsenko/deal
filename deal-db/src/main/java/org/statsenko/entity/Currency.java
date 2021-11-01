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
@Table(schema = "deal",name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {
    @Column(name = "currency_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cur_name")
    private String name;
    @Column(name = "cur_code")
    private String code;

    @OneToMany(mappedBy = "currency")
    private List<Product> productList = new ArrayList<>();


}