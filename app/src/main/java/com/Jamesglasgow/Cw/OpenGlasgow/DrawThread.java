package com.Jamesglasgow.Cw.OpenGlasgow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jamesglasgow on 05/12/2016.
 */

public class DrawThread extends Thread
{
    private int canvasWidth;
    private int canvasHeight;
    private float xPos = 0.0f;
    private float yPos = 0.0f;
    private int i;
private int place;
    private String rise,set;


    private float HalfAppletHeight;
    private float HalfAppletWidth;



    private boolean first = true;
    private boolean run = false;

    private SurfaceHolder CanvusSurface;
    private Paint paintBio;
    private CanvusView bioSF;

    public DrawThread(SurfaceHolder surfaceHolder, CanvusView bioSurfV) {
        this.CanvusSurface = surfaceHolder;
        this.bioSF = bioSurfV;
        paintBio = new Paint();

    }

    public void doStart() {
        synchronized (CanvusSurface) {

            first = false;

        }
    }

    public void run() {
        while (run) {
            Canvas c = null;
            try {
                c = CanvusSurface.lockCanvas(null);
                synchronized (CanvusSurface) {
                    svDraw(c);
                }
            } finally {
                if (c != null) {
                    CanvusSurface.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public void setRunning(boolean b) {
        run = b;
    }
    public  void setInfo(int Place,String Rise,String Set){
        place=Place+5;
        rise=Rise;
        set=Set;
    }
    public void setSurfaceSize(int width, int height) {
        synchronized (CanvusSurface) {
            canvasWidth = width;
            canvasHeight = height;
            HalfAppletHeight = canvasHeight / 2;
            HalfAppletWidth  = canvasWidth / 32;
            doStart();
        }
    }


    private void svDraw(Canvas canvas) {
        if(run) {
            canvas.save();
            canvas.restore();
            canvas.drawColor(Color.WHITE);
            paintBio.setStyle(Paint.Style.FILL);
            drawAxes(canvas);

            paintBio.setColor(Color.GREEN);
            paintBio.setStrokeWidth(20);
            drawWave(canvas, 28);

            drawLabels(canvas);

        }
    }



    public void drawWave(Canvas theCanvas, int period)
    {
        float xPosOld = 0.0f;
        float yPosOld = 0.0f;
        int sDate = 0;
        int tDate = 0;

        sDate = 6;

        for (i=0;i<=30;i++)
        {
            xPos = i * HalfAppletWidth;

            tDate = sDate + i;
            yPos = (float)(500.0f * (Math.sin((tDate%period)*2*Math.PI/period)));

            if ( i == 0)
                paintBio.setStyle(Paint.Style.FILL);
            else
                theCanvas.drawLine(xPosOld, (yPosOld + HalfAppletHeight), xPos, (yPos + HalfAppletHeight), paintBio);
            xPosOld = xPos;
            yPosOld = yPos;
            if(i==place)
            {
                theCanvas.drawCircle(xPos,(yPos + HalfAppletHeight),75, paintBio);
            }
        }
    }

    public void drawAxes(Canvas theCanvas)
    {
        String sunRise="SunRise";
        Paint paint = new Paint();
        //theCanvas.drawPaint(paint);
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);

        paintBio.setColor(Color.BLACK);
        theCanvas.drawLine(0,1000,30*HalfAppletWidth,1000, paintBio); // Horizontal X Axes
        theCanvas.drawLine(15* HalfAppletWidth,0,15* HalfAppletWidth,canvasHeight, paintBio);// Vertical Y Axes

    }
    public void drawLabels(Canvas theCanvas){

        Paint paint = new Paint();

        paint.setColor(Color.BLUE);
        paint.setTextSize(40);
        theCanvas.drawText("SunRise :"+rise, (HalfAppletWidth+150),  (HalfAppletHeight+500), paint);

        theCanvas.drawText("SunSet :"+set,(HalfAppletWidth+1600), (HalfAppletHeight+500), paint);
    }
}
