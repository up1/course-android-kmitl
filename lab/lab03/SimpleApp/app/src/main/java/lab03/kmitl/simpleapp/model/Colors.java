package lab03.kmitl.simpleapp.model;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Colors {

    private List<Integer> colorList = new ArrayList<>();

    public Colors() {
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GRAY);
    }

    public int getColor(int index) {
        return colorList.get(index);
    }

    public int getSize() {
        return colorList.size();
    }

}
