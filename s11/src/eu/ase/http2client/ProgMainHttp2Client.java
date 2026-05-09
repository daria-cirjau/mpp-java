package eu.ase.http2client;

import java.net.URI;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ProgMainHttp2Client {

	public static void main(String[] args) throws IOException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            System.out.println(httpClient.version());

            HttpRequest httpRequest =
            		HttpRequest.newBuilder()
            				.uri(new URI("https://www.google.com/"))
            				.GET()
            				.build();

            Map<String, List<String>> headers = httpRequest.headers().map();
            headers.forEach((k, v) -> System.out.println(k + "-" + v));

            HttpResponse<String> httpResponse =
            		httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("HTTP2 response = \n" + httpResponse.body());

            CompletableFuture<HttpResponse<String>> httpResponse2 =
            		httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

            Thread.currentThread().sleep(5000);

            if(httpResponse2.isDone()) {
            	System.out.println("\n\n httpResponse2 = \n");
                System.out.println(httpResponse2.get().statusCode());
                System.out.println(httpResponse2.get().body());
            } else {
            	System.out.println("Response not received!");
                httpResponse2.cancel(true);
            }

        } catch (Exception e) {
            System.out.println("message " + e);
        }
    }

}