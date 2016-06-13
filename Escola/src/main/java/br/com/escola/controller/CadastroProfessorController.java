package br.com.escola.controller;

import java.io.IOException;

import br.com.escola.dao.ProfessorDAO;
import br.com.escola.entity.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroProfessorController {

	@FXML private TextField nome;
	
	@FXML
	public void cadastrar(){
		try{
			ProfessorDAO pDao = new ProfessorDAO();
			Professor p = new Professor();
			p.setNome(nome.getText());
			pDao.salvar(p);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	public void voltar(ActionEvent event) throws IOException{
		Parent newPageParent = FXMLLoader.load(getClass().getResource("../view/InicioView.fxml"));
		Scene newPageScene = new Scene(newPageParent);
		Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		newStage.hide();
		newStage.setScene(newPageScene);
		newStage.show();
	}
	
}
