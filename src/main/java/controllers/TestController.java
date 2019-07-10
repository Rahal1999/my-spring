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
    public String getBook(@PathVariable String id, @PathVariable String title,  @PathVariable String price) {

        System.out.println("id issssss  : "+id+"--------"+title+"   "+price);
       /* DBMysql o = new DBMysql();
        o.add(id,title,price);

        DBMongo obj = new DBMongo();
        obj.add(id,title,price);*/
        return "ID: " + id+" "+ title+" "+price;
    }

}