package com.example.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
// @JsonIgnoreProperties를 통해 Json 데이터 컬럼 중 일부만 파싱할 수 있다.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// 변수명을 JSON 데이터 컬럼 이름과 똑같이 맞추거나,
// @JsonProperty("JSON 데이터 컬럼") 이용해서 다른 변수명으로 직렬화 가능.
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

