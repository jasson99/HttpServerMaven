package newpackage;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.List;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class MyHandler implements HttpHandler {

    public static final Logger logger = Logger.getLogger(HttpHandler.class.getName());
    OutputAuth_Maven obj2 = new OutputAuth_Maven();

    @Override
    public void handle(HttpExchange t) throws IOException {

        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        fa.setFile("mylog.log");
        fa.setLayout(new PatternLayout("%d   %-5p     %m%n"));
        fa.setThreshold(Level.TRACE);
        fa.setAppend(true);
        fa.activateOptions();

        logger.getRootLogger().addAppender(fa);

        logger.log(MyLevel.MYLEVEL, "I am MyLevelTest log");
        

        ObjectMapper mapper = new ObjectMapper();
        Headers headers = t.getRequestHeaders();

        BufferedReader in = new BufferedReader(new InputStreamReader(t.getRequestBody()));
        String inputLine;
        StringBuffer response_2 = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response_2.append(inputLine);
        }
        in.close();

        String string_response = response_2.toString();
        System.out.println(string_response);

        InetAddress client_IP = t.getRemoteAddress().getAddress();
        logger.info(client_IP + "   json received : {" + string_response.replace("\"", " ").replace(" ", "") + "}\r\n\r\n" + "***********************************************************");

        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(string_response);
        JsonNode json_object = mapper.readTree(parser);
        String prettyjsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json_object);
        System.out.println(prettyjsonInString);

        if (json_object.has("Header")) {
            JsonNode n = json_object.get("Header");

            JsonParser header_parser = factory.createParser(n.toString());
            JsonNode header_object = mapper.readTree(header_parser);

            if (header_object.has("de7")) {
                JsonNode de7_valueList = header_object.get("de7");
                obj2.de7 = de7_valueList.toString().replace("\"", "");
            }

            if (header_object.has("de38")) {
                JsonNode de38_valueList = header_object.get("de38");
                obj2.de38 = de38_valueList.toString().replace("\"", "");
            } else {
                obj2.de38 = "000000";
            }

            if (header_object.has("rid")) {
                JsonNode rid_valueList = header_object.get("rid");
                obj2.rid = rid_valueList.asLong();
            }

            if (header_object.has("tid")) {
                JsonNode tid_valueList = header_object.get("tid");
                obj2.tid = tid_valueList.asLong();
            }

            if (header_object.has("cid")) {
                JsonNode mtid_valueList = header_object.get("cid");
                //obj2.mtid = "0100";
                obj2.mtid = mtid_valueList.toString().replace("\"", "");
            }

            if (header_object.has("de48")) {
                JsonNode de48_valueList = header_object.get("de48");
                obj2.de48 = de48_valueList.toString().replace("\"", "");
            } else {
                obj2.de48 = null;
            }

        }
        if (json_object.has("iso_msg")) {
            JsonNode n = json_object.get("iso_msg");

            JsonParser iso_parser = factory.createParser(n.toString());
            JsonNode iso_object = mapper.readTree(iso_parser);

            if (iso_object.has("11")) {
                JsonNode de11_valueList = iso_object.get("11");
                obj2.de11 = de11_valueList.toString().replace("\"", "");
            }

            if (iso_object.has("11")) {
                JsonNode de4_valueList = iso_object.get("4");
                obj2.de4 = de4_valueList.toString().replace("\"", "");
            }

            if (iso_object.has("6")) {
                JsonNode de6_valueList = iso_object.get("6");
                obj2.de6 = de6_valueList.toString().replace("\"", "");
            }

            if (iso_object.has("2")) {
                JsonNode de2_valueList = iso_object.get("2");
                obj2.de2_tok = de2_valueList.toString().replace("\"", "");
            }

        }

        List<String> a = headers.get("content-type");
        if (a.contains("application/json")) {
            String jsonInString = null;

            try {

                // Convert object to JSON string
                jsonInString = mapper.writeValueAsString(obj2);
                // System.out.println(jsonInString);

                // Convert object to JSON string and pretty print
                jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj2);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String response = jsonInString;
            System.out.println(response);

            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            byte[] b = response.getBytes();
            os.write(b);
            logger.info(client_IP + "   json replied : {" + response.replace("\"", " ").replace("\n", " ").replace(" ", "") + "}\r\n\r\n" + "***********************************************************");

        } else {
            String response = "bad request";
            t.sendResponseHeaders(400, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}

