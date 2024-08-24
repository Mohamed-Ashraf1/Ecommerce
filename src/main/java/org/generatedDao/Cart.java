package org.generatedDao;
// Generated Aug 25, 2024, 1:33:37 AM by Hibernate Tools 6.5.0.Final


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Cart generated by hbm2java
 */
@Entity
@Table(name="cart"
    ,catalog="ecommerce"
)
public class Cart  implements java.io.Serializable {


     private CartId id;
     private User user;
     private Product product;
     private String productQuantity;

    public Cart() {
    }

    public Cart(CartId id, User user, Product product, String productQuantity) {
       this.id = id;
       this.user = user;
       this.product = product;
       this.productQuantity = productQuantity;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="productId", column=@Column(name="product_id", nullable=false) ), 
        @AttributeOverride(name="userId", column=@Column(name="user_id", nullable=false) ) } )
    public CartId getId() {
        return this.id;
    }
    
    public void setId(CartId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    @Column(name="product_quantity", nullable=false, length=45)
    public String getProductQuantity() {
        return this.productQuantity;
    }
    
    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }




}


