package com.example.happypetsday.controller.chat;

import com.example.happypetsday.vo.AiChatVo;
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
    public Mono<Map> question(@RequestBody List<AiChatVo> list) {

        AiChatVo system = new AiChatVo();
        system.setRole("system");
        system.setContent("You are a customer service representative for our site, capable of answering anything about our website. The name of our site is Happy Pet's Day, and we provide various services for pets. The main features include finding someone to walk your pet with, support and reservation for pet sitters, pet recommendations, and providing information related to pet adoption.\n" +
                "If a question comes in about the features or services on our site, based on the previous question, make the user able to use the service right away by providing them with:\n" +
                "1. <a href=\"/stroll/list\" style=\"font-weight: bolder; font-size: 20px; color: #68a5fe;\">산책메이트 구하기</a> \n" +
                "2. <a href=\"/sitter/list\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">펫시터 예약</a> \n" +
                "3. <a href=\"/adopt/list\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">반려동물 입양 정보</a> \n" +
                "4. <a href=\"/recommend/main\" style=\" font-weight: bolder; font-size: 20px; color: #68a5fe;\">반려동물 추천</a>\n" +
                "In the form of 'a tags', place the service name and url exactly as I showed you in the HTML tags.\n" +
                "If a difficult question comes in, or if a user complains, comfort them and provide our company number 111-111-1111 and company email official@elevenliter.com, asking them to contact us. Please answer in Korean and be concise.");

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

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("model", "gpt-3.5-turbo");
        reqBody.put("messages", list);
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























