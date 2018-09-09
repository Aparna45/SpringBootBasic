package com.test.shop.inventory.service;

import com.test.shop.inventory.model.ItemInventoryResponse;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

	List<ItemInventoryResponse> getItems();

	ItemInventoryResponse getItem(Optional<Integer> id) throws Exception;

}
