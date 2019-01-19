package com.uol.service.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="liga_da_justica")
@XmlAccessorType(XmlAccessType.FIELD)
public class LigaDaJustica {

	@XmlElementWrapper(name="codinomes")
    @XmlElement(name="codinome")
	private List<String> codinomes;
	
	public LigaDaJustica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<String> getCodinomes() {
		return codinomes;
	}

	public void setCodinomes(List<String> codinomes) {
		this.codinomes = codinomes;
	}
}
