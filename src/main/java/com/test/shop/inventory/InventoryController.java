package com.test.shop.inventory;

import com.test.shop.inventory.model.ItemInventoryResponse;
import com.test.shop.inventory.service.InventoryService;
import com.test.shop.inventory.service.InventoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest")
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	InventoryService inventoryService;


	// Display list

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<List<ItemInventoryResponse>> getAllItems() {
		logger.debug("Requested to get all items");
		List<ItemInventoryResponse> items = inventoryService.getItems();
		if (items.isEmpty()) {
			return new ResponseEntity<List<ItemInventoryResponse>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ItemInventoryResponse>>(items, HttpStatus.OK);
	}

	// Single record retrieval

	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemInventoryResponse> getCustomer(@PathVariable("id") int id) throws Exception{
		logger.debug("Requested the item with id {}", id);
		ItemInventoryResponse item = inventoryService.getItem(Optional.ofNullable(id));
		{
			if (item == null) {
				System.out.println("Customer with id " + id + " not found");
				return new ResponseEntity<ItemInventoryResponse>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ItemInventoryResponse>(item, HttpStatus.OK);
		}
	}

}
