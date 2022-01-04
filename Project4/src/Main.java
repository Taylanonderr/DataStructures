public class Main {

    public static void main( String[] args) {
        LEX_Comparison lex=new LEX_Comparison();
        EUC_Comparison EUC=new EUC_Comparison();
        BMX_Comparison BMX=new BMX_Comparison();
        BinaryHeapQueue PQLEX = new BinaryHeapQueue(lex);
        BinaryHeapQueue PQEUC = new BinaryHeapQueue(EUC);
        BinaryHeapQueue PQBMX = new BinaryHeapQueue(BMX);
        Thread1 begin=new Thread1(PQLEX,PQEUC,PQBMX,args[0]);
        Thread thread1 = new Thread(begin);
        thread1.start();

    }


}
