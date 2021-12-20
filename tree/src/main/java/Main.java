public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.addNode(null,"hi");
        tree.addNode("hi","Kirill");
        tree.addNode("hi","Egor");
        tree.addNode("hi","Semen");
        tree.addNode("Semen",1);

        while(tree.hasNext()) {
            System.out.println(tree.next().toString());
        }
    }
}