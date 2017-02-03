package com.forrod.mysql;


import com.forrod.mysql.dbconn.ConnManager;

public class Main {
    public static void main(String[] args) throws Exception {

        ConnManager personDao = new ConnManager();
        Integer res = personDao.countPersons();
        System.out.println("================RESULT================");
        System.out.println("Person count: " + res);
        System.out.println("================RESULT================");
    }
}
