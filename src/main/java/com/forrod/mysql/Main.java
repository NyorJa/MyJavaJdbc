package com.forrod.mysql;


import com.forrod.mysql.dbconn.ConnManager;
import com.forrod.mysql.model.Person;

public class Main {
    public static void main(String[] args) throws Exception {

        ConnManager personDao = new ConnManager();
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
    }
}
