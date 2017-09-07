package lab03.kmitl.simpleapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import lab03.kmitl.simpleapp.model.Dot;
import lab03.kmitl.simpleapp.model.Dots;

public class DotView extends View {

    public interface OnDotViewPressListener {
        void onPress(DotView dotView, int x, int y);
    }

    private Paint paint;
    private Dots dots;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dots != null) {
            for (Dot dot: dots.getAll()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(),
                        dot.getCenterY(),
                        dot.getRadius(), paint);
            }
        }
    }

    private OnDotViewPressListener listener;
    public void setListener(OnDotViewPressListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                listener.onPress(this, (int)event.getX(), (int)event.getY());
        }
        return false;
    }

    public void setDots(Dots dots) {
        this.dots = dots;
    }
}

