package com.example.mukgen.domain.rice.entity;

import com.example.mukgen.global.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "tbl_mukgen_pick")
public class MukgenPick extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "day", nullable = false)
    private int day;

    @Column(name = "rice_type", nullable = false)
    private RiceType riceType;

    @Column(name = "rice_id", nullable = false)
    private int riceId;
}
