package com.forrod.mysql.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    private Person person;
    private static String anyString = "asd";

    @Before
    public void setUp() {
        person = new Person();
    }

    @Test
    public void testFields() {
        person.setAddress(anyString);

        Assert.assertEquals(anyString, person.getAddress());
    }
}
