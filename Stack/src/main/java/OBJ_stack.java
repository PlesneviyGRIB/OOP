/**
 * @param <T> стэк
 */
public class OBJ_stack <T> {
    private int cntElem = 10;
    private T[] stack = (T[]) new Object[cntElem];

    public OBJ_stack() {
        cntElem = 0;
        stack = (T[]) new Object[cntElem];
    }

    public int count() {
        return cntElem;
    }

    /**
     *
     * @param elem элемент для пуша
     */
    public void push(T elem) {
        if(count()+1 >= stack.length) {
            T[] tmp = (T[]) new Object[cntElem*2];
            for (int i = 0; i < cntElem; i++) {
                tmp[i] = stack[i];
            }
            stack = tmp;
            stack[cntElem] = elem;
        }
        else {
            stack[cntElem] = elem;
            cntElem++;
        }
    }

    /**
     *
     * @return элемент со стека
     */
    public T pop() {
        if(cntElem>0) {
            cntElem--;
            return stack[cntElem];
        }
        return null;
    }

    /**
     *
     * @param stack1 вспомогательный стэк
     */
    public void pushStack(OBJ_stack<T> stack1){
        OBJ_stack <T> stack2 = new OBJ_stack<>();
        while(stack1.count()>0){
            stack2.push(stack1.pop());
        }
        while(stack2.count()>0) {
            push(stack2.pop());
        }
    }

    /**
     *
     * @param cnt количество элементов дл взятия
     * @return возвращает стек
     */
    public OBJ_stack popStack(int cnt){
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