package com.caique.city.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

}
