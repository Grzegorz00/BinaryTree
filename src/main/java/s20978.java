import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s20978 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        Tree tree = new Tree();

        while (sc.hasNext()){
            tree.add(sc.nextLine());
        }
    }
}

class Node {
    private char value;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getChild(char whichChild){
        return whichChild == 'L'? getLeft() : getRight();
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setChild(char whichChild, Node child){
        if (whichChild == 'L') setLeft(child);
        else setRight(child);
    }
}

class Tree {
    private Node root;
    private int arrLen = 0;

    public Tree() {
        this.root = new Node();
    }

    public void add(String line) {
        char value = line.charAt(0);

        // check if it's not the root
        if (line.length() <= 2){
            root.setValue(value);
            return;
        }

        Node tmpNodePointer = root;
        nodeCreator(value,line.substring(2),tmpNodePointer);

        if(line.substring(2).length() > arrLen) arrLen = line.substring(2).length();
    }

    public Node getRoot() {
        return root;
    }

    public int getArrLen() {
        return arrLen;
    }

    private void nodeCreator(char value, String children, Node tmpNodePointer){
        char child =  children.charAt(0);

        if (tmpNodePointer.getChild(child) == null) tmpNodePointer.setChild(child,new Node());

        if (children.length() > 1) nodeCreator(value,children.substring(1),tmpNodePointer.getChild(child));
        else if(children.length() == 1)tmpNodePointer.setChild(child,new Node(value));
    }

}


