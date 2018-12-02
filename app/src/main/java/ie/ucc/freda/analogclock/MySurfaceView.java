package ie.ucc.freda.analogclock;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ie.ucc.freda.analogclock.RegPoly;

public class MySurfaceView extends SurfaceView implements Runnable {
    private Thread thread=null;
    private SurfaceHolder surfaceHolder=null ;
    private boolean running=false;
    private float length;
    private Canvas canvas=null;
    private String theme;

    public MySurfaceView(Context context, float i, String theme) {
        super(context);
        length=i;
        this.theme = theme;
        surfaceHolder=this.getHolder();
        // TODO Auto-generated constructor stub
    }

    public void onResumeMySurfaceView(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPauseMySurfaceView(){
        boolean retry= true;
        running=false;
        while(retry)
        {
            try {
                thread.join();
                retry= false;
            }
            catch(InterruptedException e)   {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        Paint paint1;
        Paint paint2;
        Paint paint3;
        Paint paint4;
        Paint paint5;
        int hour=0,min=0,sec=0;
        while(running)
        {
            if(surfaceHolder.getSurface().isValid());
            {
                try
                {
                    Thread.sleep(1000);
                    canvas = surfaceHolder.lockCanvas();

                    if(theme != null && theme.equalsIgnoreCase("theme1")) {
                        paint1 = new Paint();
                        paint1.setColor(Color.GRAY);
                        paint1.setTextSize(25f);

                        paint2 = new Paint();
                        paint2.setColor(Color.BLUE);

                        paint3 = new Paint();
                        paint3.setColor(Color.GRAY);

                        paint4 = new Paint();
                        paint4.setColor(Color.BLUE);
                        paint4.setTextSize(80f);

                        paint5 = new Paint();
                        paint5.setColor(Color.GRAY);
                        paint5.setTextSize(35f);
                    }
                    else    {
                        paint1 = new Paint();
                        paint1.setColor(Color.GRAY);
                        paint1.setTextSize(25f);

                        paint2 = new Paint();
                        paint2.setColor(Color.YELLOW);

                        paint3 = new Paint();
                        paint3.setColor(Color.GRAY);

                        paint4 = new Paint();
                        paint4.setColor(Color.YELLOW);
                        paint4.setTextSize(80f);

                        paint5 = new Paint();
                        paint5.setColor(Color.GRAY);
                        paint5.setTextSize(35f);
                    }

                    RegPoly secMarks = new RegPoly(3600,length,getWidth()/2,getHeight()/2,canvas,paint1);
                    RegPoly hourMarks = new RegPoly(100,length-20,getWidth()/2,getHeight()/2,canvas,paint2);
                    RegPoly hourHand = new RegPoly(60,length-210,getWidth()/2,getHeight()/2,canvas,paint4);
                    RegPoly minHand = new RegPoly(60,length-140,getWidth()/2,getHeight()/2,canvas,paint4);
                    RegPoly secHand = new RegPoly(60,length-140,getWidth()/2,getHeight()/2,canvas,paint3);

                    RegPoly numbers = new RegPoly(12,length-50,getWidth()/2,getHeight()/2,canvas,paint5);
                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    secMarks.drawPoints();
                    hourMarks.drawPoints();

                    numbers.drawnum(paint5);

                    Calendar calendar = Calendar.getInstance();
                    hour=calendar.get(Calendar.HOUR);
                    min=calendar.get(Calendar.MINUTE);
                    sec=calendar.get(Calendar.SECOND);

                    secHand.drawRadius(sec+45);
                    minHand.drawRadius(min+45);
                    hourHand.drawRadius(hour*5+min /12+45);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}