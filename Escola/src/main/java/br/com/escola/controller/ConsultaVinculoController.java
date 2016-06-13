package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.dao.AlunoDAO;
import br.com.escola.dao.MateriaDAO;
import br.com.escola.dao.ProfessorDAO;
import br.com.escola.entity.Relatorio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaVinculoController {

	@FXML private TableView<Relatorio> tabela_consulta;
    @FXML private TableColumn<Relatorio, String> coluna_nome;
    private ObservableList<Relatorio> data;
    private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();
    @FXML private TextField idProfessor;
    @FXML private TextField idAluno;
    @FXML private TextField idMateria;
    
    private MateriaDAO mDao = new MateriaDAO();
    private ProfessorDAO pDao = new ProfessorDAO();
    private AlunoDAO aDao = new AlunoDAO();
    
    @FXML
    public void pesquisaMateria(){
    	int idMat = Integer.parseInt(idMateria.getText());
    	String prof = mDao.getConsultaProfessorMateria(idMat);
    	Relatorio rel = new Relatorio();
    	rel.setNome(prof);
    	listaRelatorio.clear();
    	data.clear();
    	listaRelatorio.add(rel);
    	data.addAll(listaRelatorio);
    }
    
    @FXML
    public void pesquisaAluno(){
    	int idAlu = Integer.parseInt(idAluno.getText());
    	List<String> professores = aDao.getVinculoProfessor(idAlu);
    	listaRelatorio.clear();
    	data.clear();
    	for(String p: professores){
    		Relatorio rel = new Relatorio();
    		rel.setNome(p);
    		listaRelatorio.add(rel);
    	}
    	data.addAll(listaRelatorio);
    }
    
    @FXML
    public void pesquisaProfessor(){
    	int idProf = Integer.parseInt(idProfessor.getText());
    	List<String> alunos = pDao.getVinculoAluno(idProf);
    	listaRelatorio.clear();
    	data.clear();
    	for(String a: alunos){
    		Relatorio rel = new Relatorio();
    		rel.setNome(a);
    		listaRelatorio.add(rel);
    	}
    	data.addAll(listaRelatorio);
    }
    
    @FXML
	protected void initialize(){
		try{
			// Set up the table data
	        coluna_nome.setCellValueFactory(
	            new PropertyValueFactory<Relatorio,String>("nome")
	        );	 
	        
	        data = FXCollections.observableArrayList();
	        tabela_consulta.setItems(data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
