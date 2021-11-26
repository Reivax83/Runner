import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.geom.Rectangle2D;

public class Hero extends AnimatedThing{
    private long invincibility;
    private int life = 5;
    private ImageView affichageLife;
    public Hero(String fileName, int minx, int miny, int width, int heigth, int maxIndex, int index, double x, double y) {
        super(fileName, minx, miny, width, heigth, maxIndex, index, x, y);
        Image spriteSheet = new Image( "lifebar.png");
        affichageLife = new ImageView(spriteSheet);
    }

    public boolean isinvincible(long time){
        if(time < invincibility){
            return(false);
        }
        else {
            return (true);
        }
    }

    public void setInvincibility(long invincibility) {
        this.invincibility = invincibility;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public ImageView getAffichageLife() {
        return affichageLife;
    }

}
