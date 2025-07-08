// import java.io.IOException;
// import org.apache.http.HttpEntity;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.entity.mime.MultipartEntityBuilder;
// import org.apache.http.entity.ContentType;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;
// import org.springframework.web.multipart.MultipartFile;

// public class LangChainUtils {

//     private final String baseUrl;

//     public LangChainUtils(String baseUrl) {
//         this.baseUrl = baseUrl;  // e.g. "http://localhost:8000"
//     }

//     public String chatWithPDF(MultipartFile file, String query) throws IOException {
//         return postFileWithQuery("/chat-with-pdf/", file, query);
//     }

//     public String summarizePDF(MultipartFile file) throws IOException {
//         return postFile("/pdf-summary/", file);
//     }

//     public String fraudCheckPDF(MultipartFile file) throws IOException {
//         return postFile("/pdf-fraud-check/", file);
//     }

//     private String postFileWithQuery(String path, MultipartFile file, String query) throws IOException {
//         MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//         builder.addBinaryBody("file", file.getInputStream(), ContentType.APPLICATION_PDF, file.getOriginalFilename());
//         builder.addTextBody("query", query);
//         return executePost(baseUrl + path, builder.build());
//     }

//     private String postFile(String path, MultipartFile file) throws IOException {
//         MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//         builder.addBinaryBody("file", file.getInputStream(), ContentType.APPLICATION_PDF, file.getOriginalFilename());
//         return executePost(baseUrl + path, builder.build());
//     }

//     private String executePost(String url, HttpEntity entity) throws IOException {
//         HttpPost post = new HttpPost(url);
//         post.setEntity(entity);
//         try (CloseableHttpClient client = HttpClients.createDefault();
//              CloseableHttpResponse response = client.execute(post)) {
//             return EntityUtils.toString(response.getEntity());
//         }
//     }
// }
