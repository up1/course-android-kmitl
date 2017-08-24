package lab03.kmitl.simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import lab03.kmitl.simpleapp.model.Dot;
import lab03.kmitl.simpleapp.view.DotView;

public class MainActivity extends AppCompatActivity
implements Dot.DotChangedListener{

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set default value
        dot = new Dot(0, 0, 20);
        dot.setDotChangedListener(this);

        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void randomDot(View view) {
        //Random a Dot
        Random random = new Random();
        dot.setCenterX(random.nextInt(200));
        dot.setCenterY(random.nextInt(200));
    }

    @Override
    public void onDotChanged(Dot dot) {
        //Draw dot model to view
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
