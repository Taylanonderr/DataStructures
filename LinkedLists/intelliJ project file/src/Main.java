import java.util.Iterator;
import java.util.*;

public class Main {
    public static void main(String[] arg) {
        try {
            Experiment[] exp_array = new Experiment[14];
            exp_array[0] = new Experiment("First Experiment", "10:45:20", true, 1, (float) 7);
            exp_array[1] = new Experiment("Third Experiment", "08:24:10", false, 3, (float) 2);
            exp_array[2] = new Experiment("First Experiment", "05:28:40", false, 2, (float) 17);
            exp_array[3] = new Experiment("Second Experiment", "06:30:20", true, 1, (float) 6);
            exp_array[4] = new Experiment("Second Experiment", "03:10:09", true, 2, (float) 5);
            exp_array[5] = new Experiment("Fourth Experiment", "02:56:09", true, 1, (float) 2);
            exp_array[6] = new Experiment("Fifth Experiment", "05:10:09", true, 1, (float) 7);
            exp_array[7] = new Experiment("Third Experiment", "02:14:09", true, 3, (float) 13);
            exp_array[8] = new Experiment("sixth Experiment", "02:23:09", true, 1, (float) 9);
            exp_array[9] = new Experiment("Third Experiment", "02:04:19", true, 2, (float) 10);
            exp_array[10] = new Experiment("Fourth Experiment", "02:10:09", true, 2, (float) 4);
            exp_array[11] = new Experiment("Fifth Experiment", "04:10:09", true, 2, (float) 11);
            exp_array[12] = new Experiment("Third Experiment", "06:10:09", true, 3, (float) 11);
            exp_array[13] = new Experiment("Fourth Experiment", "07:10:09", true, 3, (float) 3);


            ExperimentList list_object = new ExperimentList();
            list_object.addExp(exp_array[0]);
            list_object.helper_linkday();
            list_object.addExp(exp_array[1]);
            list_object.addExp(exp_array[2]);
            list_object.addExp(exp_array[3]);
            list_object.addExp(exp_array[4]);
            list_object.addExp(exp_array[5]);
            list_object.addExp(exp_array[6]);
            list_object.addExp(exp_array[7]);
            list_object.addExp(exp_array[8]);
            list_object.addExp(exp_array[9]);
            list_object.addExp(exp_array[10]);
            list_object.addExp(exp_array[11]);
            list_object.addExp(exp_array[12]);
            list_object.addExp(exp_array[13]);

            list_object.helper_linkday();

            list_object.listAll();
            System.out.println("\nSet and Get Experiments Method");
            System.out.println("\nGetExp : " + list_object.getExp(1, 2));
            list_object.setExp(1, 2, exp_array[5]);
            System.out.println("Output is :");
            System.out.printf("AfterSetExp GetExp : " + list_object.getExp(1, 2) + "\n\n");

            System.out.println("\nRemove Experiment Method");
            list_object.removeExp(1, 0);
            System.out.println("Removing experiment is \n" + exp_array[0]);
            Iterator itr = list_object.iterator();
            System.out.println("Output is :");
            while (itr.hasNext()) {
                System.out.println(itr.next().toString());
            }

            System.out.println("\nRemove Day Method");
            list_object.removeDay(2);
            System.out.println("Removing Day is 2");
            System.out.println("Output is :");
            itr = list_object.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next().toString());
            }


            System.out.println("\nOrder Day Method");
            list_object.orderDay(3);
            System.out.println("Ordering Day is 3");
            System.out.println("Output is :");
            itr = list_object.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next().toString());
            }


            System.out.println("\nOrder Experiments Method");
            ExperimentList orderedList = list_object.orderExperiments();
            System.out.println("Output is :");
            itr = orderedList.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next().toString());
            }

            System.out.println("\nListExp Experiment Method");
            System.out.println("Output is : for day 1");
            list_object.listExp(1);
        }
        catch (NoSuchElementException e){
            System.out.println("NoSuchElementException");
        }
        catch (NullPointerException n){
            System.out.println("NullPointerException");
        }
    }
}

