package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "deal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deal implements Serializable {
    @Column(name = "deal_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dealId;
    @Column(name = "deal_date")
    private Date dealDate;
    @Column(name = "deal_sum")
    private int sum;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "promotion")
    private Product promotion;

}
