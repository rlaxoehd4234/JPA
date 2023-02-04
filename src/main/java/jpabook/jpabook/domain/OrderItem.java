package jpabook.jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
   @ManyToOne
   @JoinColumn(name ="order_id")
    private Order order;

   @ManyToOne
   @JoinColumn(name = "item_id")
    private Item item;

   private int orderPrice;

   private int count;


}
