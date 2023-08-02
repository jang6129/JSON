package com.example.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("FOOD_CD")
    private String code;

    @JsonProperty("NUTR_CONT3")
    private String protein;

    @JsonProperty("DESC_KOR")
    private String name;

    @JsonProperty("GROUP_NAME")
    private String category;

    @JsonProperty("MAKER_NAME")
    private String maker;
}

