package com.newnomal.newdick.service;

import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OpenAIApiService {

    private String openAiApiKey="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";


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

