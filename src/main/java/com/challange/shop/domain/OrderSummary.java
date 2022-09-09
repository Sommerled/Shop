package com.challange.shop.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class OrderSummary {

	private List<OrderSummaryItem> OrderItems;
	
	private Float total;
}
