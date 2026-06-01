package eu.ase.nosql;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBClient {
    public static void main(String[] args) {
        try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase db = mongoClient.getDatabase("test");
            System.out.println("Connect to db successfully");

            if(db.getCollection("mycol") != null) {
                db.getCollection("mycol").drop();
            }
            db.createCollection("mycol");
            System.out.println("Collection created successfully");
            MongoCollection<Document> coll = db.getCollection("mycol");

            Document doc = new Document("title", "MongoDB")
                    .append("description", "database")
                    .append("likes", 100)
                    .append("url", "http://www.tutorialspoint.com/mongodb/")
                    .append("by", "tutorials point");

            coll.insertOne(doc);
            System.out.printf("Doc inserted successfully");

            FindIterable<Document> iterable = coll.find();
            MongoCursor<Document> cursor = iterable.iterator();

            System.out.println();
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            cursor.close();
        }
    }
}
