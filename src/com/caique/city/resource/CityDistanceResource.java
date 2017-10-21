package com.caique.city.resource;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.caique.city.dto.CityDistanceDTO;
import com.caique.city.service.CityDistanceService;
import com.caique.city.service.impl.CityDistanceServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Path("/calculate")
public class CityDistanceResource {
	
	private CityDistanceService cityDistanceService = new CityDistanceServiceImpl();
	private static HashMap<String, String> returnTypes;
	
	static {
		returnTypes = new HashMap<>();
		returnTypes.put("json", MediaType.APPLICATION_JSON);
		returnTypes.put("xml", MediaType.APPLICATION_XML);
	}
	
	@GET
	@Path("/{returnType}/{measureUnit}")
	public Response calculate(@PathParam("returnType") String returnType,
							  @PathParam("measureUnit") String measureUnit) {
		try {
			validateParams(returnType,measureUnit);
			
			List<CityDistanceDTO> citiesDTOs = cityDistanceService.calculateWithAllCities(measureUnit.toLowerCase());
			String response = getResponse(citiesDTOs, returnType);
			
			return Response.ok(response)
							.header(HttpHeaders.CONTENT_TYPE, returnTypes.get(returnType))
							.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
						   .entity(e.getMessage())
						   .build();
		}
	}

	private String getResponse(List<CityDistanceDTO> citiesDTOs, String returnType) throws JsonProcessingException {
		if(returnType.equals("json")){
			ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writeValueAsString(citiesDTOs);
			return response;
		}else{
			XmlMapper xmlMapper = new XmlMapper();
			String response = xmlMapper.writeValueAsString(citiesDTOs);
			return response;
		}
	}

	private void validateParams(String returnType, String measureUnit) throws Exception {
		if(!returnType.equals("xml") && !returnType.equals("json")){
			throw new Exception("Return Type is not valid");
		}
		
		if(!measureUnit.equals("km") && !measureUnit.equals("mi")){
			throw new Exception("Measure Unit is not valid");
		}
	}
}
