package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_desc")
    private String desc;


    @OneToMany(mappedBy = "product")
    private List<Promotion> promotionList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Currency> currencyList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pt_id")
    private ProductType productType;
}
