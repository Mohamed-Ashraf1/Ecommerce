package org.example.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderDaoTests {

//
//    @Test
//    void testFilterOrders()
//    {
//        EntityManagerFactory entityManagerFactory = Factory.entityManagerFactory;
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//
//        User user = entityManager.find(User.class,1);
//
//        Order a=new Order();
//        a.setStatus(STATUS.CANCELED);
//        a.setTotalPrice(600.0);
//        a.setOrderDate(new Date(2026, 5, 5));
//        a.setPaymentType(PAYMENT.CASH);
//        a.setUser(user);
//
//        Order b= new Order();
//        b.setStatus(STATUS.PENDING);
//        b.setTotalPrice(1900.0);
//        b.setOrderDate(new Date(2026, 5, 5));
//        b.setPaymentType(PAYMENT.VISA);
//        b.setUser(user);
//
//        entityManager.persist(a);
//        entityManager.persist(b);
//
//        entityManager.getTransaction().begin();
//        entityManager.getTransaction().commit();
//
//        OrderDao orderDao = new OrderDao(entityManager);
//
//        List<Order> actualOrders = orderDao.filterOrders(null,null,new Date(2026, 5, 5),new Date(2026, 5, 5));
//        List<Order> expectedOrders = new ArrayList<Order>();
//        expectedOrders.add(a);
//        expectedOrders.add(b);
//        assertEquals(expectedOrders, actualOrders);
//    }

//    @Test
//    void testCalc2Function()
//    {
//        User user = new User();
//        assertEquals(15,user.calc(5));
//        assertEquals(1,user.calc(0));
//    }


}


