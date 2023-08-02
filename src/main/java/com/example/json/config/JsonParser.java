package com.example.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {
    public String postParser(String stringResponse) {
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject innerObject = jsonObject.getJSONObject("I2790");
        JSONArray rowArray = innerObject.getJSONArray("row");

        return rowArray.toString();
    }
}
