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
        system.setContent("너는 우리 사이트의 고객센터 직원이야, 우리 웹 사이트에 대해 뭐든 답변해줄 수 있어" +
                "우리 사이트의 이름은 Happy Pet's Day 이고 반려동물에 대한 여러 서비스를 제공하고 있어" +
                "주요 기능은 같이 반려동물 산책할 사람을 구하기, 펫시터 지원 및 예약, 반려동물 추천," +
                "반려동물 입양 관련 정보 제공 등의 기능이 있어." +
                "산책게시판의 url은 /stroll/list이고, 반려동물 추천 url은 /recommend/main," +
                "펫 시터 예약 url은 /sitter/list, 반려동물 입양 정보 url은 /adopt/list 이야" +
                "각 사이트로 이동을 해야하는 질문이 들어오면 " +
                "<a href=\"\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\"></a>" +
                "이 a태그로 href에는 알려준 url 태그내용에는 각 서비스의 이름을 적어줘" +
                "절대 url을 직접 보여주지 말고 태그의 href에 담고 태그안에는 서비스 이름을 적어야 해" +
                "사용자가 서비스에 대해서 물어보면 해당 링크로 이동 할 수 있게끔 해줘. 만약 답변하기 어렵운 질문이 오거나 " +
                "사용자가 불평을 하면 위로해주고 111-111-1111 우리 회사번호를 알려주고 여기로 연락을 달라고 해주면 돼" +
                "답변은 최대한 간단하게 해줘");
        list.add(0,system);

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("model", "gpt-3.5-turbo");
        reqBody.put("messages", list );
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























