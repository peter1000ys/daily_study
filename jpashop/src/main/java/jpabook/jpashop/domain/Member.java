package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;
    // 오더 테이블에 있는 멤버 필읃에 의해서 맵핑
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
