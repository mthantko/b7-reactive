package com.example.webfluxdemo.controller;


import com.example.webfluxdemo.dto.InputFailedValidationError;
import com.example.webfluxdemo.dto.Response;
import com.example.webfluxdemo.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-math")
@RequiredArgsConstructor
public class ReactiveMathValidationController {

    private final ReactiveMathService reactiveMathService;

    // Traditional Error Handle
    @GetMapping("/square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable  int input) {
        if (input < 10 || input > 20) {
            throw new InputFailedValidationError(input);
        }

        return reactiveMathService.findSquare(input);
    }

    // Reactive Error Handle
    @GetMapping("/square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input) {
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if (integer >= 10 && integer <= 20) {
                        sink.next(integer);
                    }

                    else {
                        sink.error(new InputFailedValidationError(integer));
                    }
                })

                .cast(Integer.class)
                .flatMap(i -> reactiveMathService.findSquare(i));
    }

    // To show bad req without path
    @GetMapping("/square/{input}/assigment")
    public Mono<ResponseEntity<Response>> assigment(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(i -> reactiveMathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
