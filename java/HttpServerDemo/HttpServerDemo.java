import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServerDemo {

  public static void main(String[] args) throws IOException {
    // Create an instance of HttpServer bound to port defined by the 
    // PORT environment variable when present, otherwise on 8080.
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

    // Set root URI path.
    server.createContext("/", (var t) -> {
      byte[] response = "Hello World from HttpServerDemo.jar".getBytes();
      t.sendResponseHeaders(200, response.length);
      try (OutputStream os = t.getResponseBody()) {
        os.write(response);
      }
    });

    // Start the server.
    server.start();
    System.out.println("Enter http://localhost:8080 in the phone browser");
  }
}
