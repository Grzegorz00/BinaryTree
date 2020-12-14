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

    public Node() {}

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

        if(line.substring(2).length() + 1> arrLen) arrLen = line.substring(2).length() + 1;
    }

    public Node getRoot() {
        return root;
    }

    public int getArrLen() {
        return arrLen;
    }

    private void nodeCreator(char value, String children, Node tmpPointer){
        char child =  children.charAt(0);

        if (tmpPointer.getChild(child) == null) tmpPointer.createChild(child,new Node());

        if (children.length() > 1)nodeCreator(value,children.substring(1),tmpPointer.getChild(child));
        if(children.length() == 1)tmpPointer.getChild(child).setValue(value);
    }

    public String getWord() {
        lastAlphabeticalWord();
        longestWord = deleteNulls(longestWord);
        longestWord = reverseArr(longestWord);

        return String.valueOf(longestWord);
    }

    public void lastAlphabeticalWord() {
        longestWord = new char[arrLen];
        char[] arrTmp = new char[arrLen];

        LAWRec(arrTmp, root,0);
    }

    public void LAWRec( char[] arrTmp, Node tmpPointer, int counter) {

        if (tmpPointer == null)
            return;

        arrTmp[counter] = tmpPointer.getValue();
        counter++;

        if (tmpPointer.getLeft() == null && tmpPointer.getRight() == null) {
            if(counter <= arrLen)for (int i = counter; i < arrLen; i++) arrTmp[i] = 0;
            if (compareArrays(longestWord, arrTmp)) longestWord = arrTmp.clone();
        }

        LAWRec(arrTmp,tmpPointer.getLeft(),counter);
        LAWRec(arrTmp,tmpPointer.getRight(),counter);

    }

    public boolean compareArrays(char[] longestWord, char[] arrTmp) {
        int newLongLength = 0;
        int newTmpLength = 0;

        for (int i = 0; i < longestWord.length; i++){
            if(longestWord[i] != 0) newLongLength++;
            if(arrTmp[i] != 0) newTmpLength++;
        }

        char [] newLongAr = new char[newLongLength];
        copy(longestWord, newLongAr);
        newLongAr = reverseArr(newLongAr);
        char [] newTmpAr = new char[newTmpLength];
        copy(arrTmp, newTmpAr);
        newTmpAr = reverseArr(newTmpAr);

        for (int i = 0, j = 0; i < newLongLength && j < newTmpLength; i++, j++){
            if(newLongAr[i] < newTmpAr[i])
                return true;
            if(newLongAr[i] > newTmpAr[i])
                return false;
        }

        return newLongLength < newTmpLength;
    }

    public char[] reverseArr(char[] reverse){
        char[] tmp = reverse.clone();
        for (int i = reverse.length - 1, j = 0; i >= 0; i--, j++){
            reverse[i] = tmp[j];
        }
        return reverse;
    }

    public void copy(char[] from, char[] to){
        for (int i = 0; i < to.length; i++){
            to[i] = from[i];
        }
    }

    public char[] deleteNulls(char[] word){
        int newLength = 0;
        for (int i = 0; i < word.length; i++){
            if(word[i] != 0) newLength++;
        }
        char [] newWord = new char[newLength];
        copy(word,newWord);
        return newWord;
    }
    

}


