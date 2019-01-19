package com.uol.service.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//classe utilitária para converter a partir do JSON
//criei utilizando as ferramentas na página: http://www.jsonschema2pojo.org/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vingadores" })
public class Vingadores implements Serializable {

	@JsonProperty("vingadores")
	private List<Vingador> vingadores = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -1202816774116989950L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Vingadores() {
	}

	/**
	 * 
	 * @param vingadores
	 */
	public Vingadores(List<Vingador> vingadores) {
		super();
		this.vingadores = vingadores;
	}

	@JsonProperty("vingadores")
	public List<Vingador> getVingadores() {
		return vingadores;
	}

	@JsonProperty("vingadores")
	public void setVingadores(List<Vingador> vingadores) {
		this.vingadores = vingadores;
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