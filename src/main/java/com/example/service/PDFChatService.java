package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class PDFChatService {
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.model.name}")
    private String geminiModelName;

    private String extractTextFromPDF(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        return text;
    }

    public String summarizePDF(MultipartFile file) throws IOException {
        String content = extractTextFromPDF(file);
        System.out.println("DEBUG: Extracted Text for Summary:\n" + content);
        String prompt = "Summarize the following document:\n" + content;
        return callGeminiAPI(prompt);
    }

    public String chatWithPDF(MultipartFile file, String query) throws IOException {
        String content = extractTextFromPDF(file);
        System.out.println("DEBUG: Extracted Text for Chat:\n" + content);
        String prompt = "Document:\n" + content + "\n\nQuestion: " + query;
        return callGeminiAPI(prompt);
    }

    public String fraudCheck(MultipartFile file) throws IOException {
        String content = extractTextFromPDF(file);
        System.out.println("DEBUG: Extracted Text for Fraud Check:\n" + content);
        String prompt = "Analyze the following document. Is there any suspicious, fraudulent, or false information?\n" + content;
        return callGeminiAPI(prompt);
    }
private String sendRequestToGemini(String prompt) throws IOException {
    URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro:generateContent?key=" + geminiApiKey);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "application/json");

    String jsonBody = """
    {
      "contents": [
        {
          "parts": [
            { "text": "%s" }
          ]
        }
      ]
    }
    """.formatted(prompt.replace("\"", "\\\""));

    try (OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonBody.getBytes("utf-8");
        os.write(input, 0, input.length);
    }

    int status = conn.getResponseCode();

    if (status >= 400) {
        Scanner errorScanner = new Scanner(conn.getErrorStream(), "utf-8");
        StringBuilder errorResponse = new StringBuilder();
        while (errorScanner.hasNextLine()) {
            errorResponse.append(errorScanner.nextLine());
        }
        throw new IOException("Gemini API error: " + errorResponse);
    }

    StringBuilder response = new StringBuilder();
    try (Scanner scanner = new Scanner(conn.getInputStream(), "utf-8")) {
        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }
    }

    return response.toString();
}

private String callGeminiAPI(String prompt) throws IOException {
    int maxRetries = 3;
    int attempt = 0;

    while (attempt < maxRetries) {
        try {
            return sendRequestToGemini(prompt);
        } catch (IOException e) {
            String message = e.getMessage();
            if (message != null && message.contains("\"code\":429")) {
                long retryDelayMs = 32000; // default retry delay

                try {
                    Thread.sleep(retryDelayMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry wait", ie);
                }

                attempt++;
            } else {
                throw e;
            }
        }
    }

    throw new IOException("Exceeded max retries due to quota limits");
}


}