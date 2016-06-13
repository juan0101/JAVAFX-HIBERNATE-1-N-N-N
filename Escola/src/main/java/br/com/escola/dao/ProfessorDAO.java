package br.com.escola.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.escola.entity.Aluno;
import br.com.escola.entity.Materia;
import br.com.escola.entity.Professor;

public class ProfessorDAO {

	public EntityManager getEM(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("escola");
		return factory.createEntityManager();
	}
	
	public void salvar(Professor p){
		EntityManager em = getEM();
		try{
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> getAll(){
		EntityManager em = getEM();
		List<Professor> professores = null;
		try{
			Query query = em.createQuery("Select p FROM Professor p");
			professores = query.getResultList();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return professores;
	}
	
	public void salvarVinculoAluno(int idp, int ida){
		EntityManager em = getEM();
		Professor professor = em.find(Professor.class, idp);
		Aluno aluno = em.find(Aluno.class, ida);
		try{
			em.getTransaction().begin();
			aluno.getProfessores().add(professor);
			professor.getAlunos().add(aluno);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getVinculoAluno(int idProf){
		EntityManager em = getEM();
		List<String> alunos = null;
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
	
	public void salvarVinculoMateria(Professor p, int idMateria){
		EntityManager em = getEM();
		Professor professor = em.find(Professor.class, p.getId());
		Materia mat = em.find(Materia.class, idMateria);
		try{
			em.getTransaction().begin();
			professor = p;
			mat.setProfessor(professor);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
}
