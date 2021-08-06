import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MongoBase {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection_shops;
    MongoCollection<Document> collection_products;

    public void init() {

        mongoClient = new MongoClient("127.0.0.1", 27017);
        database = mongoClient.getDatabase("test");
        collection_shops = initCollection("shops");
        collection_products = initCollection("products");
    }

    public MongoCollection<Document>  initCollection(String name) {

        MongoCollection<Document> collection = database.getCollection(name);
//        collection.drop();
        return collection;
    }

    public void executeCommand(Command command) {
        switch (command.getCommandType()) {
            case ADD_SHOP:
                addShop(command);
                break;
            case ADD_PRODUCT:
                addProduct(command);
                break;
            case ADD_PRODUCT_TO_SHOP:
                addProductToShop(command);
                break;
            case GET_STATISTICS:
                getStatistics();
                break;
            default:
                break;

        }
    }

    private void addShop(Command command) {
        Document document = (new Document()).append("name", command.getShop()).append("products",new ArrayList<String>());
        collection_shops.insertOne(document);
    }

    private void addProduct(Command command) {
        Document document = (new Document()).append("name", command.getProduct()).append("price",command.getPrice());
        collection_products.insertOne(document);
    }

    private void addProductToShop(Command command) {
        BsonDocument query_shop = BsonDocument.parse("{name: \""+command.getShop()+"\"}");

        BsonDocument query_product = BsonDocument.parse("{ $push: { products:  \""+command.getProduct()+"\" } }");
        collection_shops.updateOne(query_shop,query_product);

    }

    private void getStatistics() {

        List<Bson> bsonDocumentList = new ArrayList<>();
        bsonDocumentList.add(BsonDocument.parse("{$lookup: {from:\"products\",localField:\"products\",foreignField:\"name\",as:\"products_list\" } } "));
        bsonDocumentList.add(BsonDocument.parse("{$unwind: {path:\"$products_list\"} } "));
        bsonDocumentList.add(BsonDocument.parse((" {$group: {" +
                " _id: \"$name\", " +
                " average_price: {$avg: \"$products_list.price\"}, " +
                " product_count: {$sum: 1}, " +
                " cheapest_product: {$min: \"$products_list.price\"}," +
                " most_expensive_product: {$max: \"$products_list.price\"}," +
                " count_cheaper_100: {$sum: { $cond: [ { $lt: [ \"$products_list.price\", 100 ] }, 1, 0 ] } } " +
                " } } ")));

        AggregateIterable<Document> list = collection_shops.aggregate(bsonDocumentList);
        System.out.println("Статистика: ");
        list.forEach((Consumer<Document>) document -> {
            System.out.println(String.format("Магазин: %s - средняя цена = %s, количество товаров = %s, " +
                    "самый дешевый товар = %s, самый дорогой товар = %s, количество товаров, дешевле 100 = %s",
                    document.get("_id"), document.get("average_price"), document.get("product_count"),
                    document.get("cheapest_product"), document.get("most_expensive_product"), document.get("count_cheaper_100")));
        });


    }



}
