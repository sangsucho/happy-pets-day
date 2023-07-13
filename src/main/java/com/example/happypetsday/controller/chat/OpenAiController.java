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
                "우리사이트에 기능이나 서비스에 대한 질문이 들어오면 이전 질문을 토대로 해당 서비스를" +
                "바로 이용할 수 있게" +
                "1. <a href=\"/stroll/list\" style=\"font-weight: bolder; font-size: 20px; color: #68a5fe;\">산책메이트 구하기</a>\n" +
                "2. <a href=\"/sitter/list\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">펫시터 예약</a>\n" +
                "3. <a href=\"/adopt/list\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">반려동물 입양 정보</a>\n" +
                "4. <a href=\"/recommend/main\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">반려동물 추천</a>" +
                "이런식으로 a태그 안에 위에 알려준 html태그 그대로 서비스명과 url을 담아서 알려줘" +
                "만약 답변하기 어렵운 질문이 오거나 " +
                "사용자가 불평을 하면 위로해주고 111-111-1111 우리 회사번호를 알려주고 여기로 연락을 달라고 해주면 돼" +
                "답변은 최대한 간단하게 해줘");

        list.add(0,system);
        AiChatVo user = new AiChatVo();
        user.setRole("user");
        user.setContent("이 사이트는 어떤 기능이나 서비스 있어?");
        list.add(1,user);
        AiChatVo assistant = new AiChatVo();
        assistant.setRole("assistant");
        assistant.setContent("Happy Pet's Day 웹 사이트에는 다음과 같은 서비스들이 있습니다\n" +
                "1. <a href=\"/stroll/list\" style=\"font-weight: bolder; font-size: 20px; color: #68a5fe;\">산책메이트 구하기</a> : 반려동물과 함께 산책할 파트너를 찾을 수 있는 서비스입니다.\n" +
                "2. <a href=\"/sitter/list\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">펫시터 예약</a>: 여행이나 일상 생활 중에 반려동물을 맡길 수 있는 펫시터를 예약할 수 있는 서비스입니다.\n" +
                "3. <a href=\"/adopt/list\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">반려동물 입양 정보</a>: 입양하고 싶은 반려동물에 대한 정보와 입양 신청을 할 수 있는 서비스입니다.\n" +
                "4. <a href=\"/recommend/main\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">반려동물 추천</a>: 자신의 생활 방식과 성향에 맞는 반려동물을 추천받을 수 있는 서비스입니다.\n" +
                "5. 기타 궁금한 사항이나 문의사항에 대해서는 고객센터를 통해 문의하실 수 있습니다.\n" +
                "원하시는 서비스를 이용하시려면 해당 서비스의 링크를 클릭하시면 됩니다.");
        list.add(2,assistant);

        System.out.println("==================");
        System.out.println(list);
        System.out.println("==================");

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























