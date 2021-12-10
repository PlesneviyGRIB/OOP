public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.addNode(null,"hi");
        tree.addNode("hi","Egor");
        tree.addNode("hi","Misha");
        tree.addNode("Misha","Love");
        tree.addNode("Egor","Computer");
        tree.addNode("hi", 13);

        tree.pr();
    }
}
