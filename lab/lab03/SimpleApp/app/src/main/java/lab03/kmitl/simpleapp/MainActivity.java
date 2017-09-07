package lab03.kmitl.simpleapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import lab03.kmitl.simpleapp.model.Colors;
import lab03.kmitl.simpleapp.model.Dot;
import lab03.kmitl.simpleapp.model.Dots;
import lab03.kmitl.simpleapp.view.DotView;

public class MainActivity extends AppCompatActivity
implements Dots.DotsChangedListener{

    private Dots dots;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set default value
        dots = new Dots();
        dots.setDotsChangedListener(this);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setListener(new DotView.OnDotViewPressListener() {
            @Override
            public void onPress(DotView dotView, int x, int y) {
                dots.addDot(new Dot(x, y, 50, Color.BLUE));
            }
        });
    }

    public void randomDot(View view) {
        Random random = new Random();
        int x = random.nextInt(this.dotView.getWidth());
        int y = random.nextInt(this.dotView.getHeight());

        Colors colors = new Colors();
        int color = colors.getColor(random.nextInt(colors.getSize()));

        dots.addDot(new Dot(x, y, 20, color));
    }

    @Override
    public void onDotsChanged(Dots dots) {
        //Draw dot model to view
        this.dotView.setDots(dots);
        this.dotView.invalidate();
    }
}
