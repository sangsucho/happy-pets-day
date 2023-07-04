package com.example.happypetsday.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
public class MainStrollSearchVo {
    Long size;
    Boolean firstBoolean;
    Boolean secondBoolean;
    String addressFirst;
    String addressSecond;

    public MainStrollSearchVo() {
        this.size = 0L;
        this.firstBoolean = true;
        this.secondBoolean = true;
    }
}
