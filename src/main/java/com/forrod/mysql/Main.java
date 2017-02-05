package com.forrod.mysql;


import com.forrod.mysql.dbconn.ConnManager;
import com.forrod.mysql.model.Person;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        ConnManager personDao = new ConnManager();

        System.out.println("================RESULT================");
        System.out.println("Creating person");
        Person p = new Person();
        p.setName("nyor");
        p.setAddress("mandaluyong");
        System.out.println("Person: " + p.toString());
        personDao.create(p);
        System.out.println("================RESULT================");


        Integer res = personDao.countPersons();
        System.out.println("================RESULT================");
        System.out.println("Person count: " + res);
        System.out.println("================RESULT================");

        Person person = personDao.findOne(1);
        System.out.println("================RESULT================");
        System.out.println("Person: " + person.toString());
        System.out.println("================RESULT================");

        String personName = personDao.getName(1);
        System.out.println("================RESULT================");
        System.out.println("Person name: " + personName);
        System.out.println("================RESULT================");

        Map<String, Object> customFieldResult = personDao.getCustomField(1);
        System.out.println("================RESULT================");
        System.out.println("Person name: " + customFieldResult);
        System.out.println("================RESULT================");

        personDao.update(new Person(1, "rods", "sda"));
        person = personDao.findOne(1);
        System.out.println("================RESULT================");
        System.out.println("Person: " + person.toString());
        System.out.println("================RESULT================");
    }
}
