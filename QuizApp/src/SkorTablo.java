package proje_Esam_Halimeh;

import java.io.Serializable;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SkorTablo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private double skor1,skor2;
	
	
	public SkorTablo(String name, double d, double e) {
		this.name = name;
		this.skor1 = d;
		this.skor2 = e;
	}
	
	public SkorTablo() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSkor1() {
		return skor1;
	}

	public void setSkor1(double skor1) {
		this.skor1 = skor1;
	}

	public double getSkor2() {
		return skor2;
	}

	public void setSkor2(double skor2) {
		this.skor2 = skor2;
	}
	
	@SuppressWarnings("unchecked")
	public void tablo(Exercise exrc) {            // yüksek skor tablosu için ayrı pencere oluşturdum. ve burada yazdırıyorum
	    TableView<SkorTablo> table = new TableView<>();
	    TableColumn<SkorTablo, String> nameClm = new TableColumn<>("Name");
	    TableColumn<SkorTablo, Double> clm1 = new TableColumn<>("Test Skor");
	    TableColumn<SkorTablo, Double> clm2 = new TableColumn<>("Hiz Skor");
	    nameClm.setMinWidth(100);
	    clm1.setMinWidth(50);
	    clm2.setMinWidth(50);

	    nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
	    clm1.setCellValueFactory(new PropertyValueFactory<>("skor1"));
	    clm2.setCellValueFactory(new PropertyValueFactory<>("skor2"));

	    table.getColumns().addAll(nameClm, clm1, clm2);
	    
	    LinkedList<SkorTablo> list = exrc.getSkorlar();

	    ObservableList<SkorTablo> data = FXCollections.observableList(list);

	    table.setItems(data);
	    Stage newStage = new Stage();
	    Scene newScene = new Scene(table, 300, 300);
	    newStage.setTitle("Yüksek Skor Tablosu");
	    newStage.setScene(newScene);
	    newStage.show();
	}

	@Override
	public String toString() {
		return  name + skor1 + skor2;
	}
}

