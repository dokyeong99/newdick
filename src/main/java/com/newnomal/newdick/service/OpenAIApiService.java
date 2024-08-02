package com.newnomal.newdick.service;

import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OpenAIApiService {

    private String openAiApiKey="sk-proj-rpazBnQJWfjRoP5TOTZpT3BlbkFJRpI7NE08NHAo1ujGW1AW";


    public List<Double>  generateCareerDescriptionVector(String careerDescription) {
        OpenAiService service = new OpenAiService(openAiApiKey);
        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model("text-embedding-ada-002")
                .input(Collections.singletonList(careerDescription))
                .build();


        return service.createEmbeddings(embeddingRequest)
                .getData().get(0).getEmbedding();
    }
}

