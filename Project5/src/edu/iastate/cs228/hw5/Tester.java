package edu.iastate.cs228.hw5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class Tester {
	private DiGraph<String> TestGraph1;
//	private Digraph<String> TestGraph2;
	
	@Before
	public void setup() {
		TestGraph1 = new DiGraph<String>();
//		TestGraph2 = new DiGraph<String>();
		TestGraph1.addEdge("A", "B", 1);
		TestGraph1.addEdge("A", "F", 3);
		TestGraph1.addEdge("B", "F", 4);
		TestGraph1.addEdge("C", "D", 2);
		TestGraph1.addEdge("C", "J", 3);
		TestGraph1.addEdge("D", "G", 3);
		TestGraph1.addEdge("D", "A", 5);
		TestGraph1.addEdge("E", "A", 1);
		TestGraph1.addEdge("E", "B", 2);
		TestGraph1.addEdge("F", "H", 3);
		TestGraph1.addEdge("F", "G", 7);
		TestGraph1.addEdge("G", "J", 2);
		TestGraph1.addEdge("H", "I", 3);
		TestGraph1.addEdge("I", "J", 2);
	}

	@Test
	public void DFSTest1() {
		LinkedStack<String> answer = DFS.depthFirstSearch(TestGraph1);
		String expected = "ECDABFHIGJ";
		String actual = "";
		while (!answer.isEmpty()) {
			actual += answer.pop();
		}

		assertEquals(expected, actual);
	}

	/**
	 * Create a cycle, and ensure it returns null
	 */
	@Test
	public void DFSFailTest1() {
		TestGraph1.addEdge("I", "E", 1);
		LinkedStack<String> actual = DFS.depthFirstSearch(TestGraph1);
		LinkedStack<String> expected = null;

		assertEquals(expected, actual);
	}

	/**
	 * Create a cycle, and ensure it returns null[Again]
	 */
	@Test
	public void DFSFailTest2() {
		TestGraph1.addEdge("F", "C", 1);
		LinkedStack<String> actual = DFS.depthFirstSearch(TestGraph1);
		LinkedStack<String> expected = null;

		assertEquals(expected, actual);
	}

	/**
	 * Test MaxPath
	 */
	@Test
	public void MaxPathTest1() {
		LinkedStack<String> path = new LinkedStack<String>();
		String expectedPath = "CDABFGJ";
		String actualPath = "";
		String pathMessage = "Incorrect Path taken";
		Integer expectedScore = 21;
		Integer actualScore = MaxPath.findMaxPath(TestGraph1, path);
		String scoreMessage = "Incorrect MaxPath";
		while (!path.isEmpty()) {
			actualPath += path.pop();
		}

		// Ensure the correct max path was used, and the correct value was
		// given.
		assertEquals(pathMessage, expectedScore, actualScore);
		assertEquals(scoreMessage, expectedPath, actualPath);

		// Attempt to break it
		TestGraph1.addEdge("F", "C", 1);
		path = new LinkedStack<String>();
		try {
			MaxPath.findMaxPath(TestGraph1, path);
		} catch (IllegalArgumentException c) {
			return;
		}

		fail("Did not throw an exception when maxPath found cycle");
	}

	/**
	 * Test MaxPath
	 */
	@Test
	public void MaxPathTest2() {
		//Add a new edge initially and ensure the path and score change
		String pathMessage = "Incorrect Path taken";
		String scoreMessage = "Incorrect MaxPath";
		LinkedStack<String> path = new LinkedStack<String>();
		String expectedPath = "CABFGJ";
		Integer expectedScore = 22;
		TestGraph1.addEdge("C", "A", 8);
		Integer actualScore = MaxPath.findMaxPath(TestGraph1, path);
		String actualPath = "";
		while (!path.isEmpty()) {
			actualPath += path.pop();
		}
		assertEquals(pathMessage, expectedScore, actualScore);
		assertEquals(scoreMessage, expectedPath, actualPath);

		//Add another edge  and ensure the path and score change
		expectedPath = "CBFGJ";
		expectedScore = 23;
		TestGraph1.addEdge("C", "B", 10);
		actualScore = MaxPath.findMaxPath(TestGraph1, path);
		actualPath = "";
		while (!path.isEmpty()) {
			actualPath += path.pop();
		}
		assertEquals(pathMessage, expectedScore, actualScore);
		assertEquals(scoreMessage, expectedPath, actualPath);
		
		//Add another edge  and ensure the path and score change
		expectedPath = "CBGJ";
		expectedScore = 25;
		TestGraph1.addEdge("B", "G", 13);
		actualScore = MaxPath.findMaxPath(TestGraph1, path);
		actualPath = "";
		while (!path.isEmpty()) {
			actualPath += path.pop();
		}
		assertEquals(pathMessage, expectedScore, actualScore);
		assertEquals(scoreMessage, expectedPath, actualPath);
		
		//Add another edge  and ensure the path and score change
		expectedPath = "CGJ";
		expectedScore = 32;
		TestGraph1.addEdge("C", "G", 30);
		actualScore = MaxPath.findMaxPath(TestGraph1, path);
		actualPath = "";
		while (!path.isEmpty()) {
			actualPath += path.pop();
		}
		assertEquals(scoreMessage, expectedPath, actualPath);
		assertEquals(pathMessage, expectedScore, actualScore);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDFSExceptions() {
		DiGraph<String> nullGraph = null;
		DFS.depthFirstSearch(nullGraph);
	}

	// Test all exceptions and messages inside of MaxPath
	@Test
	public void testMaxPathExceptions() {
		DiGraph<String> nullGraph = null;
		LinkedStack<String> nullPath = null;
		LinkedStack<String> emptyPath = new LinkedStack<String>();
		LinkedStack<String> nonEmptyPath = DFS.depthFirstSearch(TestGraph1);
		String expectedMessage = null;

		// Test Null Path
		try {
			MaxPath.findMaxPath(TestGraph1, nullPath);
		} catch (IllegalArgumentException c) {
			expectedMessage = c.getMessage();
		}
		assertEquals("It should throw an exception if a null path is given", "null arguments", expectedMessage);

		// Test Null Graph
		expectedMessage = null;
		try {
			MaxPath.findMaxPath(nullGraph, emptyPath);
		} catch (IllegalArgumentException c) {
			expectedMessage = c.getMessage();
		}
		assertEquals("It should throw an exception if a null Graph is given", "null arguments", expectedMessage);

		// Test Non-Empty Path
		expectedMessage = null;
		try {
			MaxPath.findMaxPath(TestGraph1, nonEmptyPath);
		} catch (IllegalArgumentException c) {
			expectedMessage = c.getMessage();
		}
		assertEquals("It should throw an exception if path given is not empty", "maxPath is not empty",
				expectedMessage);

		// Test Graph with a cyle in it
		expectedMessage = null;
		TestGraph1.addEdge("F", "C", 1);
		try {
			MaxPath.findMaxPath(TestGraph1, emptyPath);
		} catch (IllegalArgumentException c) {
			expectedMessage = c.getMessage();
		}
		assertEquals("It should throw an exception if the graph has a cyle in it", "The graph has a cycle",
				expectedMessage);

		// Test empty graph, ensure topoOrder is Empty message is returned
		expectedMessage = null;
		TestGraph1 = new DiGraph<String>();
		try {
			MaxPath.findMaxPath(TestGraph1, emptyPath);
		} catch (IllegalStateException c) {
			expectedMessage = c.getMessage();
		}
		assertEquals("It should throw an exception graph is empty, creating an empty topoOrder", "topoOrder is empty",
				expectedMessage);
	}
}