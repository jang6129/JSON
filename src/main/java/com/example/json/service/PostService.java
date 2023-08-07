package com.example.json.service;

import com.example.json.config.JsonParser;
import com.example.json.entity.Post;
import com.example.json.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final WebClient webClient;
    private final JsonParser jsonParser;

    // 의존성 생성자 주입
    public PostService(PostRepository postRepository, JsonParser jsonParser) {
        this.postRepository = postRepository;
        this.webClient = WebClient.create();
        this.jsonParser = jsonParser;
    }

    // domain : http://openapi.foodsafetykorea.go.kr/api/
    // key : 571a8b7bdd7b40da9155
    // data type : json
    // starting page : 1
    // ending page : 30
    // option : MAKER_NAME=(주)농심
    public void fetchAndSavePosts() throws JsonProcessingException {
        // webClient를 이용해 Json raw data를 String 객체로 반환 받는다.
        String rawResponse = webClient.get()
                // 31 - 36 라인의 주석을 참고해 uri 작성
                .uri("http://openapi.foodsafetykorea.go.kr/api/571a8b7bdd7b40da9155/I2790/json/1/30/MAKER_NAME=(주)농심")
                // API 요청
                .retrieve()
                // String 객체로 변환
                .bodyToMono(String.class)
                // return
                .block();

        // ObjectMapper를 이용해 매핑하기 위해 적절한 포맷으로 변환해준다.
        String stringResponse = jsonParser.postParser(rawResponse);
        // ObjectMapper 객체 생성
        ObjectMapper mapper = new ObjectMapper();
        // ObjectMapper를 통해 String -> Post 배열로 변환
        // Arrays.asList()를 통해 Post 배열 -> Post List로 변환
        List<Post> posts = Arrays.asList(mapper.readValue(stringResponse, Post[].class));

        // List<Post>를 MySQL에 저장
        postRepository.saveAll(posts);
    }

    // DB에서 모든 row를 반환한다.
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
