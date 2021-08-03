import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;

public class Main {

    public static String file = "/Users/Nata/IdeaProjects/java_basics/18_NoSQL/MongoTask_1/src/main/resources/mongo.csv";

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("test");

        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("Students");
        // Удалим из нее все документы
        collection.drop();

        Students students = new Students();
        students.initByFile(file);

        for (Student student : students.getList()) {

            Document document = new Document()
                    .append("name", student.getName())
                    .append("age", student.getAge())
                    .append("courses", student.getCoursesList());

           collection.insertOne(document);
        }

        System.out.println("Общее количество студентов в базе: "+ collection.countDocuments());

        BsonDocument query_older_40 = BsonDocument.parse("{age: {$gt: 40}}");
        System.out.println("Количество студентов старше 40 лет: "+ collection.countDocuments(query_older_40));

        System.out.print("Имя самого молодого студента: ");
        BsonDocument query_youngest = BsonDocument.parse("{age: 1}");
        collection.find().sort(query_youngest).limit(1).forEach((Consumer<Document>) document -> {
            System.out.println(""+ document.get("name"));
        });

        System.out.print("Список курсов самого старого студента: ");
        BsonDocument query_courses_of_oldest = BsonDocument.parse("{age: -1}");
        collection.find().sort(query_courses_of_oldest).limit(1).forEach((Consumer<Document>) document -> {
            System.out.println(""+ document.get("courses"));
        });

    }


}
