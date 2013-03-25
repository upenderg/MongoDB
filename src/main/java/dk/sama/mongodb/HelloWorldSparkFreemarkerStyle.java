package dk.sama.mongodb;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.net.UnknownHostException;

import static spark.Spark.get;

public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");


        final MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");

        final ObjectWrapper wrapper = new DefaultObjectWrapper();

        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    final DBObject document = collection.findOne();
                    helloTemplate.process(document, writer,wrapper);
                }
                catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                System.out.println("wrapper = " + wrapper.toString());
                return writer;
            }
        });




    }

}
