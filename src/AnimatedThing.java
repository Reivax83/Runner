import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.css.Rect;


public class AnimatedThing {
    private double x;
    private double y;
    private ImageView sprite;
    private int status;
    private int index;
    private int duration;
    private int maxIndex;
    private int sizeOfWindow;
    private int offsetOfFrame;
    private double[] posArrayHero = { 0, 250, 20, 0, 5, 8};
    private double heigth;
    private double width;

    public AnimatedThing(String fileName, int minx, int miny, int width, int heigth, int maxIndex, int index,double x, double y) {

        Image spriteSheet = new Image(fileName);
        sprite = new ImageView(spriteSheet);
        int startx = minx + index * 75;
        sprite.setViewport(new Rectangle2D(startx,miny,width,heigth));
        sprite.setX(x);
        sprite.setY(y);
        this.maxIndex=maxIndex;
        this.index=index;
        this.x=x;
        this.y=y;
        this.heigth = heigth;
        this.width = width;
    }

    public ImageView getImage() {
        return sprite;
    }

    public void update(long time, double posy, double vy){
        if(vy == 0) {
            index = (index + 1) % maxIndex;
            int startx = 0;
            if (startx < 471) {
                startx = startx + index * 84;
            } else {
                startx = 0;
            }
            sprite.setViewport(new Rectangle2D(startx, 0, 85, 100));
        }
        else{
            if(vy <0){
                sprite.setViewport((new Rectangle2D(20,160,60,105)));
            }
            else {
                sprite.setViewport((new Rectangle2D(95,160,70,105)));
            }
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void updatePosition(long time, double camx, double camy) {
        this.x += posArrayHero[2]+posArrayHero[4];
        if(y<=250){
            if( posArrayHero[3] !=0){
                this.y += (posArrayHero[3]+ posArrayHero[5]);
                posArrayHero[3] += posArrayHero[5];
            }
            else{
                this.y =250;
                posArrayHero[1] = y;
                posArrayHero[3] = 0;
            }
        }
        else{
            this.y =250;
            posArrayHero[1] = y;
            posArrayHero[3] = 0;
        }

        sprite.setX(x- camx);
        sprite.setY(y - camy);
    }

    public  void jump(){
        if(y ==250){
            posArrayHero[3] = -50;
        }
    }

    public void updatePositionSimple(long time, double camx, double camy){
        sprite.setX(x- camx);
        sprite.setY(y - camy);
    }

    public void resetHero(){
        x = 30;
        y = 250;
    }

    public void resetFoe(){
        x = 700;
        y = 275;
    }

    public double[] getPosArrayHero() {
        return posArrayHero;
    }

    public  Rectangle2D getHitBox(){
        return(new Rectangle2D(x,y,width,heigth));
    }
}
