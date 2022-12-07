package com.example.demo;

import com.example.demo.controller.BookingController;
import com.example.demo.dto.BookingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
@Slf4j
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateBookingStartEqualsEnd() throws Exception {
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        BookingDto bookingDto = new BookingDto(
                date,
                date);

        String body = objectMapper.writeValueAsString(bookingDto);
        log.info(body);
        mockMvc.perform(post("/bookings")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testCreateBookingStartAfterEnd() throws Exception {
        BookingDto bookingDto = new BookingDto(
                LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(2));

        mockMvc.perform(post("/bookings")
                        .content(objectMapper.writeValueAsString(bookingDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testCreateBookingStartEndIsNull() throws Exception {
        BookingDto bookingDto = new BookingDto(
                null,
                null);

        mockMvc.perform(post("/bookings")
                        .content(objectMapper.writeValueAsString(bookingDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
