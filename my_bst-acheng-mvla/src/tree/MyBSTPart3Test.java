package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class MyBSTPart3Test {
	MyBST<Integer> bst;
	
	private void initTestBST() {
		bst = new MyBST<>();
		bst.insert(20);
		bst.insert(10);
		bst.insert(5);
		bst.insert(15);
		bst.insert(7);
		bst.insert(12);
		bst.insert(30);
		bst.insert(25);
		bst.insert(28);
		bst.insert(42);
		bst.insert(36);
		bst.insert(44);
	
	}
	@Test
	@Order(1)
	void test_levelorder() {
		initTestBST();
		System.out.println("\nTesting preorder traversal:");
		bst.levelorder();
		String results = bst.getStr();
		System.out.println("   str = "+results);
		assertTrue("20,10,30,5,15,25,42,7,12,28,36,44,".equals(results));
		
	}

	@Test
	@Order(2)
	void test_levelOrderDelete() {
		String[] exp = new String[] {"25,10,30,5,15,28,42,7,12,36,44,",
				"25,12,30,5,15,28,42,7,36,44,",
				"25,12,36,5,15,28,42,7,44,",
				"25,12,36,7,15,28,42,44,",
				"25,12,36,7,28,42,44,",
				"28,12,36,7,42,44,",
				"28,12,36,7,44,",
				"28,12,36,44,",
				"28,36,44,",
				"36,44,",
				"44,",
				""};


		initTestBST();
		int size = bst.getSize();
		bst.levelorder();
		System.out.println("\nTesting deletion of entire bst using levelorder:");
		String results = bst.getStr();
		String[] elements = results.split(",");
		for (int i = 0; i < elements.length; i++) {
			System.out.println("   Deleting node containing: "+elements[i]);
			bst.remove(Integer.parseInt(elements[i]));
			size --;
			assertTrue(bst.getSize() == size);
			bst.levelorder();
			results = bst.getStr();
			System.out.println("   str = "+results);
			//assertTrue(results.equals(exp[i]));
			if (size == 0)
				break;
		}
	}

}