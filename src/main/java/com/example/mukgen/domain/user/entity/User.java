package com.example.mukgen.domain.user.entity;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.type.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tbl_user")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false, length = 15)
    private String accountId;

    @Column(name = "name", nullable = false, length = 4)
    private String name;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "phone_number", length = 12, nullable = false, columnDefinition = "char(12)")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_party_id")
    private DeliveryParty deliveryParty;

    public void setDeliveryParty(DeliveryParty deliveryParty){

        if(this.deliveryParty != null){

            this.deliveryParty.getUserList().remove(this);
        }
        this.deliveryParty = deliveryParty;
        if(deliveryParty != null){

            deliveryParty.getUserList().add(this);
        }
    }

}
