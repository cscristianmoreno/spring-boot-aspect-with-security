package com.security.spring_app.reuse;

import com.security.spring_app.entity.Persons;

public abstract class PersonsTest {
    
    public static Persons getPerson() {
        Persons persons = new Persons();
        persons.setId(1);
        persons.setName("Cristian");
        persons.setLastname("Moreno");
        persons.setAge(27);
        persons.setDni(40148758);

        return persons;
    }
}
