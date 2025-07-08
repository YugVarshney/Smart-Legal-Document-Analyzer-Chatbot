package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import com.example.service.AzureOCRService;
import com.example.service.PDFChatService;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private AzureOCRService ocrService;

    @Autowired
    private PDFChatService pdfService;

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("DEBUG: Received image for OCR: " + file.getOriginalFilename());
        String text = ocrService.extractTextFromImage(file);
        System.out.println("DEBUG: Extracted text: " + text);
        return ResponseEntity.ok(text);
    }

    @PostMapping("/upload-pdf")
    public ResponseEntity<String> uploadPDF(@RequestParam("file") MultipartFile file, @RequestParam("query") String query) throws IOException {
        System.out.println("DEBUG: Received PDF for question: " + query);
        String response = pdfService.chatWithPDF(file, query);
        System.out.println("DEBUG: OpenAI chat response: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/pdf-summary")
    public ResponseEntity<String> summarizePDF(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("DEBUG: Received PDF for summary: " + file.getOriginalFilename());
        String summary = pdfService.summarizePDF(file);
        System.out.println("DEBUG: Summary result: " + summary);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/pdf-fraud-check")
    public ResponseEntity<String> fraudCheck(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("DEBUG: Received PDF for fraud check: " + file.getOriginalFilename());
        String analysis = pdfService.fraudCheck(file);
        System.out.println("DEBUG: Fraud check result: " + analysis);
        return ResponseEntity.ok(analysis);
    }
}
