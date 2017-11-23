package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iastate.cs228.hw4.EntryTree.Node;
/**
 * @author Alexander Stevenson
 *  Some test cases
 */

public class JUNIT {

	EntryTree<Object, String> tree = new EntryTree<Object, String>();

	@Test
	public void Basic() {
		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
		assertEquals(tree.root.child(), null);
		assertEquals(tree.root.parent(), null);
		assertEquals(tree.root.prev(), null);
		assertEquals(tree.root.next(), null);
	}

	@Test
	public void adderTest1() {
		Object[] keyarr = new Object[] { 'a', 'b', 'c' };
		String value = "computer science";
		tree.add(keyarr, value);

		Node node = tree.root;
		Node child = node.child;

		assertEquals(node.child(), child);
		assertEquals(child.key(), 'c');
		assertEquals(child.value(), "hello");
		assertEquals(child.child(), null);
		assertEquals(child.parent().key(), 'b');
		assertEquals(child.next(), null);
	}

	@Test
	public void searchTestCase() {
		Object[] keyarr = {};
		tree.add(keyarr, "hi");
		assertEquals(tree.search(keyarr), null);
	}

	@Test
	public void searchTestCase2() {
		Object[] keyarr = { 'D' };
		tree.add(keyarr, "hi");
		assertEquals(tree.search(keyarr), "hi");

	}

	@Test
	public void removeTestCase() {
		Object[] keyarr = new Object[] { 'a' };
		tree.add(keyarr, "hi");

		Node node = tree.root.child;

		assertEquals(tree.remove(keyarr), "hi");
		assertEquals(node.value(), null);
	}
}