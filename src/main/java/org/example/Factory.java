package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Factory {

    static public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce");;


}
