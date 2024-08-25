package io.yanxia.ddd_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echo")
public class EchoController {

    @GetMapping
    public String echo(@RequestHeader(value = "X-FORWARDED-FOR", required = false) String ip) {
        if (ip == null) {
            ip = "localhost";
        }
        return "Hello World from " + ip;
    }
}
