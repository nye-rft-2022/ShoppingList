package com.epam.example.shoppinglist.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Represents a user in the database. Contains the users' id, name, shopping lists.
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @OneToOne(targetEntity = ShoppingListEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private ShoppingListEntity shoppingList;
}