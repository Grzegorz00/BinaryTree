import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TreeTest{

    private Tree tree1 = new Tree();
    private Tree tree2 = new Tree();

    @Test
    public void addTest() {
        //1
        tree1.add("A RR");
        assertEquals(
                "Creating no-value root and right->right node - A",
                'A',
                tree1.getRoot().getRight().getRight().getValue()
        );
        //2
        tree1.add("B RL");
        assertEquals(
                "Going through existing root->rightNode and creating new leftNode - B",
                'B',
                tree1.getRoot().getRight().getLeft().getValue()
        );
        //3
        tree1.add("C R");
        assertEquals(
                "Going through existing root and adding value to rightNode - C",
                'C',
                tree1.getRoot().getRight().getValue()
        );
        //4
        tree1.add("E RLRL");
        assertEquals(
                "Going through existing root->right->left->right and adding value to leftNode - E",
                'E',
                tree1.getRoot().getRight().getLeft().getRight().getLeft().getValue()
        );
        //4
        tree1.add("D RLR");
        assertEquals(
                "Going through existing root->right->left and adding value to rightNode - D",
                'D',
                tree1.getRoot().getRight().getLeft().getRight().getValue()
        );
        //5
        tree1.add("W");
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

        assertEquals("Check for max array length",4,tree1.getArrLen());
    }

    @Test
    public void lastAlphabeticalWordTest(){
        tree1.add("A RR");
        tree1.add("B RL");
        tree1.add("C R");
        tree1.add("E RLRL");
        tree1.add("D RLR");
        tree1.add("W");

        Scanner sc = null;
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
        assertEquals("Compare ABC to ABC",false,tree1.compareArrays("ABC".toCharArray(),"ABC".toCharArray()));
        assertEquals("Compare CBC to ABC",false,tree1.compareArrays("CBC".toCharArray(),"ABC".toCharArray()));
        assertEquals("Compare ABC to CBC",true,tree1.compareArrays("ABC".toCharArray(),"CBC".toCharArray()));
        assertEquals("Compare ABC to ABD",true,tree1.compareArrays("ABC".toCharArray(),"ABD".toCharArray()));
        assertEquals("Compare XYZ to XYZZ",false,tree1.compareArrays("XYZ".toCharArray(),"XYZZ".toCharArray()));
        assertEquals("Compare XYZZ to XYZ",true,tree1.compareArrays("XYZZ".toCharArray(),"XYZ".toCharArray()));
    }

    @Test
    public void reverseArrTest(){
        char arr[] = "ABC".toCharArray();
        arr = tree1.reverseArr(arr);
        assertEquals("Reverse", "CBA",String.valueOf(arr));
    }
}
