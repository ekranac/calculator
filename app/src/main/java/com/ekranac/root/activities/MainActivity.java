package com.ekranac.root.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.transition.Transition;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.ekranac.root.calculator.R;

import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;
import helpers.CalculatorHelper;


public class MainActivity extends Activity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        EditText calcDisplay = (EditText) findViewById(R.id.display_content);
        calcDisplay.setText("0");

        EditText oldCalcDisplay = (EditText) findViewById(R.id.display_old_content);
        oldCalcDisplay.setText("");


        calcDisplay.setFocusable(false);
        calcDisplay.setClickable(false);
        calcDisplay.setFocusableInTouchMode(false);
        calcDisplay.setEnabled(true);
        calcDisplay.setKeyListener(null);
        calcDisplay.setHorizontallyScrolling(true);


        oldCalcDisplay.setFocusable(false);
        oldCalcDisplay.setClickable(false);
        oldCalcDisplay.setFocusableInTouchMode(false);
        oldCalcDisplay.setEnabled(true);
        oldCalcDisplay.setKeyListener(null);
        oldCalcDisplay.setHorizontallyScrolling(true);


        TextView calcZero = (TextView) findViewById(R.id.calculator_zero);
        calcZero.setOnTouchListener(this);

        TextView calcOne = (TextView) findViewById(R.id.calculator_one);
        calcOne.setOnTouchListener(this);

        TextView calcTwo = (TextView) findViewById(R.id.calculator_two);
        calcTwo.setOnTouchListener(this);

        TextView calcThree = (TextView) findViewById(R.id.calculator_three);
        calcThree.setOnTouchListener(this);

        TextView calcFour = (TextView) findViewById(R.id.calculator_four);
        calcFour.setOnTouchListener(this);

        TextView calcFive = (TextView) findViewById(R.id.calculator_five);
        calcFive.setOnTouchListener(this);

        TextView calcSix = (TextView) findViewById(R.id.calculator_six);
        calcSix.setOnTouchListener(this);

        TextView calcSeven = (TextView) findViewById(R.id.calculator_seven);
        calcSeven.setOnTouchListener(this);

        TextView calcEight = (TextView) findViewById(R.id.calculator_eight);
        calcEight.setOnTouchListener(this);

        TextView calcNine = (TextView) findViewById(R.id.calculator_nine);
        calcNine.setOnTouchListener(this);

        TextView calcClear = (TextView) findViewById(R.id.calculator_clear);
        calcClear.setOnTouchListener(this);

        TextView calcPoint = (TextView) findViewById(R.id.calculator_point);
        calcPoint.setOnTouchListener(this);

        TextView calcMinus = (TextView) findViewById(R.id.calculator_subtract);
        calcMinus.setOnTouchListener(this);

        TextView calcTimes = (TextView) findViewById(R.id.calculator_multiply);
        calcTimes.setOnTouchListener(this);

        TextView calcDivide = (TextView) findViewById(R.id.calculator_divide);
        calcDivide.setOnTouchListener(this);

        TextView calcPlus = (TextView) findViewById(R.id.calculator_add);
        calcPlus.setOnTouchListener(this);

        TextView calcEquals = (TextView) findViewById(R.id.calculator_equals);
        calcEquals.setOnTouchListener(this);

    }

    public void addOperator(String op, MotionEvent event, View view, TransitionDrawable transition)
    {
        EditText calcDisplay = (EditText) findViewById(R.id.display_content);
        EditText oldCalcDisplay = (EditText) findViewById(R.id.display_old_content);

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            transition = (TransitionDrawable) view.getBackground();
            transition.startTransition(80);

            if(equ.size()==0)
            {
                calcDisplay.setText("0");
            }
            else if(equ.get(equ.size()-1)=="÷" || equ.get(equ.size()-1)=="-" || equ.get(equ.size()-1)=="+" || equ.get(equ.size()-1)=="×")
            {
                equ.remove(equ.size() -1);
                oldCalcDisplay.setText(oldCalcDisplay.getText().toString().substring(0, oldCalcDisplay.getText().toString().length() - 1));

                equ.add(op);
                oldCalcDisplay.setText(oldCalcDisplay.getText().toString() + op);
                oldCalcDisplay.setSelection(oldCalcDisplay.length()-1);
            }
            else if(oldCalcDisplay.getText().toString()=="")
            {
                Log.i("Prazen","TRUE");
            }
            else
            {
                equ.add(op);

                oldCalcDisplay.setText(oldCalcDisplay.getText().toString() + calcDisplay.getText().toString() + op);
                oldCalcDisplay.setSelection(oldCalcDisplay.length()-1);
                calcDisplay.setText("0");
            }
            hasPoint=false;
        }

        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            transition.reverseTransition(80);

            Vibrator vibe = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(20);
        }

    }


    Boolean hasPoint=false;
    final ArrayList<String> equ = new ArrayList<String>();


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        EditText calcDisplay = (EditText) findViewById(R.id.display_content);
        EditText oldCalcDisplay = (EditText) findViewById(R.id.display_old_content);


        Vibrator vibe = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        TransitionDrawable transition = (TransitionDrawable) v.getBackground();

        switch(v.getId())
        {
            case R.id.calculator_zero:
                CalculatorHelper.numClick(findViewById(v.getId()), 0, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_one:
                CalculatorHelper.numClick(findViewById(v.getId()), 1, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_two:
                CalculatorHelper.numClick(findViewById(v.getId()), 2, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_three:
                CalculatorHelper.numClick(findViewById(v.getId()), 3, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_four:
                CalculatorHelper.numClick(findViewById(v.getId()), 4, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_five:
                CalculatorHelper.numClick(findViewById(v.getId()), 5, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_six:
                CalculatorHelper.numClick(findViewById(v.getId()), 6, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_seven:
                CalculatorHelper.numClick(findViewById(v.getId()), 7, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_eight:
                CalculatorHelper.numClick(findViewById(v.getId()), 8, getApplicationContext(), calcDisplay, equ, event, transition);
                break;
            case R.id.calculator_nine:
                CalculatorHelper.numClick(findViewById(v.getId()), 9, getApplicationContext(), calcDisplay, equ, event, transition);
                break;

            case R.id.calculator_clear:
                CalculatorHelper.clearAll(findViewById(v.getId()), vibe, equ, calcDisplay, oldCalcDisplay, event, transition);
                hasPoint=false;
                equ.clear();
                break;

            case R.id.calculator_point:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    transition = (TransitionDrawable) v.getBackground();
                    transition.startTransition(80);

                    if(!hasPoint)
                    {
                        equ.add(".");
                        calcDisplay.setText(calcDisplay.getText().toString() + ".");

                        hasPoint=true;
                    }

                    oldCalcDisplay.setSelection(oldCalcDisplay.length()-1);
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    vibe.vibrate(20);
                    transition.reverseTransition(80);
                }

                break;

            case R.id.calculator_add:
                addOperator("+", event, findViewById(v.getId()), transition);
                hasPoint=false;
                break;

            case R.id.calculator_subtract:
                addOperator("-", event, findViewById(v.getId()), transition);
                hasPoint=false;
                break;

            case R.id.calculator_divide:
                addOperator("÷", event, findViewById(v.getId()), transition);
                hasPoint=false;
                break;

            case R.id.calculator_multiply:
                addOperator("×", event, findViewById(v.getId()), transition);
                hasPoint=false;
                break;

            case R.id.calculator_equals:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    transition = (TransitionDrawable) v.getBackground();
                    transition.startTransition(80);

                    String equation = oldCalcDisplay.getText().toString() + calcDisplay.getText().toString();
                    equation = equation.replace("×","*");
                    equation = equation.replace("÷","/");
                    if(!equation.contains("."))
                    {
                        equation = equation + ".";
                    }

                    try {
                        String aResult = "";
                        String finalResult="";
                        Interpreter interpreter = new Interpreter();
                        interpreter.eval("result = " + equation);

                        double dFinalResult = Double.valueOf(interpreter.get("result").toString());

                        if(dFinalResult % 1==0)
                        {
                            finalResult= Double.toString(dFinalResult).replace(".0","");
                            hasPoint=false;
                        }
                        else
                        {
                            aResult = Double.toString(dFinalResult);
                            double p = Math.pow(10d, 3); // 3 decimal places if number isn't even

                            double result = Math.round(Double.parseDouble(aResult) * p)/p;

                            finalResult = Double.toString(result);
                            hasPoint=true;
                        }

                        calcDisplay.setText(finalResult);
                        oldCalcDisplay.setText("");

                    }

                    catch (EvalError error)
                    {
                        Log.e("EvalError", error.toString());
                        Toast.makeText(getApplicationContext(), "Number too big, sorry :)", Toast.LENGTH_SHORT).show();
                    }
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    transition.reverseTransition(80);
                    vibe.vibrate(20);
                }



                // Need to find a way to convert string to a math expression !
                // Found the way, motherfucker #prodigy
                break;


        }

        return true;
    }




}
