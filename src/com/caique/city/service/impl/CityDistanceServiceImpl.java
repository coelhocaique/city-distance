package com.caique.city.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.caique.city.dao.CityDao;
import com.caique.city.dto.CityDistanceDTO;
import com.caique.city.model.City;
import com.caique.city.service.CityDistanceService;


public class CityDistanceServiceImpl implements CityDistanceService {
	
	private CityDao cityDao;
	
	public CityDistanceServiceImpl() {
		cityDao = new CityDao();
	}
	
	@Override
	public List<CityDistanceDTO> calculateWithAllCities(String measureUnit) throws Exception {
		try{
			List<CityDistanceDTO> cityDistanceDTOs = null;
			List<City> cities = cityDao.findAll();
			
			if(cities != null && cities.size() > 0){
				cityDistanceDTOs = new ArrayList<>();
				for (int i = 0; i < cities.size(); i++) {
					City src = cities.get(i);
					Double lat1 = src.getLatitude();
					Double long1 = src.getLongitude();
					for (int j = i + 1; j < cities.size(); j++) {
						City dest = cities.get(j);
						Double lat2 = dest.getLatitude();
						Double long2 = dest.getLongitude();
						Double distance = calculateDistance(lat1,lat2,long1,long2,measureUnit);
						cityDistanceDTOs.add(new CityDistanceDTO(src, dest, distance,measureUnit));
					}
				}
			}
			return cityDistanceDTOs;
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * calculate distance between two points in a circle using Haversine method
	 *  R =  6,371km / 3960miles
		  Δlat = lat2− lat1
		  Δlong = long2− long1
		  a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlong/2)
		  c = 2.atan2(√a, √(1−a))
		  d = R.c
	 * @param lat1
	 * @param lat2
	 * @param long1
	 * @param long2
	 * @param measureUnit 
	 * @return distance in centimeters
	 */
	private Double calculateDistance(Double lat1, Double lat2, Double long1, Double long2, String measureUnit) {
		
		//Radius of earth
		final int radius = measureUnit.equals("km") ? 6371:3960;

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(long2 - long1);
	    double a =  Math.sin(latDistance / 2) 
	    			* Math.sin(latDistance / 2)
	    			+ Math.cos(Math.toRadians(lat1)) 
	    			* Math.cos(Math.toRadians(lat2))
	    			* Math.sin(lonDistance / 2) 
	    			* Math.sin(lonDistance / 2);
	    
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    
	    double distance = radius * c;

	    return distance;
	}
}
