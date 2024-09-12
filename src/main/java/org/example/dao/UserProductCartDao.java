package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.Factory;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.entity.UserProductCart;
import org.example.entity.*;

import java.util.List;
import java.util.Optional;

public class UserProductCartDao extends Repository<UserProductCart>
{
    public UserProductCartDao(EntityManager entityManager) {
        super(UserProductCart.class, entityManager);
    }

    public List<UserProductCart> getCartByUser(User user){
        TypedQuery<UserProductCart> query= entityManager.
                createQuery("select u from UserProductCart u where u.user=:user", UserProductCart.class)
                .setParameter("user",user);
        return query.getResultList();

    }

    public Optional<UserProductCart> findUserProduct(int userId,int productId)
    {
        UserProductCart userProductCart = entityManager.find(UserProductCart.class,new UserProductId(userId,productId));

        return Optional.ofNullable(userProductCart);
    }

    public Integer countProductsByUser(int userId) {
        String s = "select count(up) from UserProductCart up where up.user.id = :id";
        Query q = entityManager.createQuery(s).setParameter("id", userId);
        Long countResult = (Long) q.getSingleResult(); // Cast to Long
        return countResult != null ? countResult.intValue() : null; // Convert Long to Integer
    }

}
