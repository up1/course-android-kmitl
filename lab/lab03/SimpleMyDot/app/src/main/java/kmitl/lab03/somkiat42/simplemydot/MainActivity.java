package kmitl.lab03.somkiat42.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.somkiat42.simplemydot.model.Colors;
import kmitl.lab03.somkiat42.simplemydot.model.Dot;
import kmitl.lab03.somkiat42.simplemydot.model.Dots;
import kmitl.lab03.somkiat42.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
implements Dots.OnDotsChangeListener{

    private DotView dotView;
    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dots = new Dots();
        dots.setListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }
}
