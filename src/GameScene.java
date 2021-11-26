import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameScene extends Scene {
    private Camera pointDeVue;
    private StaticThing leftBackground;
    private StaticThing rightBackground;
    private Hero hero;
    private long tim1 = 0;
    private long tim2 =0;
    private int gameStart =0;
    private int gameEnd =0;
    private Group root;
    private Text info;
    public Foe getBad() {
        return bad;
    }

    public int getGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(int gameEnd) {
        this.gameEnd = gameEnd;
    }

    public void setGameStart(int gameStart) {
        this.gameStart = gameStart;
    }

    public int getGameStart() {
        return gameStart;
    }

    public ArrayList<Foe> getListeBad() {
        return listeBad;
    }

    ArrayList<Foe> listeBad;
    Foe bad;
    AnimationTimer timer = new AnimationTimer(){
        public void handle(long time){
            if(time - tim2 >= 100_000_000){
                tim2 = time;
                hero.update(time, hero.getY(), hero.getPosArrayHero()[3]);
            }
            //hero.update(85);
            /*pointDeVue.update(time);*/
        }
    };
    AnimationTimer timerDefilement = new AnimationTimer() {
        public void handle(long time) {
            if(time - tim1>= 50_000_000){
                tim1 = time;
                update(time);

            }
        }
    };
    public GameScene(Group root, double width, double length, int xcam, int ycam, Text info) {
        super(root, width, length);
        this.root = root;
        pointDeVue = new Camera(xcam,ycam);
        leftBackground = new StaticThing("desert.png", 300, 300);
        rightBackground = new StaticThing("desert.png", 900, 300);

        leftBackground.getStaticView().setViewport(new Rectangle2D(pointDeVue.getX()%800,pointDeVue.getY()%400,800-pointDeVue.getX()%800,400-(pointDeVue.getY())));
        rightBackground.getStaticView().setViewport(new Rectangle2D(0,pointDeVue.getY()%400,(pointDeVue.getX()%800),400-(pointDeVue.getY())));
        rightBackground.getStaticView().setX(800-pointDeVue.getX()%800);
        leftBackground.getStaticView().setX( 0);/*
        rightBackground.getStaticView().setY((pointDeVue.getY()%400));
        leftBackground.getStaticView().setY((pointDeVue.getY()%400));*/


        hero = new Hero("heros.png",20,0,65,100,6,1,50,250);
        bad = new Foe("cactus.png", 0, 0, 63, 130, 1,0, 700,275);
        hero.getAffichageLife().setViewport(new Rectangle2D(0,10,259,40));
        //timer.start();
        //timerDefilement.start();
        this.info = info;

    }

    public void reset(){
        pointDeVue.reset();
        rightBackground.reset(pointDeVue.getX(),pointDeVue.getX()%800,pointDeVue.getY()%400,800-pointDeVue.getX()%800,400-(pointDeVue.getY()));
        leftBackground.reset(0,0,pointDeVue.getY()%400,(pointDeVue.getX()%800),400-(pointDeVue.getY()));
        hero.resetHero();
        bad.resetFoe();
        hero.getAffichageLife().setViewport(new Rectangle2D(0,10,259,40));
        hero.setLife(5);
        update(100);
    }

    public StaticThing getLeftBackground() {
        return leftBackground;
    }

    public StaticThing getRightBackground() {
        return rightBackground;
    }
    public Hero getHero(){ return  hero;}

    public AnimationTimer getTimer() {
        return timer;
    }

    public AnimationTimer getTimerDefilement() {
        return timerDefilement;
    }

    public Camera getPointDeVue() {
        return pointDeVue;
    }

    public void update(long time) {
        double posx;
        double posy;
        posx = hero.getX();
        posy = hero.getY();

        pointDeVue.update(time, posx, posy);
        hero.updatePosition(time, pointDeVue.getX(), pointDeVue.getY());
        bad.updatePositionSimple(time, pointDeVue.getX(), pointDeVue.getY());
        leftBackground.getStaticView().setViewport(new Rectangle2D(pointDeVue.getX()%800,pointDeVue.getY()%400,800-pointDeVue.getX()%800,400-(pointDeVue.getY())));
        rightBackground.getStaticView().setViewport(new Rectangle2D(0,pointDeVue.getY()%400,(pointDeVue.getX()%800),400-(pointDeVue.getY())));
        rightBackground.getStaticView().setX(800-pointDeVue.getX()%800);
        leftBackground.getStaticView().setX( 0);

        if (bad.getX()+300< hero.getX()){
            bad .setX(hero.getX()+700);
        }
        if(bad.getHitBox().intersects(hero.getHitBox())) {
            if (hero.isinvincible(time)) {
                System.out.println("Aie!");
                hero.setInvincibility(time + (long) 25_000_00000.0);
                if (hero.getLife() > 1) {
                    hero.setLife(hero.getLife() - 1);
                    hero.getAffichageLife().setViewport(new Rectangle2D(0, (5 - hero.getLife()) * 46, 259, 45));
                } else {
                    System.out.println("Perdu!");
                    root.getChildren().add(info);
                    setGameEnd(1);
                    timer.stop();
                    timerDefilement.stop();
                }
            }
        }
    }
}
