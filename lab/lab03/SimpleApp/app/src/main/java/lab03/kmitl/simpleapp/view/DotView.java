package lab03.kmitl.simpleapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import lab03.kmitl.simpleapp.model.Dot;

public class DotView extends View {

    private Paint paint;
    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    private Dot dot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dot != null) {
            paint.setColor(Color.RED);
            canvas.drawCircle(dot.getCenterX(),
                    dot.getCenterY(),
                    dot.getRadius(), paint);
        }
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }
}

