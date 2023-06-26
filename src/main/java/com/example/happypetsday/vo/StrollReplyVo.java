package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class StrollReplyVo {
    private Long StrollReplyNumber;
    private String StrollReplyContent;
    private String StrollReplyRegisterDate;
    private String StrollReplyUpdateDate;
    private Long strollBoardNumber;
    private Long userNumber;
    private String userId;
}
