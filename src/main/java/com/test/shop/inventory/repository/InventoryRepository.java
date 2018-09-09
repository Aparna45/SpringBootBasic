package com.test.shop.inventory.repository;

import com.test.shop.inventory.model.ItemInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<ItemInventory, Integer> {

}
