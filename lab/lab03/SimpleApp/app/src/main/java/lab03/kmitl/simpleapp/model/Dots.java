package lab03.kmitl.simpleapp.model;

import java.util.ArrayList;
import java.util.List;

public class Dots {

    private List<Dot> dots = new ArrayList<>();

    public void addDot(Dot dot) {
        dots.add(dot);
        this.dotsChangedListener.onDotsChanged(this);
    }

    public List<Dot> getAll() {
        return this.dots;
    }

    public interface DotsChangedListener {
        void onDotsChanged(Dots dots);
    }

    private DotsChangedListener dotsChangedListener;

    public void setDotsChangedListener(
            DotsChangedListener dotsChangedListener) {
        this.dotsChangedListener = dotsChangedListener;
    }
}
