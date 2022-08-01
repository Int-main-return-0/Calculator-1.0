package com.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToSubfix {

    public  List<String> InToSub(String str){

//        数字存储在sb当中
        StringBuffer sb = new StringBuffer();
//        存储运算符
        Stack<Character> stack = new Stack<>();
//        存储后缀表达式
        List<String> list = new ArrayList<>();

        char[] ch = str.toCharArray();
        for (int i = 0, chLength = ch.length; i < chLength; i++) {
            char c = ch[i];

                //                  判断是否为负数
            if (c == '+' || (c == '-' && i != 0 && ch[i - 1] != '(')) {
                doSymbol(stack, list, c, 1);
            } else if (c == '*' || c == '/' ) {
                doSymbol(stack, list, c, 2);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                char tem;
                while((tem = stack.pop()) != '('){
                    list.add(String.valueOf(tem));
                }
            } else {
                sb.append(c);
                if( (i + 1 < chLength ) && isSymbol(ch[i+1])  || i == chLength - 1){
                    list.add(String.valueOf(sb));
                    sb = new StringBuffer("");
                }
            }
        }

//        将栈中元素弹出
        while(!stack.empty()){
            list.add(String.valueOf(stack.pop()));
        }
        return list;
    }

    private boolean isSymbol(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }


    //    1: 将栈顶元素逐个取出与该元素比较，直到找到比该元素优先级低，然后压栈
    public static void doSymbol(Stack<Character> stack,List<String> list,char c,int level){

        while(!stack.empty()){
//            获取栈顶元素
            char top = (char) stack.pop();

            if(top == '('){
                stack.push(top);
                break;
            }else{
//                获取优先级
                int tem;
                if(top == '+' || top == '-') tem = 1; else  tem = 2;
//                判断优先级
                if(tem >= level){
                    list.add(String.valueOf(top));
                }else{
                    stack.push(top);
                    break;
                }
            }
        }
        stack.push(c);
    }

    public String SuffixCaculate(List<String> list){

        Stack<BigDecimal> stack = new Stack<>();
        for (String i : list) {
            // 如果是操作符   弹栈计算   注意计算顺序
            if (i.length() == 1 &&( i.charAt(0) == '+' || i.charAt(0) == '-' || i.charAt(0) == '*' || i.charAt(0) == '/')) {
                BigDecimal b = stack.pop();
                BigDecimal a = stack.pop();
                stack.push(caculate(a, b, i));
            } else {
                stack.push(new BigDecimal(i));
            }
        }
        return String.valueOf(stack.pop());
    }

    public static BigDecimal caculate(BigDecimal a, BigDecimal b, String  symbol){
        switch (symbol){
            case "+" :
                return a.add(b);
            case "-" :
                return a.subtract(b);
            case "*" :
                return a.multiply(b);
            case "/" :
                return a.divide(b,4, RoundingMode.HALF_UP);
        }
        throw new RuntimeException("Error");
    }
}

