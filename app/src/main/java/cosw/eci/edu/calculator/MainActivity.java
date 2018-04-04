package cosw.eci.edu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

import cosw.eci.edu.calculator.model.Calculator;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private TextView stackTv;
    private TextView logText;
    private Stack<String> stack;
    private Calculator cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stack=new Stack<>();
        setContentView(R.layout.activity_main);
        cl=new Calculator();
        init();
    }

    protected void init(){

        tv = (TextView) findViewById(R.id.textView);
        stackTv = (TextView) findViewById(R.id.stack);
        logText = (TextView) findViewById(R.id.logText);
        //second requeriment, the other listeners are on the xml view
        Button addToStackButton = (Button) findViewById(R.id.addToStack);
        addToStackButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                onClickAddToStack(v);
            }
        });
    }


    protected void setTextView(String text){

        tv.setText(text);

    }

    protected void onClickNumber(View v){
        Button pressed = (Button) v;

        setTextView(tv.getText() + pressed.getText().toString());

    }

    protected void onClickOperator(View v){
        Button pressed = (Button) v;

        setTextView(tv.getText() + pressed.getText().toString());
    }

    protected void onClickCleanTextView(View v){
        stack = new Stack<>();
        setStackView();
        setTextView("");
        logText.setText("");
    }

    protected void onClickEquals(View v){
            try{
                Double res= cl.calculate(stack);
                stack=new Stack<>();
                stack.push(res.toString());
                setStackView();
            }catch(Exception e){

            }

    }
    protected void onClickAddToStack(View v){
        Button pressed = (Button) v;
        System.out.println("En listener "+tv.getText().toString());
        System.out.println(cl.isNumberOrOperator(tv.getText().toString()));
        if(cl.isNumberOrOperator(tv.getText().toString())){
            stack.push(tv.getText().toString());
            setStackView();
            setTextView("");
        }else{
            logText.setText("Cant add to the stack");
        }
    }

    protected void setStackView(){
        String res = "";
        for (Object elem:stack.toArray()){
            res=elem.toString()+"\n"+res;
        }
        stackTv.setText(res);
    }

    protected void onClickPoint(View v){
        onClickNumber(v);
    }

    protected void onClickChangeNumber(View v){
        try {
            tv.setText(cl.tryChangeNumber(tv.getText().toString()));
        } catch (Exception e) {
            logText.setText("Cant change then number");
        }
    }
}
