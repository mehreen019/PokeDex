package com.example.lab_8;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

public class HelloController {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    public Button searchbtn;

    @FXML
    public Label mainn;

    @FXML
    public TextField searchbar;

    @FXML
    public GridPane gd;

    final String imgurl = "E:\\coding\\210041219\\2-2 codes\\CSE 4402\\lab_8\\src\\main\\java\\com\\example\\img\\";
    String switchType;




    ArrayList<pokemon> pokeList,favList;
    ArrayList<String> inputt;


    public HelloController(){

        pokeList = new ArrayList<pokemon>();
        favList = new ArrayList<pokemon>();

        Image im = new Image(getClass().getResource("images/search.png").toExternalForm());
        ImageView img = new ImageView(im);
        /*searchbtn = new Button();
        searchbtn.setGraphic(img);
        searchbtn.setPrefWidth(27.0);
        searchbtn.setPrefHeight(26.0);
        searchbtn.setLayoutY(21.0);
        searchbtn.setLayoutX(1052.0);
        searchbtn.setMnemonicParsing(false);
        searchbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                populateBySearch(actionEvent);
            }
        });*/

    }

    @FXML
    public void populateBySearch(ActionEvent e){
        int row=0, col=0;

        String text = searchbar.getText();

        gd.getChildren().clear();

        for(pokemon pok:pokeList) {

            if(pok.getName().equals(text.toLowerCase()) || pok.getType1().equals(text.toLowerCase()) || pok.getType2().equals(text.toLowerCase())) {

                Pane newpane = new Pane();
                newpane.setPrefHeight(157.0);
                newpane.setPrefHeight(227.0);
                //newpane.setBackground(Background.fill());
                //newpane.setStyle("-fx-background-color: var(--grassbg); -fx-background-radius: 20");
                if (Objects.equals(pok.getType1(), "grass")) newpane.getStyleClass().add("grassbg");
                else if (Objects.equals(pok.getType1(), "water")) newpane.getStyleClass().add("waterbg");
                else if (Objects.equals(pok.getType1(), "fire")) newpane.getStyleClass().add("firebg");

                //ImageView img = new ImageView(imgurl+"Bulbasaur"+".jpg")
                Image im = new Image(getClass().getResource("images/" + pok.pokeid + ".png").toExternalForm());

                ImageView img = new ImageView(im);
                //img.setImage(im);
                img.setFitHeight(138.0);
                img.setFitWidth(158.0);
                img.setLayoutX(118.0);
                img.setLayoutY(6.0);
                img.setPreserveRatio(true);
                img.setPickOnBounds(true);


                Label lbid = new Label();
                lbid.setText('#'+pok.pokeid);
                lbid.setPrefHeight(26.0);
                lbid.setPrefWidth(84.0);
                lbid.setLayoutX(14.0);
                lbid.setLayoutY(6.0);
                lbid.setStyle("-fx-font-family: Verdana; -fx-background-color: 00000");
                lbid.setOpacity(0.47);
                lbid.setTextFill(Color.WHITE);
                lbid.setFont(new Font("Verdana", 15.0));

                Label lbname = new Label();
                lbname.setText(pok.name.toUpperCase(Locale.ROOT));
                lbname.setPrefHeight(26.0);
                lbname.setPrefWidth(92.0);
                lbname.setLayoutX(14.0);
                lbname.setLayoutY(39.0);
                //lbname.setStyle("-fx-background-color: #c5eaab; -fx-font-family: Verdana; -fx-background-radius: 10; -fx-alignment: center");
                if (Objects.equals(pok.getType1(), "grass")) lbname.getStyleClass().add("grassname");
                else if (Objects.equals(pok.getType1(), "water")) lbname.getStyleClass().add("watername");
                else if (Objects.equals(pok.getType1(), "fire")) lbname.getStyleClass().add("firename");

                Label lbtype = new Label();
                lbtype.setText(pok.type1);
                lbtype.setPrefHeight(26.0);
                lbtype.setPrefWidth(84.0);
                lbtype.setLayoutX(14.0);
                lbtype.setLayoutY(75.0);
                //lbtype.setStyle("-fx-background-color: #B2D0CD; -fx-font-family: Verdana; -fx-background-radius: 20; -fx-alignment: center;");
                if (Objects.equals(pok.getType1(), "grass")) lbtype.getStyleClass().add("grasstype");
                else if (Objects.equals(pok.getType1(), "water")) lbtype.getStyleClass().add("watertype");
                else if (Objects.equals(pok.getType1(), "fire")) lbtype.getStyleClass().add("firetype");

                Button btn = new Button();
                btn.setText("Details");
                btn.setLayoutX(14.0);
                btn.setLayoutY(111.0);
                btn.setPrefHeight(25.0);
                btn.setPrefWidth(84.0);
                btn.setMnemonicParsing(false);
                btn.setStyle("-fx-background-radius: 10;");
                btn.setId(pok.name);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            switchToDetails(event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                newpane.getChildren().add(img);
                newpane.getChildren().add(lbname);
                newpane.getChildren().add(lbtype);
                newpane.getChildren().add(lbid);
                newpane.getChildren().add(btn);


                gd.add(newpane, col, row);
                col++;
                if (col == 4) {
                    row++;
                    col = 0;
                }
            }
        }
    }

    public void switchToDetails(ActionEvent e) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view-2.fxml"));
        //loader.setController(this);
        root = loader.load();

        final Node source = (Node) e.getSource();
        String id = source.getId();

        for(pokemon pok:pokeList) {
            if (pok.name.equals(id)) {

                if (Objects.equals(pok.getType1(), "grass")) {
                    root.setStyle("-fx-background-color: #38776F;");
                    System.out.println("ok");
                } else if (Objects.equals(pok.getType1(), "water")) root.setStyle("-fx-background-color: #3B8CCE;");
                else if (Objects.equals(pok.getType1(), "fire")) root.setStyle("-fx-background-color: #CE3E3E;");
            }
        }

        DetailsController sc = loader.getController();
        sc.init(pokeList, favList, id);
        stage = (Stage) gd.getScene().getWindow();
        scene =  new Scene(root);

        String cssFile = this.getClass().getResource("cards.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void populateFav(ActionEvent e){
        int row=0, col=0;
        Collections.sort(favList, new Comparator<pokemon>() {
            @Override
            public int compare(pokemon o1, pokemon o2) {
                return o1.pokeid.compareTo(o2.pokeid);
            }
        });

        gd.getChildren().clear();

        for(pokemon pok:favList) {

            Pane newpane = new Pane();
            newpane.setPrefHeight(157.0);
            newpane.setPrefHeight(227.0);
            //newpane.setBackground(Background.fill());
            //newpane.setStyle("-fx-background-color: var(--grassbg); -fx-background-radius: 20");
            if(Objects.equals(pok.getType1(), "grass")) newpane.getStyleClass().add("grassbg");
            else if(Objects.equals(pok.getType1(), "water")) newpane.getStyleClass().add("waterbg");
            else if(Objects.equals(pok.getType1(), "fire")) newpane.getStyleClass().add("firebg");

            //ImageView img = new ImageView(imgurl+"Bulbasaur"+".jpg")
            Image im = new Image(getClass().getResource("images/" + pok.pokeid + ".png").toExternalForm());

            ImageView img = new ImageView(im);
            //img.setImage(im);
            img.setFitHeight(138.0);
            img.setFitWidth(158.0);
            img.setLayoutX(118.0);
            img.setLayoutY(6.0);
            img.setPreserveRatio(true);
            img.setPickOnBounds(true);


            Label lbid = new Label();
            lbid.setText('#'+pok.pokeid);
            lbid.setPrefHeight(26.0);
            lbid.setPrefWidth(84.0);
            lbid.setLayoutX(14.0);
            lbid.setLayoutY(6.0);
            lbid.setStyle("-fx-font-family: Verdana; -fx-background-color: 00000");
            lbid.setOpacity(0.47);
            lbid.setTextFill(Color.WHITE);
            lbid.setFont(new Font("Verdana", 15.0));

            Label lbname = new Label();
            lbname.setText(pok.name.toUpperCase(Locale.ROOT));
            lbname.setPrefHeight(26.0);
            lbname.setPrefWidth(92.0);
            lbname.setLayoutX(14.0);
            lbname.setLayoutY(39.0);
            //lbname.setStyle("-fx-background-color: #c5eaab; -fx-font-family: Verdana; -fx-background-radius: 10; -fx-alignment: center");
            if(Objects.equals(pok.getType1(), "grass")) lbname.getStyleClass().add("grassname");
            else if(Objects.equals(pok.getType1(), "water")) lbname.getStyleClass().add("watername");
            else if(Objects.equals(pok.getType1(), "fire")) lbname.getStyleClass().add("firename");

            Label lbtype = new Label();
            lbtype.setText(pok.type1);
            lbtype.setPrefHeight(26.0);
            lbtype.setPrefWidth(84.0);
            lbtype.setLayoutX(14.0);
            lbtype.setLayoutY(75.0);
            //lbtype.setStyle("-fx-background-color: #B2D0CD; -fx-font-family: Verdana; -fx-background-radius: 20; -fx-alignment: center;");
            if(Objects.equals(pok.getType1(), "grass")) lbtype.getStyleClass().add("grasstype");
            else if(Objects.equals(pok.getType1(), "water")) lbtype.getStyleClass().add("watertype");
            else if(Objects.equals(pok.getType1(), "fire")) lbtype.getStyleClass().add("firetype");

            Button btn = new Button();
            btn.setText("Details");
            btn.setLayoutX(14.0);
            btn.setLayoutY(111.0);
            btn.setPrefHeight(25.0);
            btn.setPrefWidth(84.0);
            btn.setMnemonicParsing(false);
            btn.setStyle("-fx-background-radius: 10;");
            btn.setId(pok.name);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        switchToDetails(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            newpane.getChildren().add(img);
            newpane.getChildren().add(lbname);
            newpane.getChildren().add(lbtype);
            newpane.getChildren().add(lbid);
            newpane.getChildren().add(btn);


            gd.add(newpane, col, row);
            col++;
            if(col==4){
                row++;
                col=0;
            }
        }
    }

    public void populateGrid() throws IOException {

        gd.getChildren().clear();

        int row=0, col=0;
        Collections.sort(pokeList, new Comparator<pokemon>() {
            @Override
            public int compare(pokemon o1, pokemon o2) {
                return o1.pokeid.compareTo(o2.pokeid);
            }
        });

        for(pokemon pok:pokeList) {

            Pane newpane = new Pane();
            newpane.setPrefHeight(157.0);
            newpane.setPrefHeight(227.0);
            //newpane.setBackground(Background.fill());
            //newpane.setStyle("-fx-background-color: var(--grassbg); -fx-background-radius: 20");
            if(Objects.equals(pok.getType1(), "grass")) newpane.getStyleClass().add("grassbg");
            else if(Objects.equals(pok.getType1(), "water")) newpane.getStyleClass().add("waterbg");
            else if(Objects.equals(pok.getType1(), "fire")) newpane.getStyleClass().add("firebg");

            //ImageView img = new ImageView(imgurl+"Bulbasaur"+".jpg")
            Image im = new Image(getClass().getResource("images/" + pok.pokeid + ".png").toExternalForm());

            ImageView img = new ImageView(im);
            //img.setImage(im);
            img.setFitHeight(138.0);
            img.setFitWidth(158.0);
            img.setLayoutX(118.0);
            img.setLayoutY(6.0);
            img.setPreserveRatio(true);
            img.setPickOnBounds(true);


            Label lbid = new Label();
            lbid.setText('#'+pok.pokeid);
            lbid.setPrefHeight(26.0);
            lbid.setPrefWidth(84.0);
            lbid.setLayoutX(14.0);
            lbid.setLayoutY(6.0);
            lbid.setStyle("-fx-font-family: Verdana; -fx-background-color: 00000");
            lbid.setOpacity(0.47);
            lbid.setTextFill(Color.WHITE);
            lbid.setFont(new Font("Verdana", 15.0));

            Label lbname = new Label();
            lbname.setText(pok.name.toUpperCase(Locale.ROOT));
            lbname.setPrefHeight(26.0);
            lbname.setPrefWidth(92.0);
            lbname.setLayoutX(14.0);
            lbname.setLayoutY(39.0);
            //lbname.setStyle("-fx-background-color: #c5eaab; -fx-font-family: Verdana; -fx-background-radius: 10; -fx-alignment: center");
            if(Objects.equals(pok.getType1(), "grass")) lbname.getStyleClass().add("grassname");
            else if(Objects.equals(pok.getType1(), "water")) lbname.getStyleClass().add("watername");
            else if(Objects.equals(pok.getType1(), "fire")) lbname.getStyleClass().add("firename");

            Label lbtype = new Label();
            lbtype.setText(pok.type1);
            lbtype.setPrefHeight(26.0);
            lbtype.setPrefWidth(84.0);
            lbtype.setLayoutX(14.0);
            lbtype.setLayoutY(75.0);
            //lbtype.setStyle("-fx-background-color: #B2D0CD; -fx-font-family: Verdana; -fx-background-radius: 20; -fx-alignment: center;");
            if(Objects.equals(pok.getType1(), "grass")) lbtype.getStyleClass().add("grasstype");
            else if(Objects.equals(pok.getType1(), "water")) lbtype.getStyleClass().add("watertype");
            else if(Objects.equals(pok.getType1(), "fire")) lbtype.getStyleClass().add("firetype");

            Button btn = new Button();
            btn.setText("Details");
            btn.setLayoutX(14.0);
            btn.setLayoutY(111.0);
            btn.setPrefHeight(25.0);
            btn.setPrefWidth(84.0);
            btn.setMnemonicParsing(false);
            btn.setStyle("-fx-background-radius: 10;");
            btn.setId(pok.name);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        switchToDetails(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            newpane.getChildren().add(img);
            newpane.getChildren().add(lbname);
            newpane.getChildren().add(lbtype);
            newpane.getChildren().add(lbid);
            newpane.getChildren().add(btn);


            gd.add(newpane, col, row);
            col++;
            if(col==4){
                row++;
                col=0;
            }
        }

    }

    public void establishConnection() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection link = connect.getConnection();
        String query = "SELECT * FROM pokedex.pokemon";

        try {
            Statement statement = link.createStatement();
            ResultSet outt = statement.executeQuery(query);
            ResultSetMetaData outtm = outt.getMetaData();


            while (outt.next()){
                System.out.println(outt);


                String n = outt.getString("name");
                String id= outt.getString("pokeid");
                String t1= outt.getString("type1");
                String t2=  outt.getString("type2");
                String desc=outt.getString("desc");
                String height= outt.getString("height");
                String weight= outt.getString("weight");
                String g1= outt.getString("gen1");
                String g2= outt.getString("gen2");
                String g3= outt.getString("gen3");
                String category= outt.getString("category");

                /*String row = "";

                for(int i=1; i<= outtm.getColumnCount(); i++){
                    //inputt.add(outt.getString(i));
                    row+= outt.getString(i)+ " ";
                }

                System.out.println(row);*/
                pokemon newpoke = new pokemon(id, n, t1, t2, desc, height, weight,g1,g2,g3,category);
                System.out.println(n + id + t1);
                pokeList.add(newpoke);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}