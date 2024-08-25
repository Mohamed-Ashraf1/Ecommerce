package org.generatedDao;
// Generated Aug 25, 2024, 11:51:02 AM by Hibernate Tools 6.5.0.Final


import jakarta.persistence.*;

/**
 * BalanceLogs generated by hbm2java
 */
@Entity
@Table(name="balance_logs"
    ,catalog="ecommerce"
    , uniqueConstraints = @UniqueConstraint(columnNames="balance_logs_id") 
)
public class BalanceLogs  implements java.io.Serializable {


     private BalanceLogsId id;
     private User user;
     private String paymentType;
     private Float amount;

    public BalanceLogs() {
    }

    public BalanceLogs(BalanceLogsId id, User user, String paymentType, float amount) {
       this.id = id;
       this.user = user;
       this.paymentType = paymentType;
       this.amount = amount;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="balanceLogsId", column=@Column(name="balance_logs_id", unique=true, nullable=false) ), 
        @AttributeOverride(name="userUserId", column=@Column(name="user_user_id", nullable=false) ) } )
    public BalanceLogsId getId() {
        return this.id;
    }
    
    public void setId(BalanceLogsId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_user_id", nullable=false, insertable=false, updatable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="payment_type", nullable=false, length=45)
    public String getPaymentType() {
        return this.paymentType;
    }
    
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    
    @Column(name="amount", nullable=false, precision=12)
    public float getAmount() {
        return this.amount;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }




}


