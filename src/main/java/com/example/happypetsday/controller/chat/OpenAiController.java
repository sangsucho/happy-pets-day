package com.example.happypetsday.controller.chat;

import com.example.happypetsday.vo.AiChatVo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/chatBot/*")
public class OpenAiController {
    @Value("${gpt.api}")
    private String apiKey;
    private String endPoint = "https://api.openai.com/v1/chat/completions";
    @PostMapping("/question")
    public Mono<Map> question(@RequestBody List<AiChatVo> list ){

        AiChatVo system = new AiChatVo();
        system.setRole("system");
        system.setContent("나는 지금 openApi테스트를 하고 있어 너는 간단하고 짧은 아무 답변만 줘 " +
                "어떤 대답도 상관없지만 내가 보낸 메세지와 구분되게만 답변해줘");
        list.add(0,system);
        Gson gson = new Gson();

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("model", "gpt-3.5-turbo");
        reqBody.put("messages", gson.toJson(list));
        reqBody.put("temperature", 0.8);
        reqBody.put("max_tokens", 1000);

        WebClient webClient = WebClient.builder()
                .baseUrl(endPoint) //요청을 보낼 url
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //header설정
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();

        Mono<Map> resultBody = webClient.post() //요청 방식을 설정한다.
                .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입
                .body(BodyInserters.fromValue(reqBody)) //body를 설정한다. fromValue로 데이터를 담는다.
                .retrieve() //위에서 만든 요청을 보낸다.
                .bodyToMono(Map.class); //응답의 body를 받는다. Mono<Map>으로 받는다.


        return resultBody;
    }


}























