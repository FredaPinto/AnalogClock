package ie.ucc.freda.analogclock;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RegPoly {
    private float x0,y0,r;
    private int n;
    private float x[],y[];
    private Canvas c =null;
    private Paint p = null;


    public RegPoly(int n ,float r, float x0,float y0,Canvas canvas,Paint paint)
    {
        this.n=n;
        this.r=r;
        this.x0=x0;this.y0=y0;
        this.c=canvas;
        this.p=paint;
        x=new float[n];y=new float[n];
        for(int i=0;i<n;i++)
        {
            x[i]=(float)(x0+r*Math.cos(2*Math.PI*i/n));
            y[i]=(float)(y0+r*Math.sin(2*Math.PI*i/n));
        }

    }





    public float getx(int i) {
        // TODO Auto-generated method stub
        return x[i%n];
    }

    public void drawnum(Paint p){
        for(int i = 0;i<12;i++)
        {
            if(i==9){
                c.drawText(12+"",x[9],y[9],p);
            }else{
                c.drawText((i+3)%12+"", x[i],y[i],p);
            }
        }
    }





    public float gety(int i) {
        // TODO Auto-generated method stub
        return x[i%n];
    }

    public void drawRegPoly(){
        for (int i =0;i<n;i++)
        {
            c.drawLine(x[i],y[i] , x[(i+1)%n], y[(i+1)%n],p);

        }
    }
    public void drawPoints(){
        for(int i=0;i<n;i++)
        {
            c.drawCircle(x[i], y[i],4, p);
        }
    }
    public void drawRadius(int i){

        c.drawLine(x0, y0,x[i%n],y[i%n], p);


    }

}
