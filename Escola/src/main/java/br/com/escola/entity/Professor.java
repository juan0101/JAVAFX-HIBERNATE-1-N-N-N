package br.com.escola.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="professor")
@Data
public class Professor {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="nome", nullable=false)
	private String nome;
	@Column(name="materia")
	@OneToMany(mappedBy="professor",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Materia> materia;
	
	@ManyToMany
	@JoinTable(name="professor_aluno", joinColumns=
		{@JoinColumn(name="professor_id")}, inverseJoinColumns=
		{@JoinColumn(name="aluno_id")})
	private List<Aluno> alunos;
	
}
