package helpers;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Created by root on 12/9/14.
 */
public class CalculatorHelper {


    public static void numClick(View view, int num, Context context, EditText calcDisplay, ArrayList<String> equ, MotionEvent event, TransitionDrawable transition)
    {

        if(event.getAction()== MotionEvent.ACTION_DOWN)
        {
            transition = (TransitionDrawable) view.getBackground();
            transition.startTransition(80);


            if(num!=0)
            {
                if (Double.parseDouble(calcDisplay.getText().toString()) == 0.0 && !calcDisplay.getText().toString().contains("."))
                {
                    calcDisplay.setText("");
                }


                calcDisplay.setText(calcDisplay.getText().toString() + Integer.toString(num));
                equ.add(Integer.toString(num));
                calcDisplay.setSelection(calcDisplay.length()-1);

            }

            else if(num==0)
            {
                if (Double.parseDouble(calcDisplay.getText().toString()) == 0.0 && !calcDisplay.getText().toString().contains("."))
                {
                    calcDisplay.setText(calcDisplay.getText().toString());
                }
                else
                {
                    calcDisplay.setText(calcDisplay.getText().toString() + Integer.toString(num));
                    calcDisplay.setSelection(calcDisplay.length()-1);
                    equ.add(Integer.toString(num));

                }
            }
        }

        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            transition.reverseTransition(80);
            Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(20);
        }


    }



    public static void clearAll(View view,Vibrator vibrator, ArrayList<String> list, EditText editText, EditText oldEditText, MotionEvent event, TransitionDrawable transition)
    {

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);

            transition = (TransitionDrawable) view.getBackground();
            transition.startTransition(80);

            editText.startAnimation(fadeOut);
            oldEditText.startAnimation(fadeOut);
            fadeOut.setDuration(500);
            fadeOut.setFillAfter(true);
            list.clear();
            editText.setText("0");
            oldEditText.setText("");

            editText.startAnimation(fadeIn);
            oldEditText.startAnimation(fadeIn);
            fadeIn.setDuration(500);
            fadeIn.setFillAfter(true);
        }

        else if(event.getAction() == MotionEvent.ACTION_UP)
        {

            vibrator.vibrate(20);
            transition.reverseTransition(80);
        }
    }




}
