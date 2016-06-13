package br.com.escola.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.escola.entity.Aluno;

public class AlunoDAO {

	public EntityManager getEM(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("escola");
		return factory.createEntityManager();
	}
	
	public void salvar(Aluno a){
		EntityManager em = getEM();
		try{
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> getAll(){
		EntityManager em = getEM();
		List<Aluno> alunos = null;
		try{
			Query query = em.createQuery("Select a FROM Aluno a");
			alunos = query.getResultList();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return alunos;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getVinculoProfessor(int idAluno){
		EntityManager em = getEM();
		List<String> professores = null;
		try{
			Query query = em.createQuery("Select p.nome from Aluno a join a.professores as p where a.id = :idAluno ");
			query.setParameter("idAluno", idAluno);
			professores = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}
		return professores;
	}
}
