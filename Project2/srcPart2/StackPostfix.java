import java.util.EmptyStackException;

public class StackPostfix<E>  {
    private E[] theData;
    public int maxSize=2;
    public int topOfStack = -1;

    /**
     * Default constructure
     */
    public StackPostfix(){
        theData=(E[])new Object [maxSize * 2];
    }

    /**
     * push method of stack
     * @param obj   push object
     * @return  obj
     */
    public E push(E obj) {
        if (topOfStack == maxSize- 1){
            reallocate();
        }
        topOfStack++;
        theData[topOfStack] = obj;

        return obj;
    }

    /**
     * pop method of stack
     * @return  pop element
     */
    public E pop() {
        int temp;
        if (topOfStack==-1) {
            throw new EmptyStackException();
        }
        temp=topOfStack;
        topOfStack-=1;
        return theData[temp];
    }

    /**
     * return end element of stack
     * @return  theData[topOfStack]
     */
    public E peek(){

        return theData[topOfStack];
    }

    /**
     * clear stack
     */
    public void clear(){
        for(int i=0;i<topOfStack+1;i++){
            theData[i]=null;
        }
        topOfStack=-1;
    }

    /**
     * check empty or not stack
     * @return  true or not
     */
    public boolean isEmpty(){
        if(topOfStack==-1){
            return true;
        }
        return false;
    }

    /**
     * realloc stack
     */
    private void reallocate(){
        E[] newArray = (E[])new Object [maxSize * 2];
        for (int i=0; i<maxSize; i++) {
            newArray[i] = theData[i];
        }
        maxSize*=2;
        theData = newArray;
    }
}
