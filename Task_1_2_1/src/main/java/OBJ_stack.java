/**
 * Class stack
 * @param <T> type of elements of object stack
 */
public class OBJ_stack <T> {
    private int cntElem;
    private T[] stack = (T[]) new Object[cntElem];

    /**
     * Constructor of object stack
     */
    public OBJ_stack() {
        cntElem = 0;
        stack = (T[]) new Object[cntElem];
    }

    /**
     * @return count of elements in stack
     */
    public int count() {
        return cntElem;
    }

    /**
     * @param elem element for push to stack
     */
    public void push(T elem) {
        if(count()+1 >= stack.length) {
            T[] tmp = (T[]) new Object[(cntElem+1)*2];
            System.arraycopy(stack, 0,tmp,0,cntElem);
            stack = tmp;
            stack[cntElem] = elem;
            cntElem++;
        }
        else {
            stack[cntElem] = elem;
            cntElem++;
        }
    }

    /**
     * @return element from stack
     */
    public T pop() throws Exception {
        if(cntElem>0) {
            cntElem--;
            return stack[cntElem];
        }
        throw new Exception("Stack is empty!");
    }

    /**
     * @param stack1 supportive stack to push your stack in right order
     */
    public void pushStack(OBJ_stack<T> stack1) throws Exception{
        OBJ_stack <T> stack2 = new OBJ_stack<>();
        while(stack1.count()>0){
            stack2.push(stack1.pop());
        }
        while(stack2.count()>0) {
            push(stack2.pop());
        }
    }

    /**
     * @param cnt count of elements for pop from stack
     * @return returns object of class stack
     */
    public OBJ_stack popStack(int cnt) throws Exception {
        if(cnt > cntElem) throw new Exception("Cunt pop such count of elements!");
        OBJ_stack <T> stack1 = new OBJ_stack<>();
        while(cnt > 0){
            stack1.push(pop());
            cnt--;
        }
        OBJ_stack <T> stack2 = new OBJ_stack<>();
        while(stack1.count()>0){
            stack2.push(stack1.pop());
        }
        return stack2;
    }

}