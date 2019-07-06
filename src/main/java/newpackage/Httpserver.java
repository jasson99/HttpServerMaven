package newpackage;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Httpserver {

    public static void main(String args[]) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

        run();
     
    }

    public static String run() {
        ObjectMapper mapper = new ObjectMapper();
        OutputAuth_Maven obj2 = new OutputAuth_Maven();

        obj2.de11 = "2008";
        obj2.de2_tok = "Jasmine";
        obj2.de38 = "1556";
        obj2.de4 = "1672";
        obj2.de44 = "459845";
        obj2.de48 = "45";
        obj2.de5 = "54657";
        obj2.de54 = "567";
        obj2.de6 = "7565";
        obj2.de7 = "8765";
        obj2.rid = 34567;
        obj2.tid = 7654;
        obj2.mtid = "4567";

        String jsonInString = null;

        try {

            // Convert object to JSON string
            jsonInString = mapper.writeValueAsString(obj2);
            //System.out.println(jsonInString);

            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj2);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (jsonInString);

    }

}
