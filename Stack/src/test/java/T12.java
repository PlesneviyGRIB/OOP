import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class T12 {
    @Test
    public void Simple_test() throws Exception{
        OBJ_stack <Integer> stack = new OBJ_stack<>();
        stack.push(10);                             // try push
        stack.push(11);
        stack.push(90);
        stack.push(12);
        stack.push(17);
        stack.push(17);
        stack.pop();                                     // try pop

        OBJ_stack <Integer> stack1 = new OBJ_stack<>();
        stack1.push(1);
        stack1.push(4);
        stack1.push(9);
        stack1.push(0);
        stack.pushStack(stack1);                         // try pushStack

        OBJ_stack <Integer> stack2 = stack.popStack(5);  // try popStack
        OBJ_stack <Integer> stack3 = new OBJ_stack<>();
        stack3.push(17);
        stack3.push(1);
        stack3.push(4);
        stack3.push(9);
        stack3.push(0);

        for(int i=0;i<stack3.count();i++) {
            Assertions.assertEquals(stack2.pop(),stack3.pop());
        }
    }

    @Test
    public void Simple_withEmptyStack_test() throws Exception {
        OBJ_stack <Integer> stack = new OBJ_stack<>();
        stack.push(10);
        stack.push(11);
        stack.pop();
        stack.pop();

        Throwable e = assertThrows(Exception.class, () -> {
            stack.pop();
        });
        assertEquals(e.getMessage(), "Stack is empty!");
    }
}