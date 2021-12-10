import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Stack;

public class Tree <T> {
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

    private Node root = null;

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

    public void addNode(T parent, T child) {
        addWithBFS(parent,child);
        //addWithDFS(parent,child);
    }

    public void pr() {
        for(Node current: BFS()) System.out.println(current.data);
        System.out.println();
        for(Node current: DFS()) System.out.println(current.data);
    }
}