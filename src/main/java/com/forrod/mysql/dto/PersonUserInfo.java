package com.forrod.mysql.dto;

public class PersonUserInfo {
    private Integer personId;
    private Integer userId;
    private String name;
    private String gender;
    private String username;

    public PersonUserInfo() {
    }

    public PersonUserInfo(Integer personId, Integer userId, String name, String gender, String username) {
        this.personId = personId;
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.username = username;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PersonUserInfo{" +
                "personId=" + personId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
