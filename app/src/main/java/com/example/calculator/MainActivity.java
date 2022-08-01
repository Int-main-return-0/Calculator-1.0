package com.example.calculator;

import static com.example.calculator.R.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView edit_screen;
    private TextView result_screen;
    boolean sub_add = false;//负号添加
    boolean brackets_add = false;//括号添加
    int left_brackets_flag = 0;//左括号数量，与有括号匹配使用
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

//        隐藏系统自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

//        按键
        Button button0 = (Button) findViewById(id.btn0);
        Button button1 = (Button) findViewById(id.btn1);
        Button button2 = (Button) findViewById(id.btn2);
        Button button3 = (Button) findViewById(id.btn3);
        Button button4 = (Button) findViewById(id.btn4);
        Button button5 = (Button) findViewById(id.btn5);
        Button button6 = (Button) findViewById(id.btn6);
        Button button7 = (Button) findViewById(id.btn7);
        Button button8 = (Button) findViewById(id.btn8);
        Button button9 = (Button) findViewById(id.btn9);
        Button bracket_left = (Button) findViewById(id.bracket_left);
        Button bracket_right = (Button) findViewById(id.bracket_right);
        Button point = (Button) findViewById(id.point);
        Button add = (Button) findViewById(id.add);
        Button sub = (Button) findViewById(id.subtraction);
        Button mul = (Button) findViewById(id.multiplication);
        Button divide = (Button) findViewById(id.divide);
        Button DEL = (Button) findViewById(id.DEL);
        Button AC = (Button) findViewById(id.AC);
        Button result = (Button) findViewById(id.result);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        bracket_left.setOnClickListener(this);
        bracket_right.setOnClickListener(this);
        point.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        divide.setOnClickListener(this);
        DEL.setOnClickListener(this);
        AC.setOnClickListener(this);
        result.setOnClickListener(this);

