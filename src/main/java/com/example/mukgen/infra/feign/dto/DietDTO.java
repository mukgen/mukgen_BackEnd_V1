package com.example.mukgen.infra.feign.dto;

import lombok.*;

import java.util.List;

@Data
public class DietDTO {

    private List<MealServiceDietInfo> mealServiceDietInfo;

    public static class MealServiceDietInfo {
        private DietInfo mealServiceDietInfo;

        public static class DietInfo {
            private List<Row> row;

            public static class Row {
                private String DDISH_NM;
            }
        }
    }
}
