import javafx.util.converter.IntegerStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EmptyStackException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            FileInputStream fis = new FileInputStream("test_file_part2.txt");
            Character current = null;
            Character temp = '\n';
            String[] variable = new String[2];
            Character[] expression = new Character[2];
            int maxSize = 0, flag = 0, i = 0;
            double[] value = new double[2];
            int variable_count = 0;
            int variable_size = 2;
            StringBuilder temp_variable = new StringBuilder();
            while (fis.available() > 0) {
                current = (char) fis.read();
                if (temp != ' ' && current != ' ') {
                    temp_variable.append(current);
                }
                if (current == '=') {
                    if (variable_count >= variable_size) {
                        String[] temp_var = new String[variable_size + 1];
                        double[] temp_value = new double[variable_size + 1];
                        variable_size += 1;
                        for (int index = 0; index < variable_count; index++) {
                            temp_value[index] = value[index];
                        }
                        value = temp_value;
                        for (int index = 0; index < variable_count; index++) {
                            temp_var[index] = variable[index];
                        }
                        variable = temp_var;
                    }
                    StringBuilder sb = new StringBuilder();
                    String name = temp_variable.toString();
                    variable[variable_count] = name;
                    temp_variable = new StringBuilder();
                    char number = (char) fis.read();
                    while (number != '\n') {
                        sb.append(number);

                        number = (char) fis.read();
                    }
                    String num = (sb.toString());
                    double num1 = Double.parseDouble(num);
                    value[variable_count] = num1;
                    variable_count++;
                }

                if (flag == 1 && current != '\n') {
                    expression[maxSize] = current;
                    maxSize += 1;
                    Character[] newArray = new Character[maxSize + 1];
                    for (i = 0; i < maxSize; i++) {
                        newArray[i] = expression[i];
                    }
                    expression = newArray;
                }
                if ((current == '\n' && temp == '\r')) {
                    flag = 1;
                }
                temp = current;
            }
            Character[] newArray = new Character[maxSize];
            for (i = 0; i < maxSize; i++) {
                newArray[i] = expression[i];
            }
            expression = newArray;

            Calculate process = new Calculate(expression, variable, value);
            System.out.println("Expression is      " + process.getExpression());

            i = 0;

            if (process.isBalanced(expression)) {
                System.out.println("Isbalanced method check balanced status and it is Okey.");
            } else
                System.out.println("Balanced status is not Okey");
            double result = process.InfixtoPostfix();
            System.out.printf("Expression postfix status is      ");
            while (process.Output[i] != null) {
                System.out.printf("%s ", process.Output[i]);
                i++;
            }
            System.out.printf("\nExpression postfix result is       %f ", result);
        }
        catch (IllegalArgumentException e){
            System.err.println("Exception: Argument 'divisor' is 0");
        }
        catch(EmptyStackException e){
            System.err.println("Exception: Argument is not balanced");
        }
    }

}
