package com.epam.example.shoppinglist.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a shopping list.
 */
@Entity
@Table(name = "shopping_list")
@Data
@NoArgsConstructor
public class ShoppingListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "list_name", nullable = false)
    private String name;

    @OneToMany(targetEntity = ShoppingListEntryEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ShoppingListEntryEntity> entries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingListEntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(List<ShoppingListEntryEntity> entries) {
        this.entries = entries;
    }
}
