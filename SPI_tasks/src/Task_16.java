import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Task_16 {
    public static void main(String[] args) throws IOException, InterruptedException {

        if(args.length<1){
            System.out.println("Provide URL!");
            System.exit(0);
        }

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URL(args[0]).toURI())
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            System.out.println("Wrong URL!");
            throw new RuntimeException(e);
        }

        HttpResponse<Stream<String>> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofLines());
        printer(response.body());
    }

    public static void printer(Stream<String> lines) {
        AtomicInteger i = new AtomicInteger(0);
        lines.forEach(l -> {
            i.getAndIncrement();
            System.out.println(l);
            if(i.get() % 25 == 0) {
                System.out.println("Press space to scroll down..");
                while (true){
                    try {
                        if (!(System.in.read() != ' ')) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
