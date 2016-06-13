package br.com.escola.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="aluno")
@Data
public class Aluno {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="nome", nullable=false)
	private String nome;
	@ManyToMany(mappedBy="alunos")
    private List<Professor> professores;

}
