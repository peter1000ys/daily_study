package jpabook.jpashop.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long Id;

    // 얘의 값이 변경 되었을 때
    // Foreign Key가 바뀌길 바라는 것이
    // 연관 관계의 주인
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 1 대 1 관계에서 FK는 원하는데 넣어도 된다
    // 주로 액세스 하는 곳에 둔다
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private orderStatus status; // 주문 상세 [ORDER, CANCEL]
}
