public class Main {
    public static void main(String[] args) throws Exception{
        Tree tree = new Tree();

        tree.addWithBFS(null,"hi");
        tree.addWithBFS("hi","Kirill");
        tree.addWithBFS("hi","Egor");
        tree.addWithBFS("hi","Semen");
        tree.addWithBFS("Semen",1);
        tree.rmWithBFS("hi");

        while(tree.hasNext()) {
            System.out.println(tree.next().toString());
        }
    }
}