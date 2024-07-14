package my.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import my.order_service.entity.Address;
//import my.order_service.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String username;
    private String password;
    private String phone;

//    @Embedded
//    private Address address;

    private String role;
//
//    @OneToMany(mappedBy = "users")
//    private List<Order> orders = new ArrayList<>();
}
