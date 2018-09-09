package com.test.shop.inventory.service;

import com.test.shop.inventory.model.ItemInventory;
import com.test.shop.inventory.model.ItemInventoryResponse;
import com.test.shop.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private InventoryRepository inventoryRepository;

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<ItemInventoryResponse> getItems() {

        return inventoryRepository.findAll().stream().map(itemInventory -> ItemInventoryResponse.builder().id(itemInventory.getId())
                .item(itemInventory.getItem())
                .currentQuality(getCurrentQuality(itemInventory))
                .daysRemainingToSell(itemInventory.isQualityChange() ? getDaysRemainingToSell(itemInventory) : itemInventory.getSellIn())
                .build()).collect(Collectors.toList());
    }

    @Override
    public ItemInventoryResponse getItem(Optional<Integer> id) throws Exception {
        if (id.isPresent()) {
            ItemInventory itemInventory = inventoryRepository.findOne(id.get());
            return ItemInventoryResponse.builder().id(itemInventory.getId())
                    .item(itemInventory.getItem())
                    .currentQuality(getCurrentQuality(itemInventory))
                    .daysRemainingToSell(itemInventory.isQualityChange() ? getDaysRemainingToSell(itemInventory) : itemInventory.getSellIn())
                    .build();
        }
        throw new Exception("Id should be a valid number");
    }

    private Integer getCurrentQuality(ItemInventory itemInventory) {

        int qualityTobeSubtracted = 0;
        int qualityTobeAdded = 0;
        int daysRemainingToSell = getDaysRemainingToSell(itemInventory);
        logger.debug("daysRemainingToSell, {}", daysRemainingToSell);
        if (itemInventory.isDecrement()) {
            if (daysRemainingToSell < 0) {
                qualityTobeSubtracted = itemInventory.getSellIn() + Math.abs(daysRemainingToSell);
            } else {
                qualityTobeSubtracted = daysRemainingToSell;
            }
        }

        if (itemInventory.isIncrement()) {
            if (daysRemainingToSell < 0) {
                qualityTobeAdded = itemInventory.getSellIn() + Math.abs(daysRemainingToSell);
            } else {
                qualityTobeAdded = daysRemainingToSell;
            }
        }
        if (itemInventory.isDoubleDecrement()) {
            qualityTobeSubtracted += daysRemainingToSell <= 10 ? daysRemainingToSell : 0;
        }
        if (itemInventory.isDoubleIncrement()) {
            qualityTobeAdded += Math.abs(daysRemainingToSell) <= 10 ? daysRemainingToSell : 0;
        }
        logger.debug("qualityTobeSubtracted, {}, qualityTobeAdded, {}", qualityTobeSubtracted, qualityTobeAdded);
        int currentQuality = itemInventory.getQuality() - qualityTobeSubtracted + qualityTobeAdded;
        if (currentQuality > 50) {
            currentQuality = 50;
        }
        if (currentQuality < 0) {
            currentQuality = 0;
        }
        if (!itemInventory.isQualityChange()) {
            currentQuality = itemInventory.getQuality();
        }
        return currentQuality;
    }

    private Integer getDaysRemainingToSell(ItemInventory itemInventory) {
        LocalDate currentDate = LocalDate.now();
        return itemInventory.getSellIn() - (currentDate.getDayOfYear() - LocalDate.parse(
                new SimpleDateFormat("yyyy-MM-dd").format(itemInventory.getAddedDate())).getDayOfYear() + 1) ;
    }
}
