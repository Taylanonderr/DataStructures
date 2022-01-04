import java.util.EmptyStackException;

public class MatrixStack<E> {
    private E[] theData;
    public int maxSize=2;
    public int topOfStack = -1;

    /**
     * MatrixStack default consturcture allocate stack array
     */
    public MatrixStack(){
        theData=(E[])new Object [maxSize * 2];
    }

    /**
     * stack push method
     * @param obj   push object
     * @return  object which pushed
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
     * stack pop method
     * @return  end of stack object
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
     * stack peek method
     * @return  end of stack object
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
     * check stack is empty or not
     * @return  true or not
     */
    public boolean isEmpty(){
        if(topOfStack==-1){
            return true;
        }
        return false;
    }

    /**
     * reallocate stack array
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
