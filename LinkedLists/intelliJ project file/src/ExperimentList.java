import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ExperimentList implements Iterable{
    private Experiment[] experiments;
    private Experiment nextexperiment;
    private Node nextNode;
    private Node head;
    private Node day_node;

    /**
     * @return head head of experiment
     */
    public Node getHead(){
        return head;
    }

    /**
     *
     * @return day_node head of day
     */
    public Node getHeadDay(){
        return day_node;
    }

    /**
     *ExperimentList class constructure to create head of experiment and head of day
     */
    public ExperimentList(){
        head=new Node();
        head=null;
        day_node=new Node();
        day_node=null;
    }

    /**
     *Print list of experiment experiments and list of day experiments
     */
    public void listAll()
    {
        System.out.println("List experiment view:");
        Node last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.next;
        }
        System.out.println("List day view:");

        last = helper_linkday();
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.next;
        }
    }

    /**
     * Given experiment add in a list
     * @param data  data is an experiment to use adding in a list
     */
    public void addExp(Experiment data){
        int flag=0,flag2=0,counter=0;
        int while_control=0;
        Node newNode = new Node(data);
        newNode.next=null;
        if(head == null){
            head = newNode;
            day_node = newNode;
        }
        else{
            Node temp; //= new Node();
            temp =head;
            while (temp.next != null) {
                while_control=1;
                flag = temp.data.getDay();
                if (flag != temp.next.data.getDay()) {
                    if(data.getDay()<temp.data.getDay()){
                        flag2=1;
                        helperadd(data);
                        break;
                    }
                }
                temp = temp.next;
            }
            if(flag2==0 && while_control==1) {
                if(data.getDay()<temp.data.getDay()) {
                    helperadd(data);
                }
                else {
                    temp.next = newNode;
                    temp = temp.next;
                }
            }
            if(flag2==0 && while_control==0) {
                if(data.getDay()<temp.data.getDay()) {
                    newNode.next = temp;
                    temp = newNode;
                    head = temp;
                }
                else  {
                    newNode.next = temp.next;
                    temp.next = newNode;
                }

            }
        }
    }

    /**
     *  helper add function to using add the end of day according to experiment day
     * @param element   adding experiment
     *
     */
    public void helperadd(Experiment element){
        Node head_temp=head;
        if(head_temp.data.getDay()>element.getDay()){       //first element of list control
            head=new Node(element);
            Node temp=head;
            temp.next=head_temp;
            head=temp;
        }
        else{
            while(head_temp.next!=null){
                if(head_temp.next.data.getDay()>element.getDay()){
                    Node newElement = new Node(element);
                    newElement.next = head_temp.next;
                    head_temp.next = newElement;
                    return;
                }
                head_temp=head_temp.next;
            }
        }
    }



    /**
     *  My day link method I use for link day of first elements
     * @return day_node  day of head linked list
     */
    public Node helper_linkday(){
        Node temp=head;
        Node next_of_head=head.next;
        ExperimentList temp_experiment=new ExperimentList();
        if(temp.next==null){
            Experiment copy_experiment = new Experiment(temp.data.getSetup(), temp.data.getTime(), temp.data.getCompleted(), temp.data.getDay(), temp.data.getAccuracy());
            temp_experiment.addExp(copy_experiment);
        }
        while(temp.next!=null){
            if(temp==head && (temp.data.getDay()==temp.next.data.getDay())){
                Experiment copy_experiment = new Experiment(temp.data.getSetup(), temp.data.getTime(), temp.data.getCompleted(), temp.data.getDay(), temp.data.getAccuracy());
                temp_experiment.addExp(copy_experiment);
            }
            if(temp==head && (temp_experiment.head==null)){
                Experiment copy_experiment = new Experiment(temp.data.getSetup(), temp.data.getTime(), temp.data.getCompleted(), temp.data.getDay(), temp.data.getAccuracy());
                temp_experiment.addExp(copy_experiment);
            }
            if(temp.data.getDay()!=temp.next.data.getDay()) {
                Experiment copy_experiment = new Experiment(temp.next.data.getSetup(), temp.next.data.getTime(), temp.next.data.getCompleted(), temp.next.data.getDay(), temp.next.data.getAccuracy());
                temp_experiment.addExp(copy_experiment);
            }
            temp=temp.next;
        }
        day_node=temp_experiment.head;
        return day_node;
    }

    /**
     *  Set an experiment in a list
     * @param day   given setting day
     * @param index given index of elements in a day
     * @param e     given experiment to use setting in a list experiment
     */
    public void setExp(int day,int index, Experiment e) throws NoSuchElementException{
        int day_of_index=0;
        Node exp = head;
        while(exp.data.getDay()!=day || day_of_index!=index){
            if(exp.data.getDay()==day)
                day_of_index++;
            exp = exp.next;
        }

        exp.data.setSetup(e.getSetup());
        exp.data.setDay(e.getDay());
        exp.data.setTime(e.getTime());
        exp.data.setCompleted(e.getCompleted());
        exp.data.setAccuracy(e.getAccuracy());
    }

    /**
     *  Get experiment in a list
     * @param day  given getting day
     * @param index given index of elements in a day
     * @return  exp.data is a wanted to get
     */
    public Experiment getExp(int day,int index)throws NoSuchElementException{
        int day_of_index=0;
        Node exp = head;
        while(exp.next!=null){
            if(exp.data.getDay()==day && day_of_index==index)
                return exp.data;
            if(exp.data.getDay()==day && day_of_index!=index)
                day_of_index++;
            exp = exp.next;
        }
        return exp.data;
    }

    /**
     * remove according to given day and index remove experiment in list
     * @param day  given removing day
     * @param index given removing index of dat experiment
     */
    public void removeExp(int day,int index)throws NoSuchElementException{
        int day_of_index=0;
        int flag=0;
        Node exp = head;
        //Node temp = head;
        if(day==head.data.getDay()){
            if(day_of_index==index){
                exp=exp.next;
                head=exp;
                return;
            }
            while(exp.next!=null){
                if(day!=exp.next.data.getDay()){
                    break;
                }
                exp=exp.next;
            }
            head=exp;
            return;
        }
        while(exp.next.data.getDay()!=day || day_of_index!=(index)){
            if(exp.next==null){
                break;
            }
            //exeption
            if(exp.next.data.getDay()==day && day_of_index==index) {
                flag=1;
                break;
            }
            if(exp.next.data.getDay()==day)
                day_of_index++;

            exp = exp.next;
        }
        exp.next = exp.next.next;
        while (exp.next != null) {
            exp = exp.next;
        }
        exp.next = null;

        helper_linkday();

    }

    /**
     * Print all completed experiments in a given day
     * @param day   given a day for a list
     */
    public void listExp(int day)throws NoSuchElementException{
        Node exp = head;
        Node new_list=new Node();
        Node new_head=new_list;
        new_list.next=null;
        while(exp!=null){
            if(exp.data.getCompleted() && exp.data.getDay()==day) {
                System.out.println(exp.data);
            }
            exp = exp.next;
        }
    }

    /**
     * Remove all experimets a given day in a list
     * @param day   given day for remove all experiments
     */
    public void removeDay(int day)throws NoSuchElementException{
        Node exp = head;
        while(exp!=null){
            if(exp.data.getDay()==day) {
                removeExp(day,0);
            }
            exp = exp.next;
        }
        if(head.data.getDay()==day)
            head=head.next;
        helper_linkday();

    }

    /**
     * Ordering a given day acoording to accuracy in a list
     * @param day   given day for ordering experiments
     */
    public void orderDay(int day)throws NoSuchElementException{      //order experiments for a given day according to accuracy
        Node exp = head;
        Node temp=head;
        while(exp!=null) {
            if (exp.data.getDay() == day) {
                while (temp != null) {
                    if(temp.data.getDay()==day){
                        if((exp.data.getAccuracy()<temp.data.getAccuracy())  ){
                            setAll_helperOrderexperiments(exp.data,temp.data);
                        }
                    }
                    temp = temp.next;
                }
                temp = exp.next;
            }
            exp = exp.next;
        }
    }

    /**
     * The helper funtion using for copy of list element with new creating a list.This method swap two experiments fields
     * @param data  given experiment one to swaping
     * @param data1 given experiment two to swaping
     */
    private void setAll_helperOrderexperiments(Experiment data, Experiment data1) {
        Experiment temp = new Experiment();
        Experiment temp2 = new Experiment();

        temp.setAccuracy(data.getAccuracy());
        temp.setCompleted(data.getCompleted());
        temp.setSetup(data.getSetup());
        temp.setTime(data.getTime());
        temp.setDay(data.getDay());


        temp2.setAccuracy(data1.getAccuracy());
        temp2.setCompleted(data1.getCompleted());
        temp2.setSetup(data1.getSetup());
        temp2.setTime(data1.getTime());
        temp2.setDay(data1.getDay());

        data.setAccuracy(temp2.getAccuracy());
        data.setCompleted(temp2.getCompleted());
        data.setSetup(temp2.getSetup());
        data.setTime(temp2.getTime());
        data.setDay(temp2.getDay());

        data1.setAccuracy(temp.getAccuracy());
        data1.setCompleted(temp.getCompleted());
        data1.setSetup(temp.getSetup());
        data1.setTime(temp.getTime());
        data1.setDay(temp.getDay());

    }

    /**
     * This method order all experiments in a list according to accuracy and create a new list without changing new list and return new list head to show result another place
     * @return temp_experiment new list of ExperimentList to using new list head
     */
    public ExperimentList orderExperiments(){     //order experiments according to accuracy
        Node temp=head;
        ExperimentList temp_experiment=new ExperimentList();
        while(temp!=null){
            Experiment copy_experiment = new Experiment(temp.data.getSetup(), temp.data.getTime(), temp.data.getCompleted(), temp.data.getDay(), temp.data.getAccuracy());
            temp_experiment.addExp(copy_experiment);
            temp=temp.next;
        }

        Node new_list=temp_experiment.head;
        Node temp_list=temp_experiment.head;
        while(new_list!=null) {
            while (temp_list != null) {
                if((new_list.data.getAccuracy()<temp_list.data.getAccuracy())  ){

                    setAll_helperOrderexperiments(new_list.data,temp_list.data);
                    //new_list=new Node(temp.data)
                }
                temp_list = temp_list.next;
            }
            temp_list = new_list.next;
            new_list = new_list.next;
        }

        return temp_experiment;
    }

    /**
     * This method implement iterable interface using iterator method
     * @return   to create new iterator object namely head of list
     */
    @Override
    public Iterator<Experiment> iterator() {      //iterable fonction method overloading
        return new Iterator<Experiment>() {       //return a new node for return head of list
            Node current = head;

            /**
             *
             * @return null or not to check next experiment
             */
            @Override
            public boolean hasNext() {      //check the next node null or not
                return current != null;
            }

            /**
             *
             * @return  temp.data current experiment data returning
             */
            @Override
            public Experiment next() {            //check next node to return next node null or not
                if (!hasNext())
                    throw new NoSuchElementException();
                Node temp = current;
                current = current.next;
                return temp.data ;            //return next node
            }

            /**
             * @return current.data.toString() experiment to string method
             */
            @Override
            public String toString(){       //iterable object's toString method
                return current.data.toString();      //call Node class method of toString
            }


        };
    }

    /**
     * inner class to keep experiment in a linked list nood
     */
    public class Node  {        /*I create inncer node class for keep next node and data*/
        private Node next;
        private Experiment data;
        private Node nextDay;

        /**
         * Node consturcture to assing null
         */
        Node(){                 /*create node constructure*/
            next = null;
        }

        /**
         * Experiment information linking in a list
         * @param data  create a
         */
        Node(Experiment data){  /*take an experiment for change experiment object to node object and assign node's data field*/
            this.data = data;
            next = null;
        }

        /**
         * toString method
         * @return experiment to string method
         */
        public String toString(){ /*overriding the toString() method*/
            return this.data.getDay()+" "+this.data.getSetup()+" "+this.data.getTime() + " " + this.data.getCompleted() +" "+ this.data.getAccuracy();
        }
    }


}


