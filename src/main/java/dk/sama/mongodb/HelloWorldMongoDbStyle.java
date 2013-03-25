package dk.sama.mongodb;


import com.mongodb.*;
import org.bson.types.BasicBSONList;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

public class HelloWorldMongoDbStyle {
    public static void main(String[] args) throws UnknownHostException {

        //To get a mongo Client
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("school");
        DBCollection collection = database.getCollection("students");
        final DBCursor dbObjects = collection.find();
        for (DBObject dbObject : dbObjects) {
            System.out.println("dbObject.get() = " + dbObject.get("name"));

            BasicDBList scores = (BasicDBList) dbObject.get("scores");

            System.out.println("Before Deleted....");

            for (Object score : scores) {
                System.out.println("score = " + score);
            }

            Map currentScore;
            Map previousScore = null;
            Map scoreToBeDeleted = null;

            for (Object score : scores) {
                final Map score1 = (Map) score;
                if (score1.get("type").toString().equalsIgnoreCase("homework")) {
                    currentScore = score1;
                    if (previousScore != null && ((Double) currentScore.get("score")).doubleValue() < ((Double) previousScore.get("score")).doubleValue()) {
                        scoreToBeDeleted = currentScore;
                    } else if (previousScore != null) {
                        scoreToBeDeleted = previousScore;
                    }
                    previousScore = currentScore;
                }
            }

            scores.remove(scoreToBeDeleted);

            System.out.println("After Deleted....");

            for (Object score : scores) {
                System.out.println("score = " + score);
            }
            dbObject.put("scores", scores);
            collection.save(dbObject);
        }
    }
}
