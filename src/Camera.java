import java.util.ArrayList;

public class Camera {
    private double x;
    private double y;
    private double[] posArray = {0 ,100 ,0 ,0 ,0,0};
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    Camera(double x ,double y){
        this.x = x;
        this.y = y;

    }

    public void reset(){
        x = 0;
        y = 100;

        posArray[0] = 10;
        posArray[1] = 100;
        posArray[2] = 0;
        posArray[3] = 0;
        posArray[4] = 0;
        posArray[5] = 0;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double[] getPosArray() {
        return posArray;
    }

    @Override
    public String toString(){
        return (x + "," + y);
    }

    public void update(long time, double herox, double heroy) {
        double intervalTime = 0.001;
        double camx = posArray[0];
        double camy = posArray[1];
        double camvx = posArray[2];
        double camvy = posArray[3];
        double camax = posArray[4];
        double camay = posArray[5];
        x +=10;

        double f = 600;
        double m = 1;
        double k = (f * f) / 4;

        camax = k/m * (herox - posArray[0]-50)- f/m * posArray[2] ;
        camvx = posArray[4] * intervalTime + posArray[2];
        camx = posArray[2]*intervalTime + posArray[0];

        camay = (k)/m * (heroy - posArray[1]-150)- f/m * posArray[3] ;
        camvy = posArray[5] * intervalTime + posArray[3];
        camy = posArray[3]*intervalTime + posArray[1];

        posArray[0] = camx;
        posArray[1] = camy;
        posArray[2] = camvx;
        posArray[3] = camvy;
        posArray[4] = camax;
        posArray[5] = camay;

        x = posArray[0];
        y = posArray[1];

    }
}
