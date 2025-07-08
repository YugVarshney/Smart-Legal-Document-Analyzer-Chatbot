### 🧠 Smart Legal Document Analyzer \& Chatbot









#### 💼 Project Overview





###### This Smart Legal Document Analyzer \& Chatbot is an intelligent document analysis platform that allows users to:

###### 

###### Upload and analyze legal PDFs

###### 

###### Summarize content

###### 

###### Ask natural-language questions about the document

###### 

###### Detect fraud or suspicious information

###### 

###### Use OCR to process scanned images/PDFs with Azure Computer Vision API

###### 

###### Integrate with Gemini LLM for reasoning and content generation

###### 

###### 

###### The project demonstrates:

###### 

###### Integration of OCR and vision APIs for document text extraction and layout understanding

###### 

###### Use of advanced large language models for summarization, question answering, and fraud detection on legal documents

###### 

###### Modular and scalable design implemented with Java Spring Boot for backend API services

###### 

###### Handling of long documents and complex queries using generative AI

###### 

###### Practical experience combining vision, language, and reasoning workflows into a cohesive application

###### 

###### This project showcases the ability to build intelligent document interaction tools leveraging state-of-the-art ML and cloud services.

###### 





#### \## 🚀 Features



##### \- 📄 \*\*PDF Upload \& Parsing\*\*

##### \- 🧠 \*\*Chat with Documents\*\* (ask questions about the uploaded file)

##### \- 📝 \*\*Summary Generation\*\* (Gemini 1.5 Pro)

##### \- 🛡️ \*\*Fraud Detection in Legal Docs\*\*

##### \- 🔍 \*\*OCR \& Vision API Support\*\* using Azure for scanned/image-based documents



---

#### 

#### \## 🏗️ Tech Stack



##### | Layer           | Tech                             |

##### |----------------|----------------------------------|

##### | Backend         | Java, Spring Boot                |

##### | LLM Integration | Gemini 1.5 Pro (via Google API)  |

##### | OCR Processing  | Azure Computer Vision API        |

##### | PDF Parsing     | Apache PDFBox                    |

##### | Build Tool      | Maven                            |

##### 

##### ---



#### \## 📁 Folder Structure



##### src/

##### └── main/

##### ├── java/

##### │ └── com/example/

##### │ ├── controller/

##### │ ├── service/

##### │ ├── config/

##### │ └── SmartDocBotApplication.java

##### └── resources/

##### ├── application.properties

##### └── static/



---



#### \## 🔧 Setup Instructions



##### \### 1. Clone the Repository



##### 

##### git clone https://github.com/yourusername/smart-legal-doc-bot.git

##### cd smart-legal-doc-bot

##### 2\. Google Gemini Setup

##### Go to Google Cloud Console

##### 

##### Enable the Generative Language API

##### 

##### Generate an API Key

##### 

##### In application.properties, add:



##### gemini.api.key=YOUR\_GEMINI\_API\_KEY

##### gemini.model.name=models/gemini-1.5-pro

##### 3\. Azure OCR \& Vision API Setup

##### Go to Azure Portal

##### 

##### Create a Computer Vision Resource

##### 

##### Get:

##### 

##### Endpoint URL

##### 

##### Subscription Key

##### 

##### Add them to application.properties:

##### 

##### azure.vision.endpoint=https://<your-region>.api.cognitive.microsoft.com/

##### azure.vision.key=YOUR\_AZURE\_SUBSCRIPTION\_KEY

##### 4\. Configure File Upload Limits

##### Ensure this config is present:



##### @Configuration

##### public class FileUploadConfig {

##### &nbsp;   @Bean

##### &nbsp;   public MultipartConfigElement multipartConfigElement() {

##### &nbsp;       MultipartConfigFactory factory = new MultipartConfigFactory();

##### &nbsp;       factory.setMaxFileSize("50MB");

##### &nbsp;       factory.setMaxRequestSize("50MB");

##### &nbsp;       return factory.createMultipartConfig();

##### &nbsp;   }

##### }

#### ▶️ Run the App



##### mvn clean install

##### mvn spring-boot:run

##### Backend runs at: http://localhost:8080

##### 

##### 📮 API Endpoints (for Postman)

##### Method	Endpoint	Description

##### POST	/api/upload-pdf	Upload PDF

##### POST	/api/pdf-summary	Get summary from Gemini

##### POST	/api/pdf-chat	Ask questions to Gemini about the PDF

##### POST	/api/pdf-fraud-check	Analyze PDF for false/fraud content

##### POST	/api/pdf-ocr	Perform OCR on scanned/image-based documents



#### Request Format (For OCR):

##### file: PDF or Image file

##### 

##### OCR will use Azure Vision to extract text, then pass to Gemini



#### 🔁 How It Works

##### PDF or image is uploaded.

##### 

##### If image/scan, text is extracted using Azure OCR.

##### 

##### Prompt is constructed using the text.

##### 

##### Prompt is sent to Gemini API.

##### 

##### Gemini generates summary, answer, or fraud analysis.



#### ⚠️ API Usage \& Quotas

##### Gemini API

##### Free-tier may show:

##### 429 RESOURCE\_EXHAUSTED

##### Wait for quota reset or upgrade plan

##### 

##### Check quota dashboard at https://console.cloud.google.com

##### 

##### Azure Vision API

##### Limited to ~20 requests/min for Free Tier

##### 

##### Ensure subscription is active





