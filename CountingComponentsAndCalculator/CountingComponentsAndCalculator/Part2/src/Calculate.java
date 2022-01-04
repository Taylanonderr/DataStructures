import java.util.EmptyStackException;

public class Calculate {
    private StackPostfix Operator_Stack;     /*stack of postfix*/
    private String expression;               /*expression reading file*/
    private String []variable;               /*variables name reading file*/
    private double []value;                  /*value of variables reading file*/
    public String[] Output;                 /*last postfix status*/

    /**
     * default constructure
     */
    public Calculate(){
        Operator_Stack=new StackPostfix();
    }

    /**
     * get string expression
     * @return expression
     */
    public String getExpression(){
        return expression;
    }

    /**
     * consturcture for reading file expressions copy and variables value and name
     * @param first_expression reading file character array
     * @param data_variable reading file string array
     * @param value_of_variable reading file value of double array
     */
    public Calculate(Character []first_expression,String []data_variable,double []value_of_variable){
        Operator_Stack=new StackPostfix();
        StringBuilder sb = new StringBuilder();

        for (Character ch : first_expression) {
            sb.append(ch);
        }
        expression = sb.toString();

        sb = new StringBuilder();
        for (String ch : data_variable) {
            sb.append(ch);
            sb.append(" ");
        }
        String temp=sb.toString();
        variable = temp.split(" ");
        value=new double [value_of_variable.length];
        for(int i=0;i<value_of_variable.length;i++){
            value[i]=value_of_variable[i];
        }
    }

    /**
     * main convert postfix and calculate value of expression method
     * @return result double calculate result
     */
    public double InfixtoPostfix(){         /*main convert postfix and calculate value of expression method*/
        int i=0;
        String[] tokens = expression.split(" ");    /*parsing expression according to space*/
        Output=new String[tokens.length];                  /*create string array*/
        //StringBuilder Output = new StringBuilder();
        double result=0;
        int pre_val=0,previous_val=0,counter=0;     /*pre_val is precedunce value previous val is previus precedunce value*/

        for (String t : tokens){                    /*iterate loop for all expression elements*/
            if(!Operator_Stack.isEmpty()){           /*it's for stack is not empty*/
                pre_val=precedence(t);
                previous_val=pre_val;

            }
            else {                                  /*for empty stack*/
                previous_val=pre_val;
                pre_val = precedence(t);
            }

            if(pre_val==0){                         /*open paranthesis*/
                Operator_Stack.push(t);             /*push stack*/
            }
            else if(pre_val==1){                    /*clos paranthesis*/
                while(!Operator_Stack.isEmpty() && !(((String)Operator_Stack.peek()).equals("("))){
                    Output[counter]=((String)Operator_Stack.peek());
                    String temp=(String) Operator_Stack.pop();
                    counter++;
                }
                if(!Operator_Stack.isEmpty()) {
                    String temp = (String) Operator_Stack.pop();
                }
                if(!Operator_Stack.isEmpty() && precedence(((String)Operator_Stack.peek()))==4){
                    Output[counter]=((String)Operator_Stack.peek());
                    String temp=(String) Operator_Stack.pop();
                    counter++;
                }
            }
            else if(pre_val==2){                    /*plus and mines operator add stack*/
                Operator_Stack.push(t);
            }
            else if(pre_val==3){                    /*devide and multiply precedence*/
                if((pre_val==3 && previous_val==4)  ){      /*check equal precedence or big and pop push for this operators*/
                    String temp=(String) Operator_Stack.pop();
                    Output[counter]=(temp);
                    counter++;
                }
                else if((pre_val==3 && previous_val==3)  ){     /*check equal precedence or big and pop push for mines and divide operator*/
                    String temp=(String) Operator_Stack.pop();
                    if(!temp.equals("(")) {
                        Output[counter] = (temp);
                        counter++;
                    }
                    Operator_Stack.push(t);

                }
                else{                               /*it's not important precedence */
                    Operator_Stack.push(t);
                }
            }
            else if(pre_val==4){                    /*for abs sin and cos methods*/
                if( previous_val==3) {
                    String temp = (String) Operator_Stack.pop();
                    Output[counter]=(temp);
                    counter++;
                }
                /*push stack sin cos abs and paranthesis*/
                if (t.equals("sin(")) {
                    Operator_Stack.push("sin");
                    Operator_Stack.push("(");
                } else if (t.equals("cos(")) {
                    Operator_Stack.push("cos");
                    Operator_Stack.push("(");
                } else if (t.equals("abs(")){
                    Operator_Stack.push("abs");
                 Operator_Stack.push("(");
                 }
            }
            else{                   /*it's for numbers or variables add output*/
                Output[counter]=(t);
                counter++;
            }
        }
        /*last element of expression adding output*/
        while(!Operator_Stack.isEmpty()){
            String temp = (String) Operator_Stack.pop();
            Output[counter]=(temp);
            counter++;
        }

        i=0;
        StackPostfix Calculate_Stack=new StackPostfix();


        while(i<Output.length) {                    /*check and change variables of values in expression to calculate*/
            for(int t=0;t<variable.length;t++){
                if(variable[t].equals(Output[i])){
                    Output[i]=(Double.toString(value[t]));
                }
            }
            i++;
        }
        i=0;

        /*calculate value of expression*/
        while(Output[i] != null) {
            if(!Output[i].equals("\r") && !Output[i].equals("+") && !Output[i].equals("-") && !Output[i].equals("*") && !Output[i].equals("/") && !Output[i].equals("sin") && !Output[i].equals("cos") && !Output[i].equals("abs")   ){
                Calculate_Stack.push(Output[i]);
            }
            if(Output[i].equals("+") || Output[i].equals("-") || Output[i].equals("*") || Output[i].equals("/")){
                double val1=0,val2=0;
                String first=(String) Calculate_Stack.pop();
                String second=(String) Calculate_Stack.pop();
                val1=Double.parseDouble(first);
                val2=Double.parseDouble(second);
                if(Output[i].equals("+"))
                   result=val2+val1;
                else if(Output[i].equals("-"))
                    result=val2-val1;
                else if(Output[i].equals("*"))
                    result=val2*val1;
                else if(Output[i].equals("/")) {
                    if(val1==0){        /*check devide 0 and throw exception*/
                        throw new IllegalArgumentException();
                    }
                    result = val2 / val1;
                }
                Calculate_Stack.push(Double.toString(result));
            }
            else{       /*calculate sin cos abs value and push postfix stack*/
                if(Output[i]=="sin"){
                    result=sin(Double.parseDouble((String)Calculate_Stack.pop()));
                    Calculate_Stack.push(Double.toString(result));
                }
                else if(Output[i]=="cos"){
                    result=cos(Double.parseDouble((String)Calculate_Stack.pop()));
                    Calculate_Stack.push(Double.toString(result));
                }
                else if(Output[i]=="abs"){
                    result=abs(Double.parseDouble((String)Calculate_Stack.pop()));
                    Calculate_Stack.push(Double.toString(result));
                }
            }
            i++;
        }

        return result;
    }

