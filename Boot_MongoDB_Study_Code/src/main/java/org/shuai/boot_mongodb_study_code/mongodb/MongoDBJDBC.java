package org.shuai.boot_mongodb_study_code.mongodb;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBJDBC {
  public static void main(String[] args) {
    // Replace the placeholder with your MongoDB deployment's connection string
    String uri = "mongodb://root:123456@192.168.211.166:27017/zsh_db?authSource=admin";
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase("zsh_db");
      MongoCollection<Document> collection = database.getCollection("my_data");
      Document doc = collection.find(eq("name", "Alice")).first();
      if (doc != null) {
        System.out.println(doc.toJson());
      } else {
        System.out.println("No matching documents found.");
      }
    }
  }
}
