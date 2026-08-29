package org.shuai.boot_mongodb_study_code.mongodb;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBJdbc {

  private static final String DB_URI =
      "mongodb://root:123456@192.168.211.166:27017/zsh_db?authSource=admin";
  private static final String DB_NAME = "zsh_db";
  private static final String COLLECTION_NAME = "my_data";

  public static void main(String[] args) {
    // Replace the placeholder with your MongoDB deployment's connection string
    String uri = DB_URI;
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase(DB_NAME);
      MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
      Document doc = collection.find(eq("name", "Alice")).first();
      if (doc != null) {
        System.out.println(doc.toJson());
      } else {
        System.out.println("No matching documents found.");
      }
    }
  }
}
