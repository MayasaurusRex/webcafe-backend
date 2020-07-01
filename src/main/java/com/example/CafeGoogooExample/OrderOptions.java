package com.example.CafeGoogooExample;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity table for order options, getters and setters
 */
@Entity
public class OrderOptions {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String orderId;

    private String menuOpId;

    private Integer quantity;

    private String price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMenuOpId() {
        return menuOpId;
    }

    public void setMenuOpId(String menuOpId) {
        this.menuOpId = menuOpId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
