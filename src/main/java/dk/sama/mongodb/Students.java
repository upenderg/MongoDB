package dk.sama.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;

public class Students implements Serializable {
    private ObjectId _id;
    private String name;
    private ArrayList<Scores> scores= new ArrayList<Scores>();

    public Students(ObjectId _id, String name, ArrayList<Scores> scores) {
        this._id = _id;
        this.name = name;
        this.scores = scores;
    }


    public Students() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Scores> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Scores> scores) {
        this.scores = scores;
    }


    public void makePojoFromBson( DBObject bson )
    {
        BasicDBObject b = ( BasicDBObject ) bson;
        this._id    = ( ObjectId ) b.get( "_id" );
        this.name   = ( String )  b.get( "name" );
        this.scores = ( ArrayList )  b.get( "scores" );
    }

    private class Scores {
        String type;
        double score;

        Scores(String type, double score) {
            this.type = type;
            this.score = score;
        }

    }
}
