package org.shuai.boot_mongodb_study_code.mongodb;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** MongoUtils */
public class MongoUtils {
  private static final String DB_URI =
      "mongodb://root:123456@192.168.211.166:27017/zsh_db?authSource=admin";
  private static final String DB_NAME = "zsh_db";
  private static final String COLLECTION_NAME = "my_data_02";
  private static MongoClient mongoClient;

  @BeforeAll
  public static void init() {
    // 通过连接认证获取MongoDB连接
    mongoClient = MongoClients.create(DB_URI);
  }

  @Test
  public void getMongoConnectionByAddress() {
    try {
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      MongoCollection<Document> col = mongoDatabase.getCollection(COLLECTION_NAME);
      FindIterable<Document> documents = col.find();
      System.out.println("col documents : " + documents);

    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  /** 创建集合 */
  @Test
  public void createCollection() {
    try {
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      mongoDatabase.createCollection(COLLECTION_NAME);
      System.out.println(COLLECTION_NAME + "集合创建成功");
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  /** 获取集合 */
  @Test
  public void getCollection() {
    try {
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
      System.out.println("集合" + COLLECTION_NAME + "选择成功，collection：" + collection);
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  /** 批量插入文档 */
  @Test
  public void insertMany() {
    try {
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
      System.out.println("集合" + COLLECTION_NAME + "选择成功，collection：" + collection);

      // 插入文档
      // 1. 创建文档 org.bson.Document 参数为key-value的格式
      // 2. 创建文档集合List<Document>
      // 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用
      // mongoCollection.insertOne(Document)
      Document document_01 =
          new Document("title", "hobby")
              .append("description", "爱好")
              .append("size", 100)
              .append("sport", "Fly");
      Document document_02 =
          new Document("title", "technology")
              .append("description", "技术")
              .append("size", 100)
              .append("tch", "http");
      List<Document> documents = new ArrayList<>();
      documents.add(document_01);
      documents.add(document_02);
      collection.insertMany(documents);
      System.out.println("文档插入成功");
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  /** 检索所有文档 */
  @Test
  public void find() {
    try {
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
      System.out.println("集合" + COLLECTION_NAME + "选择成功，collection：" + collection);

      // 检索所有文档
      // 1.获取迭代器FindIterable<Document>
      // 2.获取游标MongoCursor<Document>
      // 3.通过游标遍历检索出的文档集合
      FindIterable<Document> findIterable = collection.find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      System.out.println("获取游标成功，mongoCursor：" + mongoCursor);
      while (mongoCursor.hasNext()) {
        System.out.println(mongoCursor.next());
      }
      System.out.println("检索所有文档完成");
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  /** 更新文档 */
  @Test
  public void updateMany() {
    try {
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
      System.out.println("集合" + COLLECTION_NAME + "选择成功，collection：" + collection);

      // 更新文档   将文档中likes=100的文档修改为likes=200
      collection.updateMany(
          Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
      // 检索查看结果
      FindIterable<Document> findIterable = collection.find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      System.out.println("获取游标成功，mongoCursor：" + mongoCursor);
      while (mongoCursor.hasNext()) {
        System.out.println(mongoCursor.next());
      }
      System.out.println("更新文档完成");
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  /** 删除文档 */
  @Test
  public void findOneRemove() {
    try {
      // 连接到数据库
      MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
      System.out.println("Connect to database successfully");
      MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION_NAME);
      System.out.println("集合" + COLLECTION_NAME + "选择成功，collection：" + collection);

      // 删除符合条件的第一个文档
      collection.deleteOne(Filters.eq("likes", 200));
      // 删除所有符合条件的文档
      collection.deleteMany(Filters.eq("likes", 200));
      // 检索查看结果
      FindIterable<Document> findIterable = collection.find();
      MongoCursor<Document> mongoCursor = findIterable.iterator();
      System.out.println("获取游标成功，mongoCursor：" + mongoCursor);
      while (mongoCursor.hasNext()) {
        System.out.println(mongoCursor.next());
      }
      System.out.println("删除文档完成");
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}
