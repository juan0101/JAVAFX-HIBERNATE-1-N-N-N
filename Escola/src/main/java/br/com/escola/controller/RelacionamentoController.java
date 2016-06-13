package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.dao.AlunoDAO;
import br.com.escola.dao.MateriaDAO;
import br.com.escola.dao.ProfessorDAO;
import br.com.escola.entity.Aluno;
import br.com.escola.entity.Materia;
import br.com.escola.entity.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RelacionamentoController {

	private ProfessorDAO pDao = new ProfessorDAO();
	private AlunoDAO aDao = new AlunoDAO();
	private MateriaDAO mDao = new MateriaDAO();
	
	private List<Professor> professores = new ArrayList<Professor>();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private List<Materia> materias = new ArrayList<Materia>();
	
	@FXML private ComboBox<String> cProfA;
	@FXML private ComboBox<String> cAluno;
	@FXML private ComboBox<String> cProfM;
	@FXML private ComboBox<String> cMat;
	
	private int idMateria;
	
	@FXML
	public void vincularProfAluno(){
		String nomeProfessor = cProfA.getSelectionModel().selectedItemProperty().getValue();
		String nomeAluno = cAluno.getSelectionModel().selectedItemProperty().getValue();
		Professor p = null;
		Aluno a = null;
		for(Professor pro: professores){
			if(pro.getNome().equals(nomeProfessor)){
				p = pro;
				break;
			}
		}
		for(Aluno alu: alunos){
			if(alu.getNome().equals(nomeAluno)){
				a = alu;
				break;
			}
		}
		if(p != null && a != null){
			pDao.salvarVinculoAluno(p.getId(), a.getId());
		}
		
		
	}
	
	@FXML
	public void vincularProfMat(){
		System.out.println("OIIIIIIII");
		String nomeProfessor = cProfM.getSelectionModel().selectedItemProperty().getValue();
		String nomeMateria = cMat.getSelectionModel().selectedItemProperty().getValue();
		Professor p = null;
		for(Professor pro: professores){
			if(pro.getNome().equals(nomeProfessor)){
				p = pro;
				System.out.println("ACHEEI");
				break;
			}
		}
		if(p != null){
			if(p.getMateria() != null){
				for(Materia mat: materias){
					if(mat.getNome().equals(nomeMateria)){
						idMateria = mat.getId();
						p.getMateria().add(mat);
						break;
					}
				}
			}else{
				List<Materia> listaMateria = new ArrayList<Materia>();
				p.setMateria(listaMateria);
				for(Materia mat: materias){
					if(mat.getNome().equals(nomeMateria)){
						idMateria = mat.getId();
						listaMateria.add(mat);
						break;
					}
				}
			}
			pDao.salvarVinculoMateria(p, idMateria);
		}
	}
	
	@FXML
	protected void initialize(){
		try{
			professores = pDao.getAll();
			List<String> nomeProfessores = new ArrayList<String>();
			for(Professor p: professores){
				nomeProfessores.add(p.getNome());
			}
			
			alunos = aDao.getAll();
			List<String> nomeAlunos = new ArrayList<String>();
			for(Aluno a: alunos){
				nomeAlunos.add(a.getNome());
			}
			
			materias = mDao.getAll();
			List<String> nomeMaterias = new ArrayList<String>();
			for(Materia m: materias){
				nomeMaterias.add(m.getNome());
			}
			
			cProfA.getItems().setAll(nomeProfessores);
			cProfM.getItems().setAll(nomeProfessores);
			cAluno.getItems().setAll(nomeAlunos);
			cMat.getItems().setAll(nomeMaterias);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
