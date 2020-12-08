import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TreeTest{

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
        assertEquals("Last alphabetical word", "EDBCW",tree1.getWord());
        assertEquals("Last alphabetical word", "XHCA",tree2.getWord());
    }

    @Test
    public void compareArraysTest(){
        assertFalse("Compare ABC to ABC", tree1.compareArrays("ABC".toCharArray(), "ABC".toCharArray()));
        assertFalse("Compare CBC to ABC", tree1.compareArrays("CBC".toCharArray(), "ABC".toCharArray()));
        assertTrue("Compare ABC to CBC", tree1.compareArrays("ABC".toCharArray(), "CBC".toCharArray()));
        assertTrue("Compare ABC to ABD", tree1.compareArrays("ABC".toCharArray(), "ABD".toCharArray()));
        assertFalse("Compare XYZ to XYZZ", tree1.compareArrays("XYZ".toCharArray(), "XYZZ".toCharArray()));
        assertTrue("Compare XYZZ to XYZ", tree1.compareArrays("XYZZ".toCharArray(), "XYZ".toCharArray()));
    }

    @Test
    public void reverseArrTest(){
        char[] arr = "ABC".toCharArray();
        arr = tree1.reverseArr(arr);
        assertEquals("Reverse", "CBA",String.valueOf(arr));
    }
}
