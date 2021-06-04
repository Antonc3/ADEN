package tree;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class MyBSTTest {
	MyBST<String> letters;

	@Test
	@Order(1)
	void BST_InsertTest() {
		int size = 0;
		System.out.println("BST: Testing Insertion");
		letters = new MyBST<>();
		System.out.println("   Testing creation of BST");
		assertNull(letters.getRoot());
		assertEquals(letters.getSize(),size);
		
		// checking first insertion
		System.out.println("   Testing insert of a");
		assertTrue(letters.insert("a"));
		size++;
		assertTrue(letters.getRoot()!= null);
		assertEquals(letters.getSize(),size);
		BSTNode<String> root = letters.getRoot();
		assertEquals("a",root.getData());
		assertNull(root.getLeftChild());
		assertNull(root.getRightChild());
		System.out.println("   Testing insert of b");
		assertTrue(letters.insert("b"));
		size++;
		assertEquals(letters.getSize(),size);
		assertNull(root.getLeftChild());
		BSTNode<String> node = root.getRightChild();
		assertTrue(node != null);
		assertEquals(node.getData(),"b");
		
		System.out.println("   Testing insert of d");
		assertTrue(letters.insert("d"));
		size++;
		assertEquals(letters.getSize(),size);
		assertNull(root.getLeftChild());
		assertNull(node.getLeftChild());
		node = node.getRightChild();
		assertEquals(node.getData(),"d");
		
		System.out.println("   Testing insert of c");
		assertTrue(letters.insert("c"));
		size++;
		assertEquals(letters.getSize(),size);
		assertNull(root.getLeftChild());
		assertTrue(node.getLeftChild() != null);
		assertTrue(node.getRightChild() == null);
		assertEquals(node.getLeftChild().getData(),"c");
		
		System.out.println("   Testing insert of e");
		assertTrue(letters.insert("e"));
		size++;
		assertEquals(letters.getSize(),size);
		assertNull(root.getLeftChild());
		assertTrue(node.getLeftChild() != null);
		assertTrue(node.getRightChild() != null);
		assertEquals(node.getRightChild().getData(),"e");
		
		System.out.println("   Testing insert of A");
		assertTrue(letters.insert("A"));
		size++;
		assertEquals(letters.getSize(),size);
		assertTrue(root.getLeftChild() != null);
		assertTrue(root.getLeftChild().getLeftChild() == null);
		assertTrue(root.getLeftChild().getRightChild() == null);
		assertEquals(root.getLeftChild().getData(),"A");
		
		System.out.println("   Testing insert of duplicate values");
		assertFalse(letters.insert("a"));
		assertFalse(letters.insert("b"));
		assertFalse(letters.insert("c"));
		assertFalse(letters.insert("d"));
		assertFalse(letters.insert("e"));
		assertFalse(letters.insert("A"));
	}

	private void initLetters() {
		letters = new MyBST<>();
		letters.insert("a");
		letters.insert("b");
		letters.insert("d");
		letters.insert("c");
		letters.insert("e");
		letters.insert("A");
	}
	
	@Test
	@Order(2)
	void BST_SearchTest() {
		int size = 0;
		System.out.println("BST: Testing Search Function");
		initLetters();
		// checking first insertion
		System.out.println("   Testing for inserted values");
		assertTrue(letters.search("a"));
		assertTrue(letters.search("A"));
		assertTrue(letters.search("c"));
		assertTrue(letters.search("b"));
		assertTrue(letters.search("d"));
		assertTrue(letters.search("e"));

		System.out.println("   Testing for missing values");
		assertFalse(letters.search("B"));
		assertFalse(letters.search("1"));
		assertFalse(letters.search("C"));
		assertFalse(letters.search("q"));
		assertFalse(letters.search("Z"));
		assertFalse(letters.search("z"));

	}	
}
