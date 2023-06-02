package com.example.mukgen.domain.deliveryparty.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE `tbl_delivery_party` SET is_deleted = true WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "tbl_delivery_party")
public class DeliveryParty extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writerAccountId;

    @OneToMany(mappedBy = "deliveryParty")
    private List<User> userList = new ArrayList<>();

    private String menu;

    @Column(name = "participant_number")
    private Integer participantNumber;

    private String place;

    @Column(name = "meet_time")
    private LocalDateTime meetTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Builder
    public DeliveryParty(String menu, Integer participantNumber, String place, LocalDateTime meetTime, User user) {

        this.isDeleted = false;
        this.menu = menu;
        this.participantNumber = participantNumber;
        this.place = place;
        this.meetTime = meetTime;
        this.writerAccountId = user.getAccountId();
    }

    public void joinDeliveryParty(User user) {

        this.userList.add(user);
        user.setDeliveryParty(this);
    }

    public void leaveDeliveryParty(User user) {
        this.userList.remove(user);
        user.setDeliveryPartyLeave();
    }
}
