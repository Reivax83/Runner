// Runner de Xavier Bourguet

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main2 extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Demo");
        /*Group root = new Group();
        Scene scene = new Scene(root, 600, 400);*/
        Text info = new Text(0, 150, " Appyez sur espace pour accéder au score");
        info.setFont(new Font(30));
        info.setFill(Color.RED);

        Group root2 = new Group();
        GameScene stage = new GameScene(root2, 600, 300,800,100, info);

        Text text = new Text(0, 150, "Appuyez sur espace pour commencer");
        text.setFont(new Font(35));
        text.setFill(Color.RED);



        Text score = new Text(0, 150, "Votre score : " + (char)stage.getHero().getX());
        score.setFont(new Font(35));
        score.setFill(Color.RED);

        root2.getChildren().add(stage.getLeftBackground().getStaticView());
        root2.getChildren().add(stage.getRightBackground().getStaticView());
        root2.getChildren().add(text);

        //root2.getChildren().add(stage.getListeBad().get(0).getImage());

        primaryStage.setScene(stage);
        stage.setOnKeyPressed( event -> {
            System.out.println("Press");
            if(stage.getGameStart()==0 && stage.getGameEnd()==0) {
                stage.setGameStart(1);
                updateStart(root2, stage, text);
                //press space to start
                System.out.println("Start");
            }
            else {
                if (stage.getGameEnd() == 1 && stage.getGameStart() == 1) {
                    stage.setGameStart(0);
                    updateEnd(root2, stage);
                    root2.getChildren().remove(info);
                    root2.getChildren().add(score);
                    System.out.println("retour menu ou score");
                }
                else{
                    if(stage.getGameStart() == 0 && stage.getGameEnd() == 1) {
                        //retour menu recrée tout

                        root2.getChildren().remove(score);
                        stage.setGameEnd(0);
                        stage.reset();
                        root2.getChildren().add(text);
                        System.out.println("attente du redépart ou retour menu de départ");
                    /*root2 = new Group();
                    stage = new GameScene(root2, 600, 300,800,0);
                    root2.getChildren().add(stage.getLeftBackground().getStaticView());
                    root2.getChildren().add(stage.getRightBackground().getStaticView());*/
                    }
                }
            }
        });
        stage.setOnMouseClicked( (event)->{
            System.out.println("Jump");
            stage.getHero().jump();
        });
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
        // write your code here


    }

    public void updateStart(Group root2, GameScene stage, Text text){
        root2.getChildren().remove(text);
        root2.getChildren().add(stage.getHero().getImage());
        root2.getChildren().add(stage.getBad().getImage());
        root2.getChildren().add(stage.getHero().getAffichageLife());

        stage.getTimer().start();
        stage.getTimerDefilement().start();
    }
    public void updateEnd(Group root2, GameScene stage){


        root2.getChildren().remove(stage.getHero().getImage());
        root2.getChildren().remove(stage.getBad().getImage());
        root2.getChildren().remove(stage.getHero().getAffichageLife());
    }
}
