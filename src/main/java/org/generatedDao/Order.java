package org.generatedDao;
// Generated Aug 25, 2024, 1:33:37 AM by Hibernate Tools 6.5.0.Final


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Order generated by hbm2java
 */
@Entity
@Table(name="order"
    ,catalog="ecommerce"
)
public class Order  implements java.io.Serializable {


     private int orderId;
     private User user;
     private double totalPrice;
     private Timestamp date;
     private String status;
     private String paymentType;
     private Set<Product> products = new HashSet<Product>(0);

    public Order() {
    }

	
    public Order(int orderId, User user, double totalPrice, Timestamp date, String status, String paymentType) {
        this.orderId = orderId;
        this.user = user;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
        this.paymentType = paymentType;
    }
    public Order(int orderId, User user, double totalPrice, Timestamp date, String status, String paymentType, Set<Product> products) {
       this.orderId = orderId;
       this.user = user;
       this.totalPrice = totalPrice;
       this.date = date;
       this.status = status;
       this.paymentType = paymentType;
       this.products = products;
    }
   
     @Id 

    
    @Column(name="order_id", unique=true, nullable=false)
    public int getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="total_price", nullable=false, precision=22)
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable=false, length=19)
    public Timestamp getDate() {
        return this.date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }

    
    @Column(name="status", nullable=false, length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="payment_type", nullable=false, length=45)
    public String getPaymentType() {
        return this.paymentType;
    }
    
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

@ManyToMany(fetch=FetchType.LAZY, mappedBy="orders")
    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }




}


