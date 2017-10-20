package com.caique.city.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.caique.city.dto.CityDistanceDTO;
import com.caique.city.service.CityDistanceService;

@Path("/calculate")
public class CityDistanceResource {
	
	@Inject
	private CityDistanceService cityDistanceService;
	
	@GET
	@Path("/{returnType}/{measureUnit}")
	public ResponseBuilder calculate(@PathParam("returnType") String returnType,
									 @PathParam("measureUnit") String measureUnit) {
		try {
			validateParams(returnType,measureUnit);
			
			List<CityDistanceDTO> citiesDTOs = cityDistanceService.calculateWithAllCities(measureUnit.toLowerCase());
			
			return Response.ok(citiesDTOs);
		} catch (Exception e) {
			return null; //e.getMessage();
		}
	}

	private void validateParams(String returnType, String measureUnit) throws Exception {
		if(!returnType.toLowerCase().equals("xml") || !returnType.toLowerCase().equals("json")){
			throw new Exception("Return Type is not valid");
		}
		
		if(!measureUnit.toLowerCase().equals("km") || !measureUnit.toLowerCase().equals("mi")){
			throw new Exception("Measure Unit is not valid");
		}
	}
}
