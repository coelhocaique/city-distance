package com.caique.city.service;

import java.util.List;

import com.caique.city.dto.CityDistanceDTO;


public interface CityDistanceService {
	
	List<CityDistanceDTO> calculateWithAllCities(String measureUnit) throws Exception;
}
