package com.spring.sample.chatbot.Rag;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class VectorStoreService {
    private final PDFLoader pdfLoader;
    private final TokenTextSplitter textSplitter;
    @Getter
    private final VectorStore vectorStore;

    @Value("${app.documents.travel-policy.file.path}")
    private String travelPolicyFilePath;

    @Value("${app.documents.spring-QandAns.file.path}")
    private String springQAndAns;

    public VectorStoreService(PDFLoader pdfLoader, VectorStore vectorStore) {
        this.pdfLoader = pdfLoader;
        this.vectorStore = vectorStore;
        this.textSplitter = new TokenTextSplitter();
    }

    @PostConstruct
    public void initialize() throws IOException {
        String pdfText = pdfLoader.loadPDF(travelPolicyFilePath);

        List<Document> documents = textSplitter.split(new Document(pdfText));
        vectorStore.add(documents);

        // Scan all the word documents.
        String springDoc = pdfLoader.loadWord(springQAndAns);
        List<Document> springdocuments = textSplitter.split(new Document(springDoc));
        vectorStore.add(springdocuments);

        log.info("✅ Vector store initialized with PDF and word content content.");
    }

}
