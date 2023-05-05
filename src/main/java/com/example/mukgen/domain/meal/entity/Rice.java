package com.example.mukgen.domain.meal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
@NoArgsConstructor
@Getter
public class Rice {

    @Id
    private int id;
    private String item;


    public Rice(String[] item,int id) {
        this.item = Arrays.toString(item);
        this.id = id;
    }

}