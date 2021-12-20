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

    public void addWithBFS(T parent, T child) {
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

    public void addWithDFS(T parent, T child) {
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


    public void rmWithBFS(T child) {
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
    }
//    public void rmWithDFS(T child) {
//        if(root == null) return;
//        Stack<Node> stack = new Stack<>();
//        Node tmp = root;
//        stack.add(tmp);
//
//        do{
//            tmp = stack.pop();
//            if(tmp.data.equals(child)) {
//                tmp.data = null;
//                clear();
//                return;
//            }
//            for (int i = tmp.children.size() - 1; i >= 0; i--)
//                stack.add((Node) tmp.children.get(i));
//        }while(!stack.isEmpty());
//    }
//
//    private void clear() {
//        if(root == null) return;
//        ArrayDeque<Node> queue = new ArrayDeque<>();
//        Node tmp = root;
//        queue.add(root);
//        ArrayList<Node> r;
//        T child = null;
//        do {
//            tmp = queue.poll();
//            r = tmp.children;
//            for(int i = 0; i<r.size();i++) {
//                if(r.get(i).data == child) {
//                    tmp.children.remove(i);
//                }
//            }
//            queue.addAll(tmp.children);
//        }
//        while (!queue.isEmpty());
//    }

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

    public void pr() {
        for(Node current: BFS()) System.out.println(current.data);
        System.out.println();
        for(Node current: DFS()) System.out.println(current.data);
    }
}