import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Stack;

public class Tree <T> implements Iterator {
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

    public ArrayList<T> getBFS() {
        ArrayList<T> cur = new ArrayList<>();
        for(Node<T> current: BFS())
            cur.add(current.data);
        return cur;
    }

    public ArrayList<T> getDFS() {
        ArrayList<T> cur = new ArrayList<>();
        for(Node<T> current: DFS())
            cur.add(current.data);
        return cur;
    }

    private void addWithBFS(T parent, T child) {
        if(root == null) root = new Node(child);
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
    }

    private void addWithDFS(T parent, T child) {
        if(root == null) root = new Node(child);
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
    }


    private void rmWithBFS(T child) {
        if(root == null) root = new Node(child);
        if(root.data.equals(child)){
            root = null;
            return;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Node tmp = root;
        queue.add(root);

        do {
            tmp = queue.poll();
            ArrayList<Node> r = tmp.children;
            for(int i = 0; i<r.size();i++) {
                if(r.get(i).data.equals(child)) {
                    tmp.children.remove(i);
                    return;
                }
            }
            queue.addAll(tmp.children);
        }
        while (!queue.isEmpty());
    }

    public void addNode(T parent, T child) {
        addWithBFS(parent,child);
        //addWithDFS(parent,child);
    }

    public void rmNode (T child) {
        rmWithBFS(child);
    }

    @Override
    public boolean hasNext() {
        return current < BFS().size();
//        return current < DFS().size();
    }

    @Override
    public Object next() {
        current++;
        return BFS().get(current - 1).data;
//        return current < DFS().size();
    }

//    public void pr() {
//        for(Node current: BFS()) System.out.println(current.data);
//        System.out.println();
//        for(Node current: DFS()) System.out.println(current.data);
//    }
}