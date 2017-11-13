package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class FunTTest {

	@Test
	public void testBridge() {
		Graph g = new Graph();
		String filepath = "D:/1.txt";
		g.readFile(filepath);
		FunT tt = new FunT();
		String word1 = "wawaw";
		String word2 = "threeaaa";
		tt.bridge(g, word1, word2);
		String ans = tt.strS;
		String except = "No word 1 or word 2 in the graph";
		System.out.println(ans + except);
		assertEquals(except, ans);
		//fail("Not yet implemented");
	}

}
