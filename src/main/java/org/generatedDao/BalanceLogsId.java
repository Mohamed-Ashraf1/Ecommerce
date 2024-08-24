package org.generatedDao;
// Generated Aug 25, 2024, 1:33:37 AM by Hibernate Tools 6.5.0.Final


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * BalanceLogsId generated by hbm2java
 */
@Embeddable
public class BalanceLogsId  implements java.io.Serializable {


     private int balanceLogsId;
     private int userUserId;

    public BalanceLogsId() {
    }

    public BalanceLogsId(int balanceLogsId, int userUserId) {
       this.balanceLogsId = balanceLogsId;
       this.userUserId = userUserId;
    }
   


    @Column(name="balance_logs_id", unique=true, nullable=false)
    public int getBalanceLogsId() {
        return this.balanceLogsId;
    }
    
    public void setBalanceLogsId(int balanceLogsId) {
        this.balanceLogsId = balanceLogsId;
    }


    @Column(name="user_user_id", nullable=false)
    public int getUserUserId() {
        return this.userUserId;
    }
    
    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BalanceLogsId) ) return false;
		 BalanceLogsId castOther = ( BalanceLogsId ) other; 
         
		 return (this.getBalanceLogsId()==castOther.getBalanceLogsId())
 && (this.getUserUserId()==castOther.getUserUserId());
   }
   
   public int hashCode() {
         int result = 17;
         
        result = 37 * result + this.getBalanceLogsId();
        result = 37 * result + this.getUserUserId();
         return result;
   }   


}


