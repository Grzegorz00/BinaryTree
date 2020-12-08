import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class s20978 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        Tree tree = new Tree();

        while (sc.hasNext()){
            tree.add(sc.nextLine());
        }
        System.out.println(tree.getWord());

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
    private char longestWord [];
    private char arrTmp [];

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

    public String getWord() {
        lastAlphabeticalWord();
        longestWord = reverseArr(longestWord);

        return String.valueOf(longestWord);
    }

    public void lastAlphabeticalWord() {
        longestWord = new char[arrLen];
        arrTmp = new char[arrLen];

        Node tmpNodePointer = root;
        LAWRec(longestWord,arrTmp,tmpNodePointer,0);
    }

    public void LAWRec(char longestWord[],char arrTmp[], Node tmpNodepointer,int counter) {

        if (tmpNodepointer == null)
            return;

        arrTmp[counter] = tmpNodepointer.getValue();
        counter++;

        if (tmpNodepointer.getLeft() == null && tmpNodepointer.getRight() == null)
            if(compareArrays(longestWord,arrTmp)) longestWord = arrTmp.clone();
        else {
            LAWRec(longestWord,arrTmp,tmpNodepointer.getLeft(),counter);
            LAWRec(longestWord,arrTmp,tmpNodepointer.getRight(),counter);
        }
    }

    public boolean compareArrays(char[] longestWord, char[] arrTmp) {
        for (int i = 0; i < longestWord.length && i < arrTmp.length; i++){
            if(longestWord[i] < arrTmp[i]) return true;
        }
        if (longestWord.length > arrTmp.length) return true;
        return false;
    }

    public char[] reverseArr(char before[]){
        char tmp [] = before.clone();
        for (int i = before.length - 1, j = 0; i >= 0; i--, j++){
            before[i] = tmp[j];
        }
        return before;
    }

}


