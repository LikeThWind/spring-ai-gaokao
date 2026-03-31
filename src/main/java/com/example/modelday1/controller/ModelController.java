package com.example.modelday1.controller;

import com.example.modelday1.service.ConsultantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ModelController {

    private static final Logger log = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ConsultantService consultantService;

//    @RequestMapping("/chat")
//    public String chat(@RequestParam String message) {
//        String result = model.chat(message);
//        return result;
//    }

//    @RequestMapping("/chat")
//    public String chat(@RequestParam String message) {
//        return consultantService.chat(message);
//    }

    @RequestMapping(value = "/chat", produces = "text/plain;charset=utf-8")
    public Flux<String> chat(String memoryId, String message) {
        log.info("收到聊天请求 - memoryId: {}, message: {}", memoryId, message);
        return consultantService.fluxChat(memoryId, message)
                .doOnNext(chunk -> log.debug("发送数据块: {}", chunk))
                .doOnComplete(() -> log.info("流式响应完成"))
                .doOnError(error -> log.error("聊天过程出错", error));
    }
}
