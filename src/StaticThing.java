import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    private double x;
    private double y;
    private ImageView staticView;

    StaticThing(String fileName, double x, double y){
        this.x = x;
        this.y = y;

        Image spriteSheet = new Image( fileName);
        staticView = new ImageView(spriteSheet);
    }

    public void reset(double x, double minX, double minY, double width, double heigth){
        getStaticView().setX(800-x%800);
        staticView.setViewport(new Rectangle2D(minX,minY,width,heigth));
    }

    public ImageView getStaticView() {
        return staticView;
    }

}
