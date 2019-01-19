package com.uol.service.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codinome" })
public class Vingador implements Serializable {

	@JsonProperty("codinome")
	private String codinome;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8292418345969241141L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Vingador() {
	}

	/**
	 * 
	 * @param codinome
	 */
	public Vingador(String codinome) {
		super();
		this.codinome = codinome;
	}

	@JsonProperty("codinome")
	public String getCodinome() {
		return codinome;
	}

	@JsonProperty("codinome")
	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}