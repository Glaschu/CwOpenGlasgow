package com.Jamesglasgow.Cw.OpenGlasgow;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jamesglasgow on 05/12/2016.
 */

public class CanvusView  extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder SunRiseSurface;

    DrawThread drawingThread = null;


    public CanvusView(Context context,int Place,String rise,String set)
    {
        super(context);
        SunRiseSurface = getHolder();
        SunRiseSurface.addCallback(this);
        drawingThread = new DrawThread(getHolder(), this);
        drawingThread.setInfo(Place,rise,set);

        setFocusable(true);

    }

    public DrawThread getThread()
    {
        return drawingThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

        drawingThread.setRunning(true);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        drawingThread.setSurfaceSize(width,height);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        drawingThread.setRunning(false);
        while(retry)
        {
            try {
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}