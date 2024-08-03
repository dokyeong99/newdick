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