    /**
     * Function for precedence return value to use
     * @param elem character of expression
     * @return  precedence value
     */
    int precedence(String elem) { /* Function for precedence return value to use  */
        if(elem.equals("("))
            return 0;
        else if(elem.equals(")"))
            return 1;
        else if(elem.equals("+")|| elem.equals("-"))
            return 2;
        else if(elem.equals("*" )|| elem.equals("/"))
            return 3;
        else if(elem.equals("sin(") || elem.equals("cos(") || elem.equals("abs("))   /*before parsing argument checking*/
            return 4;
        else if(elem.equals("sin") || elem.equals("cos") || elem.equals("abs"))   /*after parsing argument check*/
            return 4;

        return 5;
    }

    /**
     * sin method
     * @param x degree
     * @return result of sin
     */
    public double sin(double x){    /*sin method*/
        double PI=3.141592653589793238;
        double result, pre_sum, sum;
        int i = 1;
        if(x>=360){     /*it's for bigger degree than 360*/
            x=x%360;
        }
        if(x==180){     /*it calculate wrong result for only this*/
            return 0;
        }
        x*=PI/180;
        sum = result = x;                    // This should calculating the first term Value
        do    // The loop will calculate the Taylor Series
        {
            pre_sum = result;
            i++;
            sum = + sum * x * x / i;
            i++;
            sum = sum / i;
            sum = -sum;  // Add this line!
            result = pre_sum + sum;
        }
        while( result != pre_sum);

        return result;

        //source https://stackoverflow.com/questions/34640142/java-sinex-taylor-series   help method
    }

    /**
     * cos method
     * @param x degree
     * @return result
     */
    public double cos(double x){    /*cos method*/
        if(x>=360){
            x=x%360;
        }
        if(90-x>=0){
            return(sin(90-x));
        }
        else {
            return (sin(90 - x + 360));
        }
    }

    /**
     * abs method
     * @param x value
     * @return x is result
     */
    public double abs(double x){    /*abs method */
        if(x<0){
            return(-1*x);
        }
        return x;

    }

    /**
     * check paranthesis balanced method
     * @param first_expression expression
     * @return  true or not checking paranthesis result
     */
    public boolean isBalanced(Character[] first_expression ){       /*check paranthesis balanced method*/
        StackPostfix stack_paranthesis=new StackPostfix();
        int i=0;
        StringBuilder sb = new StringBuilder();                     /*Character convert to stringbuilder and then convert to string array*/
        for (Character ch : first_expression) {
            sb.append(ch);
        }
        expression = sb.toString();
        String[] tokens = expression.split(" ");                /*expression parsing according to space*/
        for (String token : tokens){
            if (token.equals("sin(")) {
                stack_paranthesis.push("(");    /*push stack paranthesis for sin(  (its for sin has not space between '(' ) argument*/
            }
            else if (token.equals("cos(")) {
                stack_paranthesis.push("(");    /*push stack paranthesis for cos(  (its for cos has not space between '(' ) argument*/
            }
            else if (token.equals("abs(")){
                stack_paranthesis.push("(");    /*push stack paranthesis for abs(  (its for sin has not space between '(' ) argument*/
            }
            if(token.equals("(")){
                stack_paranthesis.push(token);      /*push stack paranthesis */
            }
            else if(token.equals(")")){
                stack_paranthesis.pop();
            }
        }
        if(stack_paranthesis.isEmpty()){           /*it's true balanced form*/
            return true;
        }
        else
            throw new EmptyStackException() ;       /*throw exception for not balanced paranthesis*/
    }
}

