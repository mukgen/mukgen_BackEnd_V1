package com.example.mukgen.domain.rice.entity;

import com.example.mukgen.global.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "tbl_mukgen_pick")
public class MukgenPick extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = )
    private Long id;

    private int month;

    private int day;

    private RiceType riceType;

    private int riceId;
}
