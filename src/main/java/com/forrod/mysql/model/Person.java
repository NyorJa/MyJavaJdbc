package com.forrod.mysql.model;

public class Person {
    private Integer id;
    private String name;
    private String address;
    private Gender gender;

    public Person() {
    }

    public Person(Integer id, String name, String address, Gender gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public Person(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Person(String name, String address, Gender gender) {
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}
