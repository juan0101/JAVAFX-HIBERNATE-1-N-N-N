package br.com.escola.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="materia")
@Data
public class Materia {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="nome", nullable=false)
	private String nome;
	@ManyToOne
	private Professor professor;
	
}
