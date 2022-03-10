import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Stack;

public class Tree <T> implements Iterable <T> {
    private static class Node <T> {
        T data;
        ArrayList<Node> children;

        Node(T data) {
            this.data = data;
            children = new ArrayList<>();
        }

        private void addChild(Node child) {
            this.children.add(child);
        }
    }

    private int current = 0;
    private Node root = null;

    private ArrayList<Node> BFS() {
        ArrayList<Node> sequence = new ArrayList<>();
        if(root == null) return sequence;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Node tmp = root;
        queue.add(tmp);

        do {
            tmp = queue.poll();
            sequence.add(tmp);
            queue.addAll(tmp.children);
        }
        while (!queue.isEmpty());
        return sequence;
    }

    private ArrayList<Node> DFS() {
        ArrayList<Node> sequence = new ArrayList<>();
        if(root == null) return sequence;
        Stack<Node> stack = new Stack<>();
        Node tmp = root;
        stack.add(tmp);

        do{
            tmp = stack.pop();
            sequence.add(tmp);
            for (int i = tmp.children.size() - 1; i >= 0; i--)
                stack.add((Node) tmp.children.get(i));
        }while(!stack.isEmpty());
        return sequence;
    }

    /**
     * get sequence with BFS
     * @return
     */
    public ArrayList<T> getBFS() {
        ArrayList<T> cur = new ArrayList<>();
        for(Node<T> current: BFS())
            cur.add(current.data);
        return cur;
    }

    /**
     * get sequence with DFS
     * @return
     */
    public ArrayList<T> getDFS() {
        ArrayList<T> cur = new ArrayList<>();
        for(Node<T> current: DFS())
            cur.add(current.data);
        return cur;
    }

    /**
     * add with BFS
     * @return
     */
    public void addWithBFS(T parent, T child) throws Exception{
        if(root == null) {
            root = new Node(child);
            return;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Node tmp = root;
        queue.add(tmp);

        do {
            tmp = queue.poll();
            if(tmp.data.equals(parent)) {
                tmp.addChild(new Node(child));
                return;
            }
            queue.addAll(tmp.children);
        }
        while (!queue.isEmpty());
        throw new Exception("No such parent in tree!");
    }

    /**
     * add with DFS
     * @return
     */
    public void addWithDFS(T parent, T child) throws Exception{
        if(root == null) {
            root = new Node(child);
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node tmp = root;
        stack.add(tmp);

        do{
            tmp = stack.pop();
            if(tmp.data.equals(parent)) {
                tmp.addChild(new Node(child));
                return;
            }
            for (int i = tmp.children.size() - 1; i >= 0; i--)
                stack.add((Node) tmp.children.get(i));
        }while(!stack.isEmpty());
        throw new Exception("No such parent in tree!");
    }


    public void rmWithBFS(T child) throws Exception{
        if(root == null) return;
        if(root.data.equals(child)){
            root = null;
            return;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Node tmp = root;
        queue.add(root);
        ArrayList<Node> r;

        do {
            tmp = queue.poll();
            r = tmp.children;
            for(int i = 0; i<r.size();i++) {
                if(r.get(i).data.equals(child)) {
                    tmp.children.remove(i);
                    return;
                }
            }
            queue.addAll(tmp.children);
        }
        while (!queue.isEmpty());
        throw new Exception("No such node in tree!");
    }

    private class iter <T> implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return current < BFS().size();
        }

        @Override
        public T next() {
            current++;
            return (T) BFS().get(current - 1).data;
        }
    }

    @Override
    public Iterator iterator() {
        return new iter();
    }

//    public void pr() {
//        for(Node current: BFS()) System.out.println(current.data);
//        System.out.println();
//        for(Node current: DFS()) System.out.println(current.data);
//    }
}