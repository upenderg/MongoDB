package dk.sama.mongodb;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

public class HomeWork31 {
    public static void main(String[] args) throws UnknownHostException {
        //To get a mongo Client
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
    }
}
