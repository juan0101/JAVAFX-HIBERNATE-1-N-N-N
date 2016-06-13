package br.com.escola.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.escola.entity.Materia;

public class MateriaDAO {

	public EntityManager getEM(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("escola");
		return factory.createEntityManager();
	}
	
	public void salvar(Materia m){
		EntityManager em = getEM();
		try{
			em.getTransaction().begin();
			em.persist(m);
			em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Materia> getAll(){
		EntityManager em = getEM();
		List<Materia> materias = null;
		try{
			Query query = em.createQuery("Select m FROM Materia m");
			materias = query.getResultList();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return materias;
	}
	
	public String getConsultaProfessorMateria(int idMat){
		EntityManager em = getEM();
		String professor = null;
		try{
			Query query = em.createQuery("Select p.nome FROM Materia m join m.professor as p where m.id = :idMat");
			query.setParameter("idMat", idMat);
			professor = (String) query.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}
		return professor;
	}
	
}
