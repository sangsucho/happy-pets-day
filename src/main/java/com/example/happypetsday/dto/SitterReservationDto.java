package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterReservationDto {
    private Long reservationNumber;
    private Long sitterNumber;
    private Long userNumber;
    private String reservationDate;
    private String reservationStatus;
}
