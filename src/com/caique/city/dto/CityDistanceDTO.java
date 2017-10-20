package com.caique.city.dto;


import com.caique.city.model.City;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDistanceDTO {
	
	private City source;
	
	private City destin;
	
	private Double distance;
	
	private String measureUnit;

}
