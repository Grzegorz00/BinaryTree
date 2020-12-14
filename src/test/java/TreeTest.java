import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TreeTest{
    private final Tree tree = new Tree();
    private final Tree tree1 = new Tree();
    private final Tree tree2 = new Tree();

    @Test
    public void addTest() {
        tree1.add("A RR");
        tree1.add("B RL");
        tree1.add("C R");
        tree1.add("E RLRL");
        tree1.add("D RLR");
        tree1.add("W");

        assertEquals(
                "Creating no-value root and right->right node - A",
                'A',
                tree1.getRoot().getRight().getRight().getValue()
        );
        assertEquals(
                "Going through existing root->rightNode and creating new leftNode - B",
                'B',
                tree1.getRoot().getRight().getLeft().getValue()
        );
        assertEquals(
                "Going through existing root and adding value to rightNode - C",
                'C',
                tree1.getRoot().getRight().getValue()
        );
        assertEquals(
                "Going through existing root->right->left->right and adding value to leftNode - E",
                'E',
                tree1.getRoot().getRight().getLeft().getRight().getLeft().getValue()
        );
        assertEquals(
                "Going through existing root->right->left and adding value to rightNode - D",
                'D',
                tree1.getRoot().getRight().getLeft().getRight().getValue()
        );
        assertEquals("Only root value", 'W', tree1.getRoot().getValue());
    }

    @Test
    public void getArrLenTest(){
        tree1.add("A RR");
        tree1.add("B RL");
        tree1.add("C R");
        tree1.add("E RLRL");
        tree1.add("D RLR");
        tree1.add("W");

        assertEquals("Check for max array length",5,tree1.getArrLen());
    }

    @Test
    public void compareArraysTest(){
        char [] emptyArr = new char[3];
//        assertTrue("Compare empty array - 000 to ABC", tree1.compareArrays(emptyArr, "CBA0".toCharArray()));
//        assertFalse("Compare ABC to ABC", tree1.compareArrays("CBA0".toCharArray(), "CBA0".toCharArray()));
//        assertFalse("Compare CBC to ABC", tree1.compareArrays("CBC0".toCharArray(), "CBA0".toCharArray()));
//        assertTrue("Compare ABC to CBC", tree1.compareArrays("CBA0".toCharArray(), "CBC0".toCharArray()));
//        assertTrue("Compare ABC to ABD", tree1.compareArrays("CBA0".toCharArray(), "DBA0".toCharArray()));
//        assertTrue("Compare XYZ to XYZZ", tree1.compareArrays("ZYX0".toCharArray(), "ZZYX".toCharArray()));
//        assertFalse("Compare XYZZ to XYZ", tree1.compareArrays("ZZYX".toCharArray(), "ZYX0".toCharArray()));
//        assertFalse("Compare XHCA to XFA", tree1.compareArrays("ACHX".toCharArray(), "XFA0".toCharArray()));
//        assertTrue("Compare 0000 to XHCA", tree1.compareArrays("0000".toCharArray(), "ACHX".toCharArray()));
//        assertFalse("Compare", tree1.compareArrays("LSPZJIHCKRXZ".toCharArray(), "LJYRPOZQEALAXTBYR".toCharArray()));

        assertTrue("Compare ABC to ABC", tree1.compareArrays("ACHFJPZ".toCharArray(), "AFGPZZ0".toCharArray()));

    }

    @Test
    public void lastAlphabeticalWordTest(){
        tree1.add("A RR");
        tree1.add("B RL");
        tree1.add("C R");
        tree1.add("E RLRL");
        tree1.add("D RLR");
        tree1.add("W");

        Scanner sc;
        try {
            sc = new Scanner(new File("src/test/testData.txt"));
            while (sc.hasNext()){
                tree2.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tree1.lastAlphabeticalWord();
        tree2.lastAlphabeticalWord();
        //assertEquals("Check for max array length",4,tree2.getArrLen());

        assertEquals("Last alphabetical word", "EDBCW",tree1.getWord());
        assertEquals("Last alphabetical word", "XHCA",tree2.getWord());
    }

    @Test
    public void reverseArrTest(){
        char[] arr = "ABC".toCharArray();
        arr = tree1.reverseArr(arr);
        assertEquals("Reverse", "CBA",String.valueOf(arr));
        assertEquals("Reverse", "CBA",String.valueOf(arr));
    }

    @Test
    public void singularOutputTest(){
        // errors: 3 and 6
        int i = 8;
        Scanner sc;
        try {
            sc = new Scanner(new File("src/test/Zad2-testowe-dane/input" + i));
            while (sc.hasNext()) {
                tree.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("Last alphabetical word of " + i, getOutput(i), tree.getWord());
    }

    @Test
    public void copyTest(){
        char[] from = "ABCD".toCharArray();
        char[] to = "000".toCharArray();

        tree1.copy(from,to);
        for(char c : to) System.out.print(c);
    }

    public String getOutput(int i){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("src/test/Zad2-testowe-dane/output" + i));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sc.hasNext()? sc.nextLine() : "empty";
    }

}
