package dk.sama.mongodb;


import com.mongodb.*;
import freemarker.template.*;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        final MongoClient client = new MongoClient(new ServerAddress("ec2-176-34-166-77.eu-west-1.compute.amazonaws.com", 27017));
        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");
        final ObjectWrapper wrapper = new DefaultObjectWrapper();

        Template helloTemplate = configuration.getTemplate("hello.ftl");
        final StringWriter writer = new StringWriter();
        final DBObject document = collection.findOne();
        helloTemplate.process(document, writer, wrapper);
        System.out.println("wrapper = " + wrapper.toString());
        System.out.println("writer = " + writer.toString());
    }
}
