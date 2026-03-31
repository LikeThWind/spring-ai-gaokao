package com.example.modelday1.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CommonConfig {
    @Autowired
    private OpenAiChatModel model;
    @Autowired
    private ChatMemoryStore redisMemoryStore;
    @Autowired
    private EmbeddingModel embeddingModel;
//    @Autowired
//    private RedisEmbeddingStore redisEmbeddingStore;

//    @Bean
//    public ConsultantService chat() {
//        ConsultantService build = AiServices.builder(ConsultantService.class)
//                .chatModel(model)
//                .build();
//        return build;
//    }

    @Bean
    public ChatMemory chat() {
        MessageWindowChatMemory build = MessageWindowChatMemory
                .builder()
                .maxMessages(20)
                .build();
        return build;
    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider provider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object modelId) {
                MessageWindowChatMemory build = MessageWindowChatMemory
                        .builder()
                        .maxMessages(20)
                        .id(modelId)
                        .chatMemoryStore(redisMemoryStore)
                        .build();
                return build;
            }
        };
        return provider;
    }

    //构建向量数据库
    @Bean
    public EmbeddingStore store () {
//        List<Document> documents = ClassPathDocumentLoader.loadDocuments("rag");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:\\java-project\\model-day1\\src\\main\\resources\\rag");
        InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();//内存存储向量数据库
        DocumentSplitter recursive = DocumentSplitters.recursive(300, 100);

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
//                .embeddingStore(redisEmbeddingStore)
                .documentSplitter(recursive)
                .embeddingModel(embeddingModel)
                .build();
        ingestor.ingest(documents);
        return store;
    }

    //构建向量数据库检索对象
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore store) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
//                .embeddingStore(redisEmbeddingStore)
                .minScore(0.5)
                .maxResults(3)
                .embeddingModel(embeddingModel)
                .build();
    }

    // 配置CORS，允许前端跨域访问
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}
