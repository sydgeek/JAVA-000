package geekbang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequests;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyRequest {

    private static final Log log = LogFactory.getLog(MyRequest.class);

    public static void main(final String... args) {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();

        httpClient.start();

        SimpleHttpRequest request = SimpleHttpRequests.get("http://localhost:8801");

        Future<SimpleHttpResponse> future = httpClient.execute(request, null);

        try {
            SimpleHttpResponse response = future.get();
            log.info("{" + response.getCode() + "}\t\t{" + response.getBodyText() + "}");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
