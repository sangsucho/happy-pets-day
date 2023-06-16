package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StrollReplyDto {
    private Long StrollReplyNumber;
    private String StrollReplyContent;
    private String StrollReplyRegisterDate;
    private String StrollReplyUpdateDate;
    private Long strollBoardNumber;
    private Long userNumber;
}
