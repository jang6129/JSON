package com.example.json.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

// JSON 파싱 메소드를 포함한 JsonParser 클래스
@Component
public class JsonParser {
    // String 인풋
    public String postParser(String stringResponse) {
        // String 인풋을 JSONObject 객체로 변환
        JSONObject jsonObject = new JSONObject(stringResponse);
        // getJSONObject("객체 이름") 메소드를 통해 jsonObject 안의 JSONObject를 또 다른 객체로 추출
        JSONObject innerObject = jsonObject.getJSONObject("I2790");
        // DB에 매핑해주기 위해 getJSONArray("배열 이름") 메소드를 통해
        // JSON 배열 형태인 JSONArray로 I2790 객체 안의 JSON 배열(row) 추출
        JSONArray rowArray = innerObject.getJSONArray("row");

        // String 타입으로 변환해 return
        return rowArray.toString();
    }
}
