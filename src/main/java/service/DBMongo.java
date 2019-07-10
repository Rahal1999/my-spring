package service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DBMongo {
    DBCollection table;
    public DBMongo(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("books");
         table = db.getCollection("items");
    }


    public void add(String id,String title,String price){
        BasicDBObject document = new BasicDBObject();
        document.put("ID", id);
        document.put("Title", title);
        document.put("Price",price);
        table.insert(document);
    }

    public void delete(String id){
        BasicDBObject document = new BasicDBObject();
        document.put("ID", id);
        table.remove(document);
    }

    public void update(String id,String title,String price){

        BasicDBObject old = new BasicDBObject("ID", id);
        BasicDBObject newData = new BasicDBObject().append("Title", title)
                .append("Price", price);
        BasicDBObject updateDoc = new BasicDBObject("$set", newData);
        table.update(old, updateDoc);

    }

}
