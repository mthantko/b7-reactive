package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.dto.MultiplyRequestDto;
import com.example.webfluxdemo.dto.Response;
import com.example.webfluxdemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
public class ReactiveMathController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping(value = "/square/{input}",produces = MediaType.TEXT_EVENT_STREAM_VALUE )
    public Mono<Response> findSquare(@PathVariable int input){
        return reactiveMathService.findSquare(input);
    }
    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return reactiveMathService
                .multiplicationTable(input);
    }

    @PostMapping(value = "/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> requestDtoMono,
                                   @RequestHeader Map<String ,String> headers){
        System.out.println(headers);
        return reactiveMathService.multiply(requestDtoMono);
    }
}