//        文本框
        edit_screen = findViewById(id.text_screen);
        result_screen = findViewById(id.result_screen);
    }

    public String figure(int number, String str, boolean zero){
        if (zero)
            str = str.substring(0,str.length()-1);
       else if( str.length() >0 && str.charAt(str.length() - 1) == ')'){
           str+="*";
       }
        str += number;
        return str;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

//        获取编辑框的字符串
        String text = edit_screen.getText().toString();

//        判断字符串最后一位是否有零
        if (text.length() == 0 || text.charAt(text.length() - 1) != '0') {
            flag = false;
        }
        switch (v.getId()){
            case id.btn0:
                    if (text.length() == 0 || text.charAt(text.length() - 1) != '/') {
                        if (text.length() == 0 || (!Character.isDigit(text.charAt(text.length() - 1)) && text.charAt(text.length() - 1) != '.')) {
                            text += "0";
                            flag = true;
                        }

                        //清除多余的0
                        if (!flag) {
                            if (text.length() == 1 && text.charAt(0) == '0') {
                                flag = true;
                                break;
                            }
                            text += "0";
                        }
                        edit_screen.setText(text);
                    }
                 if((text.charAt(text.length() - 1)) == '/')
                {
                    text += "0";
                    flag = true;
                    edit_screen.setText(text);
                }
                    break;
            case id.btn1:
                text =figure(1, text,flag);edit_screen.setText(text);break;
            case id.btn2:
                text =figure(2, text,flag);edit_screen.setText(text);break;
            case id.btn3:
                text =figure(3, text,flag);edit_screen.setText(text);break;
            case id.btn4:
                text =figure(4, text,flag);edit_screen.setText(text);break;
            case id.btn5:
                text =figure(5, text,flag);edit_screen.setText(text);break;
            case id.btn6:
                text =figure(6, text,flag);edit_screen.setText(text);break;
            case id.btn7:
                text =figure(7, text,flag);edit_screen.setText(text);break;
            case id.btn8:
                text =figure(8, text,flag);edit_screen.setText(text);break;
            case id.btn9:
                text =figure(9, text,flag);edit_screen.setText(text);break;

            case id.point:
                if(text.length() > 0 && !Character.isDigit(text.charAt(text.length() - 1)))
                    break;
                if(text.length() == 0){
                    text += "0.";
                }else{
    //                防止添加多个小数点
                    int len = text.length() - 1;
    //                len>=0 防止越界
                    while( len >= 0 && Character.isDigit(text.charAt(len))){
                        len--;
                    }
                    if( len >= 0 && text.charAt(len) == '.'){
                        break;
                    }
                    text +='.';
                }
                edit_screen.setText(text);
                break;
            case id.add:

                if (text.length() == 0){
                    text = "";
                    break;
                }
//                if(text.charAt(text.length() - 1) == '0' && text.charAt(text.length() -2) == '/'){
//                    text = text.substring(0,text.length() - 2);
//                    Toast.makeText(this, "除数不能为0，请重新输入", Toast.LENGTH_SHORT).show();
//                }

                if(text.length() != 0 && text.charAt(text.length() - 1) == '.' )
                        text += "0+";

                if (Character.isDigit(text.charAt(text.length() - 1)) ||  text.charAt(text.length() -1) == ')' ){
                        text += "+";
                }

                if(  text.length() != 1 && text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != '(' && !Character.isDigit(text.charAt(text.length() - 1))){
                    text = text.substring(0, text.length() - 1) + '+';
                }
                //     判断是否除0
                text =  isDivZero(text);
                edit_screen.setText(text);
                break;

            case id.subtraction:

                if(text.charAt(text.length() - 1) == '0' && text.charAt(text.length() -2) == '/'){
                    text = text.substring(0,text.length() - 2);
                    Toast.makeText(this, "除数不能为0，请重新输入", Toast.LENGTH_SHORT).show();
                }

                if(text.length() != 0 && text.charAt(text.length() - 1)  == '.')
                    text +="0-";

                else if(text.length() == 0 || text.charAt(text.length() - 1) == '('){
                    text +="-";
                    sub_add = true;
                }
    //            前面数字
                if(Character.isDigit(text.charAt(text.length() - 1)) ||  text.charAt(text.length() -1) == ')'){
                    text += "-";
                }
    //                前面运算符
                else if(  text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != '(' && !Character.isDigit(text.charAt(text.length() - 1))){
                    text = text.substring(0, text.length() - 1) + '-';
                }

                text =  isDivZero(text);
                    edit_screen.setText(text);
                    break;

            case id.multiplication:
                if(text.length() == 0){
                    text = "";
                    break;
                }
                if(text.length() != 0 && text.charAt(text.length() - 1)  == '.')
                    text +="0*";
                if(Character.isDigit(text.charAt(text.length() - 1)) ||  text.charAt(text.length() -1) == ')' ){
                    text += "*";
                }
                if( text.length() != 1 && text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != '(' && !Character.isDigit(text.charAt(text.length() - 1)) ){
                    text = text.substring(0, text.length() - 1) + '*';
                }
                text =  isDivZero(text);
                edit_screen.setText(text);
                break;

            case id.divide:
                if (text.length() == 0){
                    text = "";
                    break;
                }
                if(text.length() != 0 && text.charAt(text.length() - 1)  == '.')
                    text +="0/";
                if(Character.isDigit(text.charAt(text.length() - 1)) ||  text.charAt(text.length() -1) == ')' ){
                    text += "/";
                }
                else if( text.length() != 1 && text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != '(' && !Character.isDigit(text.charAt(text.length() - 1)) ){
                    text = text.substring(0, text.length() - 1) + '/';
                }
                text =  isDivZero(text);
                edit_screen.setText(text);
                break;

            case id.bracket_left:
                if (text.length() == 0) {
                    text += "(";
                    left_brackets_flag++;
                    brackets_add = true;
                }
                else if(text.charAt(text.length() - 1) == '.' || text.charAt(text.length() - 1) == ')'){
                    text += "*(";
                    left_brackets_flag++;
                    brackets_add = true;
                }else if(!Character.isDigit(text.charAt(text.length() - 1))){
                    text += "(";
                    left_brackets_flag++;
                    brackets_add = true;
                }else{
                    text += "*(";
                    brackets_add = true;
                    left_brackets_flag++;
                }
                edit_screen.setText(text);
                break;

            case id.bracket_right:
                if(left_brackets_flag > 0){
                    if(Character.isDigit(text.charAt(text.length() - 1))){
                        text +=")";
                        left_brackets_flag--;
                    }

                    else if(text.charAt(text.length() - 1) == '.'){
                        text += "0)";
                        left_brackets_flag--;
                    }
                    else if(text.charAt(text.length() - 1) == '('){
                        text = text.substring(0,text.length() - 1);
                        left_brackets_flag--;
                    }
                }
                if(left_brackets_flag == 0){
                    brackets_add = false;
                }
                edit_screen.setText(text);
                break;

            case id.AC:
                text = "";
                sub_add = false;
                left_brackets_flag = 0;
                brackets_add = false;
                edit_screen.setText(text);
                result_screen.setText(text);
                break;
            case id.DEL:
                if(text.length() == 1 || text.length() == 0){
                        text = "";
                        left_brackets_flag = 0;
                        brackets_add = false;
                }else{
                    if(text.charAt(text.length() - 1) == ')'){
                        text = text.substring(0,text.length() - 1);
                        left_brackets_flag++;
                    }
                    else if(text.charAt(text.length() - 1) == '('){
                        text = text.substring(0,text.length() - 1);
                        if(--left_brackets_flag == 0)
                            brackets_add = false;
                    }
                    else if(text.charAt(text.length() - 1) == '-' && sub_add){
                        text = text.substring(0, text.length() - 1);
                        sub_add = false;
                    }else{
                        text = text.substring(0, text.length() - 1);
                    }
                }
                edit_screen.setText(text);
                break;
            case id.result:
                while (left_brackets_flag != 0 ){
                    text  += ")" ;
                    left_brackets_flag--;
                }
                InfixToSubfix its = new InfixToSubfix();
                String result = its.SuffixCaculate(its.InToSub(text));
                edit_screen.setText(result);
//                result_screen.setText(text);

                break;
            default:
                break;
        }
    }
    public  String isDivZero(String text){
        if(text.charAt(text.length() - 2) == '0' && text.charAt(text.length() -3) == '/'){
            text = text.substring(0,text.length() - 3);
            Toast.makeText(this, "除数不能为0，请重新输入", Toast.LENGTH_SHORT).show();
        }
        return  text;
    }
}
