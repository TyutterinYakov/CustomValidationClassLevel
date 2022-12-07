package com.example.demo.dto;

import com.example.demo.valid.StartBeforeEndDateValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@StartBeforeEndDateValid
public class BookingDto {

    @FutureOrPresent
    private LocalDateTime start;
    @Future
    private LocalDateTime end;
}
