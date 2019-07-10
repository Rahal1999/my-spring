package service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DBMongo {
    DBCollection table;

    static final String id = "ID";
    static final String title = "Title";
    static final String price = "Price";


    public DBMongo(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("books");
         table = db.getCollection("items");
    }


    public void add(String id,String title,String price){
        BasicDBObject document = new BasicDBObject();
        document.put(id, id);
        document.put(title, title);
        document.put(price,price);
        table.insert(document);
    }

    public void delete(String id){
        BasicDBObject document = new BasicDBObject();
        document.put(id, id);
        table.remove(document);
    }

    public void update(String id,String title,String price){

        BasicDBObject old = new BasicDBObject(id, id);
        BasicDBObject newData = new BasicDBObject().append(title, title)
                .append(price, price);
        BasicDBObject updateDoc = new BasicDBObject("$set", newData);
        table.update(old, updateDoc);

    }

}
