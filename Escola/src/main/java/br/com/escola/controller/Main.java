package br.com.escola.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.escola.entity.Aluno;

public class Main {
	
	public static EntityManager getEM(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("escola");
		return factory.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aluno> getVinculoAluno(int idProf){
		EntityManager em = getEM();
		List<Aluno> alunos = null;
		try{
			Query query = em.createQuery("Select a.nome from Professor p join p.alunos as a where p.id = :idProf ");
			query.setParameter("idProf", idProf);
			System.out.println(query.toString());
			alunos = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}
		return alunos;
	}

	public static void main(String[] args) {
		List<Aluno> alunos = null;
		alunos = getVinculoAluno(1);
		if(alunos != null){
			for(Aluno a: alunos){
				String nome = a.getNome();
				System.out.println(nome);
			}
		}

	}

}
