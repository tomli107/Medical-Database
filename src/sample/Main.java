package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;

import java.util.*;

public class Main extends Application {

    ObservableList<PATIENT> records = FXCollections.observableArrayList();
    private final TableView<PATIENT> table = new TableView();

    @Override
    public void start(final Stage primaryStage) {
        parseData();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        final Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Scene loginScene = new Scene(grid, 300, 250);

        grid.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    btn.fire();
                }
            }
        });

        primaryStage.setScene(loginScene);
        primaryStage.show();
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                if (userTextField.getCharacters().toString().equals("tom") && pwBox.getCharacters().toString().equals("tom")) {
                    // next scene
                    mainStart(primaryStage);
                    primaryStage.setResizable(false);
                    primaryStage.setTitle("Patient Records");
                }
                else if (!userTextField.getCharacters().equals("tom") && !pwBox.getCharacters().equals("tom")){
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Incorrect username or password");
                }
            }
        });


    }

    public void parseData(){
        try {

            File fXmlFile =  new File("records.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Record");

            for (int i = 0; i < nList.getLength(); i++) {

                org.w3c.dom.Node nNode = nList.item(i);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                    Element pat = (Element) nNode;

                    String temp_last_name = pat.getElementsByTagName("LAST_NAME").item(0).getTextContent();
                    String temp_first_name= pat.getElementsByTagName("FIRST_NAME").item(0).getTextContent();
//                    int temp_base = Integer.parseInt(pat.getElementsByTagName("CHART").item(0).getTextContent());
//                    int temp_index = Integer.parseInt(pat.getElementsByTagName("INDEX").item(0).getTextContent());
//                    int temp_phn = Integer.parseInt(pat.getElementsByTagName("PHN").sub);
//                    int temp_day = Integer.parseInt(pat.getElementsByTagName("DOB").toString().substring(2,4) );
//                    int temp_month = Integer.parseInt(pat.getAttribute("DOB").substring(0,1));
//                    int temp_year = Integer.parseInt(pat.getAttribute("CHART").substring(6,9));

                    PATIENT temp = new PATIENT(temp_last_name,temp_first_name,0,0,0,0,0,0);
                    records.add(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainStart(Stage stage) {
        table.setPrefSize(690, 550);
        table.setEditable(false);

        Scene scene = new Scene(new Group());
        stage.setWidth(800);
        stage.setHeight(600);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMaxWidth(150);
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellFactory(new PropertyValueFactory<>("first_name"));
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMaxWidth(150);
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellFactory(new PropertyValueFactory<>("last_name"));
        TableColumn phnCol = new TableColumn("PHN");
        phnCol.setMinWidth(100);
        phnCol.setMaxWidth(100);
        phnCol.setCellFactory(new PropertyValueFactory<>("phn"));
        TableColumn chartCol = new TableColumn("Chart");
        TableColumn baseCol = new TableColumn("Base");
        baseCol.setMaxWidth(60);
        baseCol.setMinWidth(60);
        baseCol.setCellFactory(new PropertyValueFactory<>("base"));
        TableColumn indexCol = new TableColumn("Index");
        indexCol.setMaxWidth(50);
        indexCol.setMinWidth(50);
        indexCol.setCellFactory(new PropertyValueFactory<>("index"));
        TableColumn dobCol = new TableColumn("D.O.B");
        TableColumn dayCol = new TableColumn("day");
        dayCol.setMinWidth(60);
        dayCol.setMaxWidth(60);
        dayCol.setCellFactory(new PropertyValueFactory<>("day"));
        TableColumn monthCol = new TableColumn("month");
        monthCol.setMaxWidth(60);
        monthCol.setMinWidth(60);
        monthCol.setCellFactory(new PropertyValueFactory<>("month"));
        TableColumn yearCol = new TableColumn("year");
        yearCol.setMaxWidth(60);
        yearCol.setMinWidth(60);
        yearCol.setCellFactory(new PropertyValueFactory<>("year"));

        chartCol.getColumns().addAll(baseCol, indexCol);
        dobCol.getColumns().addAll(dayCol, monthCol, yearCol);

        table.setItems(records);
        table.getColumns().addAll(lastNameCol, firstNameCol, phnCol, chartCol, dobCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
