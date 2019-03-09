package br.com.empresa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -2480126210060974634L;

	public Funcionario() {
	}
	
	public Funcionario(String nome, Double latitude, Double longitude) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private Double latitude;
	
	private Double longitude;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
