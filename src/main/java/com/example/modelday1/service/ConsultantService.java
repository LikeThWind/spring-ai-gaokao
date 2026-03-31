package com.example.modelday1.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "openAiChatModel",//指定模型
//        chatMemory = "chat",
        streamingChatModel = "openAiStreamingChatModel",//
        chatMemoryProvider = "chatMemoryProvider",//配置会话功能提供对象
        contentRetriever = "contentRetriever",//配置向量数据库检索对象
        tools = "reservationTool"
)
public interface ConsultantService {
    //    String chat(String message);
//     @SystemMessage("")
    Flux<String> fluxChat(@MemoryId String memoryId, @UserMessage String message);
}
