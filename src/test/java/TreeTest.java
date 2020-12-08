import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TreeTest{

    private Tree tree1 = new Tree();

    @Test
    public void addTest() {
        Tree tree = new Tree();
        tree.add("A");
        assertEquals("Only root value", 'A', tree.getRoot().getValue());

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
    }

    @Test
    public void getArrLenTest(){
        tree1.add("A RR");
        tree1.add("B RL");
        tree1.add("C R");
        tree1.add("E RLRL");
        tree1.add("D RLR");

        assertEquals("Check for max array length",4,tree1.getArrLen());
    }
}
