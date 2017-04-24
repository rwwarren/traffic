// Imports the Google Cloud client library
//import com.google.cloud.language.spi.v1.LanguageServiceClient;
//
//import com.google.cloud.language.v1.Document;
//import com.google.cloud.language.v1.Document.Type;
//import com.google.cloud.language.v1.Sentiment;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;

public class GoogleTest extends Application<GoogleTestConfiguration> {
//    public static void main(String... args) throws Exception {
//        // Instantiates a client
//        LanguageServiceClient language = LanguageServiceClient.create();
//
//        // The text to analyze
//        String text = "SHARTS lol!";
//        Document doc = Document.newBuilder()
//                .setContent(text).setType(Type.PLAIN_TEXT).build();
//
//        // Detects the sentiment of the text
//        Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
//
//        System.out.printf("Text: %s%n", text);
//        System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
//    }

    public static void main(String[] args) throws Exception {
        new GoogleTest().run(args);
    }

    public void run(GoogleTestConfiguration googleTestConfiguration, Environment environment) throws Exception {
        final Client client = new JerseyClientBuilder(environment).build("DemoRESTClient");
        environment.jersey().register(new Why(client));

    }
}