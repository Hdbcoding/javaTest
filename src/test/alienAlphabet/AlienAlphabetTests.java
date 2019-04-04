package test.alienAlphabet;

import alienAlphabet.Solution;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlienAlphabetTests {
    @Test
    public void SimpleExample(){
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String result = new Solution().alienOrder(words);
        assertEquals("wertf", result);
    }

    @Test
    public void Cycle() {
        var words = new String[]{"ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby"};
        String result = new Solution().alienOrder(words);
        assertEquals("", result);
    }
}
