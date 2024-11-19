package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.model.Person;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")  // Используется профиль "test", который подгружает application-test.yml
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private Person person;

    @Autowired
    private DataSource dataSource;

    @Test
    public void printDatabaseInfo() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("URL: " + connection.getMetaData().getURL());
        System.out.println("User: " + connection.getMetaData().getUserName());
        connection.close();
    }

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        person.setAge(30);
        personService.savePerson(person);  // Сохраняем данные перед тестом
    }

    @Test
    public void testGetPerson() {
        Person foundPerson = personService.getPerson(1L);
        assertNotNull(foundPerson);
        assertEquals("John Doe", foundPerson.getName());
        assertEquals(30, foundPerson.getAge());
    }

    @Test
    public void testSavePerson() {
        Person newPerson = new Person();
        newPerson.setId(2L);
        newPerson.setName("Jane Doe");
        newPerson.setAge(25);

        Person savedPerson = personService.savePerson(newPerson);
        assertNotNull(savedPerson);
        assertEquals("Jane Doe", savedPerson.getName());
        assertEquals(25, savedPerson.getAge());
    }
}

