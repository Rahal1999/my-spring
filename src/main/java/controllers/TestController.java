package controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import service.DBMongo;
import service.DBMysql;

@RestController
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/")
    public String home() {

        return "Spring boot is working!";

    }

    @RequestMapping(value = "/fetch/{id:[a-z]+}/{name}", method = RequestMethod.GET)
    String getDynamicUriValueRegex(@PathVariable("name") String name) {
        System.out.println("Name is " + name);
        return "Dynamic URI parameter fetched using regex";
    }


    @RequestMapping(path = "/api/{id}/{title}/{price}", method = RequestMethod.GET)
    public String addBook(@PathVariable String id, @PathVariable String title,  @PathVariable String price) {

        Book bookobj = new Book(id,title,price);

        System.out.println("id issssss  : "+id+"--------"+title+"   "+price);

        DBMysql o = new DBMysql();
        o.add(bookobj.getId(),bookobj.getTitle(),bookobj.getPrice());

        DBMongo obj = new DBMongo();
        obj.add(id,title,price);
        return "Book Added  : " + id+" "+ title+" "+price;
    }


    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable String id) {

        DBMysql o = new DBMysql();
        o.delete(id);

        DBMongo mongo = new DBMongo();
        mongo.delete(id);

        return "Deleted    : " + id;
    }

    @RequestMapping(path = "/update/{id}/{title}/{price}", method = RequestMethod.GET)
    public String updateBook(@PathVariable String id, @PathVariable String title,  @PathVariable String price) {

        Book bookobj = new Book(id,title,price);

        DBMysql o = new DBMysql();
        o.update(bookobj.getId(),bookobj.getTitle(),bookobj.getPrice());

        DBMongo mongo = new DBMongo();
        mongo.update(bookobj.getId(),bookobj.getTitle(),bookobj.getPrice());

        return "Updated  : " + id;
    }




}

//mvn spring-boot:run