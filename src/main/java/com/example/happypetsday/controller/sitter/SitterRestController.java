package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.SitterReservationDto;
import com.example.happypetsday.dto.SitterReviewDto;
import com.example.happypetsday.service.sitter.SitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sitters/*")
@RequiredArgsConstructor
public class SitterRestController {
    private final SitterService sitterService;


    @PostMapping("/reserve")
    public RedirectView sitterProfile(HttpServletRequest req, SitterReservationDto sitterReservationDto) {
        sitterReservationDto.setUserNumber((Long) req.getSession().getAttribute("userNumber"));

        sitterService.registerReserve(sitterReservationDto);


        return new RedirectView("/sitter/list");
    }

    @GetMapping("/reservations")
    public List<String> getReservations(Long sitterNumber) {
        List<SitterReservationDto> cantDates = sitterService.findCantDate(sitterNumber);
        List<String> reservationDates = cantDates.stream()
                .map(SitterReservationDto::getReservationDate)
                .collect(Collectors.toList());
        return reservationDates;
    }

    @PostMapping("/sendModifyReview")
    public void sendReview(@RequestBody SitterReviewDto sitterReviewDto){
        sitterService.modifyMyReview(sitterReviewDto);
    }

    @PostMapping("/removeReview")
    public void removeReview(@RequestParam("reviewNumber") Long reviewNumber){
        System.out.println(reviewNumber);
        sitterService.removeReview(reviewNumber);
    }


}
