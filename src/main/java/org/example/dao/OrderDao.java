package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


import org.example.entity.*;

import java.util.*;

public class OrderDao extends Repository<Order>{

    public OrderDao(EntityManager entityManager) {super(Order.class, entityManager);
    }

    //user Orders
    //products per Order
    public List<Order> findOrdersByUser(int userId)
    {
        String s = "from Order o where o.user.id = :userId";
        Query query = entityManager.createQuery(s).setParameter("userId",userId);
        return query.getResultList();
    }

    public Map<Integer,List<Order>> filterOrders(STATUS status,PAYMENT payment,Date startDate, Date endDate,Double minPrice,Double maxPrice,boolean sortByPrice,int pageNumber, int pageSize)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);


        // Initialize a list of predicates
        List<Predicate> predicates = new ArrayList<>();

        if(status != null)
            predicates.add( criteriaBuilder.equal(root.get("status"),status) );

        if(payment != null)
            predicates.add( criteriaBuilder.and( criteriaBuilder.equal(root.get("paymentType"),payment) ) );

        if(startDate != null && endDate != null)
            predicates.add( criteriaBuilder.and(criteriaBuilder.between(root.get("orderDate"),startDate,endDate) ) );

        if(minPrice != null && maxPrice != null)
            predicates.add( criteriaBuilder.and(criteriaBuilder.between(root.get("totalPrice"),minPrice,maxPrice) ) );


        // Apply predicates to the query
        if (!predicates.isEmpty()) {
            query.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }


        if(sortByPrice == true)
            query.orderBy(criteriaBuilder.asc(root.get("totalPrice") ));


        // Create TypedQuery from CriteriaQuery
        TypedQuery<Order> typedQuery = entityManager.createQuery(query);
        int ordersNumber = typedQuery.getResultList().size();

        // Apply pagination parameters to TypedQuery
        int firstResult = (pageNumber - 1) * pageSize;
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(pageSize);


        Map<Integer,List<Order>> result = new HashMap<>();

        result.put(ordersNumber,typedQuery.getResultList());

        // Execute the query and return the results
        return result;
    }


    public <T> Map<T, List<Integer>> getOrdersByGroupBy(GROUPBY type)
    {
        Object t = null;

        switch(type)
        {
            case GROUPBYPAYMENT:
                t = "o.paymentType";
                break;
            case GROUPBYSTATUS:
                t = "o.status";
                break;
            case GROUPBYUSER:
                t = "o.user_id";
                break;
            case GROUPBYDATE:
                t = "o.order_date";
                break;
        }


        String s = "SELECT " + t + ", o.id FROM orders o";

        Query q = entityManager.createNativeQuery(s);

        List<Object[]> list = q.getResultList();

        Map<T, List<Integer>> entry = new HashMap<T, List<Integer>>();

        for(Object[] objects: list)
        {
            if(entry.get((T) objects[0]) == null)
                entry.put((T) objects[0],new ArrayList<Integer>());

            entry.get((T) objects[0]).add((Integer) objects[1]);
        }

        return entry;
    }

}
