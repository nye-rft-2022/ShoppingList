package com.epam.example.shoppinglist.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Represents an entry in a shopping list.
 */
@Entity
@Table(name = "entry")
@Data
@NoArgsConstructor
public class ShoppingListEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}