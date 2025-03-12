package proje_Esam_Halimeh;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
	
	private Admin admin = new Admin("");
	private Pane root = new Pane();
	private Alert alert = new Alert(AlertType.INFORMATION);
	private Exercise exercise;
	private ArrayList<Question> Qs = new ArrayList<>();
	private Child child;
	private int min =0;
	private int sec =0;
	
	@Override
	public void start(Stage primaryStage) {
		
	    admin = Data.loadData();       // .ser dosyasındaki datayı okuma 
	    if (admin == null) {          // eğer dosya boş ise admin tanımlanır
	        Label label1 = new Label("Başlamak için Admin hesabı oluşturun:");
	        label1.setLayoutX(20);
	        label1.setLayoutY(20);
	        Label label2 = new Label("UserName:");
	        label2.setLayoutX(20);
	        label2.setLayoutY(60);
	        TextField tf = new TextField();
	        tf.setLayoutX(90);
	        tf.setLayoutY(60);
	        Button btn = new Button("Oluştur");
	        btn.setLayoutX(250);
	        btn.setLayoutY(60);
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                admin = new Admin(tf.getText());
	                showMainMenu();
	            }
	        });
	        root.getChildren().addAll(label1,label2,tf,btn);
	    }else {
	    	showMainMenu();
	    }
	    alert.setTitle("Bilgi");
	    primaryStage.setOnCloseRequest(event -> {          // program close sekmesinden kapandığı zaman bilgileri kaydetme
	    	Data.saveData(admin);
	    });
	    Scene scene = new Scene(root, 350, 350);
	    primaryStage.setTitle(" çarpım tablosu alıştırma programı");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	}
	
	private void showMainMenu() {      // ana menu
		root.getChildren().clear();
		Label intro = new Label("yapmak istediğiniz işlemi seçin:");
		Button loginButton = new Button(" -> hesaba giriş yap");
		Button crtBtn = new Button(" -> Çocuk hesabı oluştur");
		Button btn3 = new Button("-> Kaydet ve programı kapat");
		VBox vbox = new VBox();
		vbox.setLayoutX(20);
		vbox.setLayoutY(20);
		vbox.setSpacing(10);
		vbox.getChildren().addAll(intro,loginButton,crtBtn,btn3);
		root.getChildren().add(vbox);
		
		loginButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				root.getChildren().clear();
				Label isim = new Label("UserName:");
				isim.setLayoutX(20);
				isim.setLayoutY(20);
				TextField tf = new TextField();
				tf.setLayoutX(90);
				tf.setLayoutY(20);
				Button btn = new Button("Giriş");
		        btn.setLayoutX(250);
		        btn.setLayoutY(20);
		        root.getChildren().addAll(isim,tf,btn);
		        btn.setOnAction(new EventHandler<>() {
					@Override
					public void handle(ActionEvent arg0) {
						loginOp(tf.getText());
					}
		        });
			}
		});
		crtBtn.setOnAction(new EventHandler<>() {     // çocuk hesabı oluşturma metodu
			@Override
			public void handle(ActionEvent arg0) {
				root.getChildren().clear();
				Label lab1 = new Label("UserName");
				lab1.setLayoutX(20);
				lab1.setLayoutY(20);
				Button btn = new Button("Oluştur");
				TextField tf1 = new TextField();
				tf1.setLayoutX(90);
				tf1.setLayoutY(20);
				btn.setLayoutX(250);
				btn.setLayoutY(20);
				root.getChildren().addAll(lab1,tf1,btn);
				
				btn.setOnAction(new EventHandler<>() {
					@Override
					public void handle(ActionEvent arg0) {
					Child ch = new Child(tf1.getText(),admin);	
					admin.addChild(ch);
					showMainMenu();	
					}
				});
			}
			
		});
		btn3.setOnAction(new EventHandler<>() {     // programı kapat düğmesine basıldığı zaman datayı dosyaya yazma
			@Override
			public void handle(ActionEvent arg0) {
				Data.saveData(admin);
				Platform.exit();
			}
		});
	}
	
	public void loginOp(String str) {     // admin ve çocuk login işlemleri
		if(admin.adminLogin(str)) {
			root.getChildren().clear();
			adminOperation();
		}else {
			if(admin.childLogin(str)) {
				child = admin.getChild(str);
				if(admin.getExercises().size()==0) {
					alert.setHeaderText("işleme devam edilemez");
					alert.setContentText("Admin herhangi bir alıştırma tanımlamadı!!");
					alert.showAndWait();
					showMainMenu();
				}else {
					exerciseOperation();
				}
			}else {
				alert.setHeaderText("Invalid Input");
				alert.setContentText(str + " adına kayıtlı kullanıcı yok");
				alert.showAndWait();
				MenuButton();
			}
		}
	}
	
	public void adminOperation() {        // admin işlemleri
		root.getChildren().clear();
		Label label = new Label("Yapmak istediğiniz işlemi seçin");
		Button Rbtn = new Button("Rapor oluştur");
		Button ayarBtn = new Button("Alıştırma tanımla");
		Button tabloBtn = new Button("Yüksek skor tablosu oluştur");
		VBox vbox = new VBox();
		vbox.setLayoutX(20);
		vbox.setLayoutY(20);
		vbox.setSpacing(10);
		vbox.getChildren().addAll(label,Rbtn,ayarBtn,tabloBtn);
		root.getChildren().add(vbox);
		
		Rbtn.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				admin.writeToExcel();
				showMainMenu();
			}
			
		});
		
		ayarBtn.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				root.getChildren().clear();
				Label Elab = new Label("Alıştırma ismi veya ID");
				Elab.setLayoutX(20);
				Elab.setLayoutY(20);
				TextField Etf = new TextField();
				Etf.setLayoutX(160);
				Etf.setLayoutY(20);
				Label labN=new Label("Soru sayısı:");
				labN.setLayoutX(20);
				labN.setLayoutY(50);
				TextField Ntf = new TextField();
				Ntf.setLayoutX(160);
				Ntf.setLayoutY(50);
				Label labMin = new Label("minimum sayı:");
				labMin.setLayoutX(20);
				labMin.setLayoutY(80);
				TextField Mintf = new TextField();
				Mintf.setLayoutX(160);
				Mintf.setLayoutY(80);
				Label labMax=new Label("maximum sayı:");
				labMax.setLayoutX(20);
				labMax.setLayoutY(110);
				TextField Maxtf = new TextField();
				Maxtf.setLayoutX(160);
				Maxtf.setLayoutY(110);
				Button doneButton = new Button("Kaydet");
				doneButton.setLayoutX(150);
				doneButton.setLayoutY(150);
				root.getChildren().addAll(Elab,Etf,labN,Ntf,labMin,Mintf,Maxtf,labMax,doneButton);
				doneButton.setOnAction(new EventHandler<>() {
					@Override
					public void handle(ActionEvent arg0) {
						admin.initExercise(Etf.getText(),Ntf.getText(),Mintf.getText(),Maxtf.getText());
						Etf.clear();
						Ntf.clear();
						Mintf.clear();
						Maxtf.clear();
						MenuButton();
					}
				});
			}
		});
		
		tabloBtn.setOnAction(new EventHandler<>() {

			@Override
			public void handle(ActionEvent arg0) {
				root.getChildren().clear();
				selectExercise();
				MenuButton();
			}
			
		});
	}
	
	public void exerciseOperation() {       // alıştırma işlemleri
		root.getChildren().clear();
		Label label = new Label("Bir alıştırma seçin:");
		label.setLayoutX(20);
		label.setLayoutY(20);
		root.getChildren().add(label);
		ScrollPane scrol = new ScrollPane();
		scrol.setPrefViewportWidth(200);
		scrol.setPrefViewportHeight(200);
	    scrol.setFitToHeight(true);
	    scrol.setFitToWidth(true);
	    scrol.setLayoutX(30);
	    scrol.setLayoutY(50);
	    root.getChildren().add(scrol);
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		for(Exercise ex : admin.getExercises()) {
			Button btn = new Button("->> " + ex.getExerciseID());
			vbox.getChildren().add(btn);
		}
		scrol.setContent(vbox);
		scrol.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		int i;
		
		for(i=0;i<vbox.getChildren().size();i++) {
			final int index=i;
			Button button = (Button) vbox.getChildren().get(i);
			button.setOnAction(new EventHandler<>() {
				@Override
				public void handle(ActionEvent arg0) {
					doExercise(index);
				}
				
			});
		}
	}
	
	public void doExercise(int index) {
		root.getChildren().clear();
		setExercise(admin.getExercise(index));
		setQs(exercise.getQuestions());
		child.addExerciseToChild(exercise);
		Label timerLabel = new Label();
		timerLabel.setLayoutX(120);
		timerLabel.setLayoutY(20);
		AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000) {
                    sec++;
                    if (sec == 60) {
                        min++;
                        sec = 0;
                    }
                    timerLabel.setText(String.format("Timer: %02d:%02d ", min, sec));
                    lastUpdate = now;
                }
                
            }
            
        };
		Label qLabel = new Label(Qs.get(0).toString());
		qLabel.setLayoutX(20);
		qLabel.setLayoutY(70);
		TextField tf = new TextField();
		tf.setLayoutX(90);
		tf.setLayoutY(70);
		Button nextBtn = new Button("Next");
		nextBtn.setLayoutX(250);
		nextBtn.setLayoutY(70);
		timer.start();
		root.getChildren().addAll(timerLabel,qLabel,tf,nextBtn);
		
		nextBtn.setOnAction(new EventHandler<>() {
			private int index=0;
			private int size = exercise.getQuestions().size();
			@Override
			public void handle(ActionEvent arg0) {
				child.kaydetQ(exercise,sec, min,index,tf.getText());
				if(index < size-1) {
					index++;
					qLabel.setText(Qs.get(index).toString());
					tf.clear();
				}else {
					timer.stop();
					child.endOfExercise(exercise , min,sec);
					setMin(0);
					setSec(0);
					root.getChildren().clear();
					printResult();
					MenuButton();
				}
			}
		});
	}
	
	public void printResult() {
		TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setLayoutX(10);
        textArea.setLayoutY(10);
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(250);
        int index = child.getListOfResults().size()-1;
        textArea.setText(child.viewExerciseResult(index));
        root.getChildren().add(textArea);
	}
	
	public void selectExercise() {
		Label label = new Label("Bir alıştırma seçin:");
		label.setLayoutX(20);
		label.setLayoutY(20);
		Button chBtn = new Button("Seç");
		chBtn.setLayoutX(160);
		chBtn.setLayoutY(50);
		root.getChildren().addAll(chBtn,label);
		ScrollPane scrol = new ScrollPane();
		scrol.setPrefViewportWidth(200);
		scrol.setPrefViewportHeight(200);
	    scrol.setFitToHeight(true);
	    scrol.setFitToWidth(true);
	    scrol.setLayoutX(30);
	    scrol.setLayoutY(50);
	    root.getChildren().add(scrol);
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		for(Exercise p : admin.getExercises()) {
			Button btn = new Button("->> " + p.getExerciseID());
			vbox.getChildren().add(btn);
		}
		scrol.setContent(vbox);
		scrol.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		int i;
		for (i = 0; i < vbox.getChildren().size(); i++) {
		    int index = i;
		    Button button = (Button) vbox.getChildren().get(i);
		    button.setOnAction(event -> {
		        setChild(child);
		        setExercise(admin.getExercise(index));
		        if(exercise.getSkorlar().size() == 0) {
					alert.setHeaderText("işlem gerçekleşemez");
					alert.setContentText("Bu alıştırmayı henüz kimse yapmadı.");
					alert.showAndWait();
				}else {
					exercise.viewTablo();
				}
		    });
		}
	}

	public void MenuButton() {
		Button backButton = new Button("Ana Menuye don");
		backButton.setLayoutX(120);
		backButton.setLayoutY(300);
		root.getChildren().add(backButton);
		backButton.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				showMainMenu();
			}
		});
	}
	
	public void setMin(int min) {
		this.min = min;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}
	

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}
	
	
	public ArrayList<Question> getQs() {
		return Qs;
	}

	public void setQs(ArrayList<Question> qs) {
		Qs = qs;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

