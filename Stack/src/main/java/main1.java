public class main1 {

    public static void main(String[] args) {
        OBJ_stack <Integer> stack = new OBJ_stack<>();

        stack.push(10);
        stack.push(11);
        stack.push(90);
        stack.push(12);
        stack.push(17);
        OBJ_stack <Integer> stack1 = new OBJ_stack<>();
        stack1.push(1);
        stack1.push(4);
        stack1.push(9);
        stack1.push(0);
        stack.pushStack(stack1);


        OBJ_stack <Integer> stack2 = stack.popStack(3);
    }
}
