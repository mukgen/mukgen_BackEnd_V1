package com.example.mukgen.domain.deliveryparty.entity;

import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tbl_delivery_party")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE `tbl_delivery_party` SET is_deleted = true WHERE id = ?")
public class DeliveryParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "deliveryParty", cascade = CascadeType.REMOVE)
    private List<User> userList = new ArrayList<>();

    private String menu;

    @Column(name = "participant_number")
    private Integer participantNumber;

    private String place;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "meet_time")
    private LocalDateTime meetTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Builder
    public DeliveryParty(User user, String menu, Integer participantNumber, String place, LocalDateTime meetTime) {

        this.isDeleted = false;
        this.menu = menu;
        this.participantNumber = participantNumber;
        this.place = place;
        this.meetTime = meetTime;
        this.userList.add(user);
    }

    public void joinDeliveryParty(User user){

        this.userList.add(user);
        user.setDeliveryParty(this);
    }
}
