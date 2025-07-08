# 🧠 Smart Legal Document Analyzer & Chatbot

---

## 💼 Project Overview

This Smart Legal Document Analyzer & Chatbot is an intelligent platform that enables users to:

- Upload and analyze legal PDFs  
- Summarize content  
- Ask natural-language questions about the document  
- Detect fraud or suspicious information  
- Use OCR to process scanned images/PDFs with **Azure Computer Vision API**  
- Integrate with **Gemini LLM** for reasoning and content generation  

### Key Highlights

- Integration of OCR and vision APIs for document text extraction and layout understanding  
- Use of advanced large language models for summarization, question answering, and fraud detection  
- Modular and scalable design implemented with Java Spring Boot backend  
- Handling of long documents and complex queries using generative AI  
- Combines vision, language, and reasoning workflows into a cohesive application  

This project showcases the ability to build intelligent document interaction tools leveraging state-of-the-art ML and cloud services.

---

## 🚀 Features

- 📄 **PDF Upload & Parsing**  
- 🧠 **Chat with Documents** (ask questions about the uploaded files)  
- 📝 **Summary Generation** (using Gemini 1.5 Pro)  
- 🛡️ **Fraud Detection in Legal Documents**  
- 🔍 **OCR & Vision API Support** with Azure for scanned/image-based documents  

---

## 🏗️ Tech Stack

| Layer           | Technology                         |
| --------------- | --------------------------------- |
| Backend         | Java, Spring Boot                 |
| LLM Integration | Gemini 1.5 Pro (Google API)       |
| OCR Processing  | Azure Computer Vision API          |
| PDF Parsing     | Apache PDFBox                    |
| Build Tool      | Maven                            |

---

## 📁 Folder Structure

src/
└── main/
    ├── java/
    │   └── com/example/
    │       ├── controller/
    │       ├── service/
    │       ├── config/
    │       └── SmartDocBotApplication.java
    └── resources/
        ├── application.properties
        └── static/
🔧 Setup Instructions
1. Clone the Repository

git clone https://github.com/yourusername/smart-legal-doc-bot.git
cd smart-legal-doc-bot
2. Google Gemini Setup
Go to Google Cloud Console

Enable the Generative Language API

Generate an API Key

Add to application.properties:

gemini.api.key=YOUR_GEMINI_API_KEY
gemini.model.name=models/gemini-1.5-pro
3. Azure OCR & Vision API Setup
Go to Azure Portal

Create a Computer Vision Resource

Get Endpoint URL and Subscription Key

Add to application.properties:

azure.vision.endpoint=https://<your-region>.api.cognitive.microsoft.com/
azure.vision.key=YOUR_AZURE_SUBSCRIPTION_KEY
4. Configure File Upload Limits
Make sure this config class exists to allow large uploads:

@Configuration
public class FileUploadConfig {
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize("50MB");
    factory.setMaxRequestSize("50MB");
    return factory.createMultipartConfig();
  }
}
▶️ Running the App

mvn clean install
mvn spring-boot:run
Backend runs at: http://localhost:8080

📮 API Endpoints (Example for Postman)
Method	Endpoint	Description
POST	/api/upload-pdf	Upload PDF
POST	/api/pdf-summary	Get summary using Gemini
POST	/api/pdf-chat	Ask questions to Gemini about the PDF
POST	/api/pdf-fraud-check	Analyze PDF for fraud or suspicious info
POST	/api/pdf-ocr	Perform OCR on scanned/image PDFs

Request Format for OCR:
file: PDF or Image file
OCR uses Azure Vision to extract text, then passes it to Gemini for processing.

🔁 How It Works
PDF or image is uploaded.

If scanned/image, text is extracted using Azure OCR.

A prompt is constructed using the extracted text.

Prompt is sent to Gemini API.

Gemini generates summary, answers, or fraud analysis.

⚠️ API Usage & Quotas
Gemini API
Free-tier limits may cause 429 RESOURCE_EXHAUSTED errors.
Wait for quota reset or consider upgrading your plan.
Monitor quota at Google Cloud Console.

Azure Vision API
Free tier limits about 20 requests/minute.
Ensure your Azure subscription is active.
