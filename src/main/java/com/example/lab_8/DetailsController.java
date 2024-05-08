package com.example.lab_8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class DetailsController {

    Parent root;
    Stage stage;
    Scene scene;

    ArrayList<pokemon> pokeList,favList;
    String pokename;
    pokemon thisPoke;

    @FXML
    public Label desc;

    @FXML
    public Label id;

    @FXML
    public Label nameMain;

    @FXML
    public Label type1;
    @FXML
    public Label type2;
    @FXML
    public Label height;

    @FXML
    public Label weight;

    @FXML
    public ImageView pokepic;

    @FXML
    public ImageView ev1;
    @FXML
    public ImageView ev2;

    @FXML
    public ImageView ev3;

    @FXML
    public Button heart;

    @FXML
    public Label cat;

    @FXML
    public Pane p1;

    @FXML
    public Pane p2;
    @FXML
    public Pane p3;
    @FXML
    public Pane p4;

    @FXML
    public ImageView tog1;

    @FXML
    public ImageView tog2;

    @FXML
    public ImageView tog3;


    Boolean faved;

    public DetailsController(){
        pokeList = new ArrayList<pokemon>();
        favList = new ArrayList<pokemon>();
    }
    @FXML
    public void Hearting(ActionEvent e){
        if(!faved){
            faved=true;
            favList.add(thisPoke);
            heart.setStyle("-fx-background-color:  #ff166b; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.2c6.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\" ");}
        else{
            faved=false;
            favList.remove(thisPoke);
            heart.setStyle("-fx-background-color:  #ffffff; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.2c6.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\" ");
        }
    }

    @FXML
    public void switchToDash(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        //loader.setController(this);
        root = loader.load();
        HelloController sc = loader.getController();
        sc.pokeList= this.pokeList;
        sc.favList= this.favList;

        stage = (Stage) nameMain.getScene().getWindow();
        scene =  new Scene(root);

        String cssFile = this.getClass().getResource("cards.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        sc.populateGrid();
        stage.setScene(scene);
        stage.show();
    }



    public void init(ArrayList<pokemon> pk, ArrayList<pokemon> favpk, String name){
        this.pokeList = pk;
        this.pokename = name;
        this.favList=favpk;

        for(pokemon pok:pokeList){
            if(pok.getName().equals(name)){
                this.thisPoke= pok;

                if(name.equals("togepi")){
                    tog1.setVisible(true);
                    tog2.setVisible(true);
                    tog3.setVisible(true);
                }
                else{
                    tog1.setVisible(false);
                    tog2.setVisible(false);
                    tog3.setVisible(false);
                }

                if(Objects.equals(pok.getType1(), "grass")){
                    this.p1.setStyle("-fx-background-color:  #B2D0CD; -fx-background-radius: 20;");
                    this.p2.setStyle("-fx-background-color:  #B2D0CD; -fx-background-radius: 20;");
                    this.p3.setStyle("-fx-background-color:  #B2D0CD; -fx-background-radius: 20;");
                    this.p4.setStyle("-fx-background-color:  #B2D0CD; -fx-background-radius: 20;");
                }
                else if(Objects.equals(pok.getType1(), "water")){
                    this.p1.setStyle("-fx-background-color:  #C2D6E7; -fx-background-radius: 20;");
                    this.p2.setStyle("-fx-background-color:  #C2D6E7; -fx-background-radius: 20;");
                    this.p3.setStyle("-fx-background-color:  #C2D6E7; -fx-background-radius: 20;");
                    this.p4.setStyle("-fx-background-color:  #C2D6E7; -fx-background-radius: 20;");
                }
                else if(Objects.equals(pok.getType1(), "fire")){
                    System.out.println("reached");
                    this.p1.setStyle("-fx-background-color:  #FABCB8; -fx-background-radius: 20;");
                    this.p2.setStyle("-fx-background-color:  #FABCB8; -fx-background-radius: 20;");
                    this.p3.setStyle("-fx-background-color:  #FABCB8; -fx-background-radius: 20;");
                    this.p4.setStyle("-fx-background-color:  #FABCB8; -fx-background-radius: 20;");
                }
                else if(Objects.equals(pok.getType1(), "electric")){
                    System.out.println("reached");
                    this.p1.setStyle("-fx-background-color:  #F8EDB8; -fx-background-radius: 20;");
                    this.p2.setStyle("-fx-background-color:  #F8EDB8; -fx-background-radius: 20;");
                    this.p3.setStyle("-fx-background-color:  #F8EDB8; -fx-background-radius: 20;");
                    this.p4.setStyle("-fx-background-color:  #F8EDB8; -fx-background-radius: 20;");
                }
                else if(Objects.equals(pok.getType1(), "fairy")){
                    System.out.println("reached");
                    this.p1.setStyle("-fx-background-color:   #FCE4EC; -fx-background-radius: 20;");
                    this.p2.setStyle("-fx-background-color:   #FCE4EC; -fx-background-radius: 20;");
                    this.p3.setStyle("-fx-background-color:   #FCE4EC; -fx-background-radius: 20;");
                    this.p4.setStyle("-fx-background-color:   #FCE4EC; -fx-background-radius: 20;");
                }

                Image im = new Image(getClass().getResource("images/" + pok.pokeid + ".png").toExternalForm());
                this.pokepic.setImage(im);
                im = new Image(getClass().getResource("images/" + pok.gen1 + ".png").toExternalForm());
                this.ev1.setImage(im);
                im = new Image(getClass().getResource("images/" + pok.gen2 + ".png").toExternalForm());
                this.ev2.setImage(im);
                im = new Image(getClass().getResource("images/" + pok.gen3 + ".png").toExternalForm());
                this.ev3.setImage(im);

                this.desc.setText(pok.getDesc());
                this.id.setText(pok.getPokeid());
                this.type1.setText(pok.getType1().toUpperCase(Locale.ROOT));
                this.type2.setText(pok.getType2().toUpperCase(Locale.ROOT));
                this.height.setText(pok.getHeight());
                this.weight.setText(pok.getWeight());
                this.nameMain.setText(pok.getName().toUpperCase(Locale.ROOT));
                this.cat.setText(pok.getCategory().toUpperCase(Locale.ROOT));

                this.faved=false;
            }

        }

        for(pokemon pok:favList){
            if(pok.getName().equals(name)){
                this.faved=true;

                heart.setStyle("-fx-background-color:  #ff166b; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.2c6.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\" ");
            }
        }

    }
}
