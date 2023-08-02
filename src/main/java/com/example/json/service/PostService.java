package com.example.json.service;

import com.example.json.config.JsonParser;
import com.example.json.dto.PostResponse;
import com.example.json.entity.Post;
import com.example.json.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final WebClient webClient;
    private final JsonParser jsonParser;

    public PostService(PostRepository postRepository, JsonParser jsonParser) {
        this.postRepository = postRepository;
        this.webClient = WebClient.create();
        this.jsonParser = jsonParser;
    }

    public void fetchAndSavePosts() throws JsonProcessingException {
        String rawResponse = webClient.get()
                .uri("http://openapi.foodsafetykorea.go.kr/api/571a8b7bdd7b40da9155/I2790/json/1/30/MAKER_NAME=(주)농심")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String stringResponse = jsonParser.postParser(rawResponse);
        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts = Arrays.asList(mapper.readValue(stringResponse, Post[].class));

        postRepository.saveAll(posts);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
