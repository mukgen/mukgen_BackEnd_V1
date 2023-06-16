package com.example.mukgen.domain.deliveryparty.entity;

import com.example.mukgen.global.common.entity.BaseTimeEntity;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE `tbl_delivery_party` SET is_deleted = true WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_delivery_party")
public class DeliveryParty extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "writer_account_id", nullable = false, length = 15)
    private String writerAccountId;

    @OneToMany(mappedBy = "deliveryParty")
    private List<User> userList = new ArrayList<>();

    @Column(name = "menu", nullable = false,length = 256)
    private String menu;

    @Min(2) @Max(10)
    @Column(name = "participant_number", nullable = false)
    private Integer participantNumber;

    @Column(name = "place", length = 1000, nullable = false)
    private String place;

    @Column(name = "meet_time", nullable = false)
    private LocalDateTime meetTime;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public DeliveryParty(String menu, Integer participantNumber, String place, LocalDateTime meetTime, User user) {
        this.writerAccountId = user.getAccountId();
        this.menu = menu;
        this.participantNumber = participantNumber;
        this.place = place;
        this.meetTime = meetTime;
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
