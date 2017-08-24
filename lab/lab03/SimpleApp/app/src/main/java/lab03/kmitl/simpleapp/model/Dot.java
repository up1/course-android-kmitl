package lab03.kmitl.simpleapp.model;

public class Dot {

    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }

    private DotChangedListener dotChangedListener;

    public void setDotChangedListener(
            DotChangedListener dotChangedListener) {
        this.dotChangedListener = dotChangedListener;
    }

    private int centerX;
    private int centerY;
    private int radius;

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.dotChangedListener.onDotChanged(this);
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.dotChangedListener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}


