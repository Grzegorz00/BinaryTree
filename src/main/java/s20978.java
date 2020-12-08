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
        System.out.println(tree.getWord());

    }
}

class Node {
    private char value;
    private Node left;
    private Node right;

    public Node() {
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

    public void createChild(char whichChild, Node child){
        if (whichChild == 'L') setLeft(child);
        else setRight(child);
    }
}

class Tree {
    private final Node root;
    private int arrLen = 0;
    private char[] longestWord;

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

        nodeCreator(value,line.substring(2), root);

        if(line.substring(2).length() > arrLen) arrLen = line.substring(2).length() + 1;
    }

    public Node getRoot() {
        return root;
    }

    public int getArrLen() {
        return arrLen;
    }

    private void nodeCreator(char value, String children, Node tmpNodePointer){
        char child =  children.charAt(0);

        if (tmpNodePointer.getChild(child) == null) tmpNodePointer.createChild(child,new Node());

        if (children.length() > 1)nodeCreator(value,children.substring(1),tmpNodePointer.getChild(child));
        if(children.length() == 1)tmpNodePointer.getChild(child).setValue(value);
    }

    public String getWord() {
        lastAlphabeticalWord();
        longestWord = reverseArr(longestWord);

        return String.valueOf(longestWord);
    }

    public void lastAlphabeticalWord() {
        longestWord = new char[arrLen];
        char[] arrTmp = new char[arrLen];

        LAWRec(longestWord, arrTmp, root,0);
    }

    public void LAWRec(char[] longestWord, char[] arrTmp, Node tmpNodePointer, int counter) {

        if (tmpNodePointer == null)
            return;

        arrTmp[counter] = tmpNodePointer.getValue();
        counter++;

        if (tmpNodePointer.getLeft() == null && tmpNodePointer.getRight() == null)
            if (compareArrays(longestWord, arrTmp)) longestWord = arrTmp.clone();
        LAWRec(longestWord,arrTmp,tmpNodePointer.getLeft(),counter);
        LAWRec(longestWord,arrTmp,tmpNodePointer.getRight(),counter);
    }

    public boolean compareArrays(char[] longestWord, char[] arrTmp) {
        for (int i = 0; i < longestWord.length && i < arrTmp.length; i++){
            if(longestWord[i] < arrTmp[i]) return true;
        }
        return longestWord.length > arrTmp.length;
    }

    public char[] reverseArr(char[] reverse){
        char[] tmp = reverse.clone();
        for (int i = reverse.length - 1, j = 0; i >= 0; i--, j++){
            reverse[i] = tmp[j];
        }
        return reverse;
    }

}


