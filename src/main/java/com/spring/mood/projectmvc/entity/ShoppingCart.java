package com.spring.mood.projectmvc.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Double cartTotalPrice;
    private long cartTotalCount;

    @ManyToOne
    @JoinColumn(name = "user_account")
    private Member member;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopItem> items;
}
