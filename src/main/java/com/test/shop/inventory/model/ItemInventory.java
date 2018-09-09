package com.test.shop.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Table(name = "item_inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemInventory {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "item")
	private String item;
	@Column(name = "sell_in")
	private Integer sellIn;
	@Column(name = "quality")
	private Integer quality;
	@Column(name = "add_date")
	@Temporal(TemporalType.DATE)
	private Date addedDate;
	@Column(name = "quality_change")
	private boolean qualityChange;
	@Column(name = "increment")
	private boolean increment;
	@Column(name = "decrement")
	private boolean decrement;
	@Column(name = "double_increment")
	private boolean doubleIncrement;
	@Column(name = "double_decrement")
	private boolean doubleDecrement;

}
