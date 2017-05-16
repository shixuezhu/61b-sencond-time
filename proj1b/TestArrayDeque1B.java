import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by mwang on 5/16/17.
 */
public class TestArrayDeque1B {
    @Test
    public void testRandom() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> notSad1 = new ArrayDequeSolution<>();

        String testMesg = "";
        for (int i = 0; i < 10; i += 1) {
            int randFourMethod = StdRandom.uniform(4);
            if (randFourMethod == 0) {
                Integer toAdd = StdRandom.uniform(100);
                sad1.addFirst(toAdd);
                notSad1.addFirst(toAdd);
                testMesg += ("addFirst(" + toAdd + ")\n");
            }
            else if (randFourMethod == 1){
                Integer toAdd = StdRandom.uniform(100);
                sad1.addLast(toAdd);
                notSad1.addLast(toAdd);
                testMesg += ("addLast(" + toAdd + ")\n");
            }
            else if (randFourMethod == 2 && (! notSad1.isEmpty())) {
                Integer expected = notSad1.removeFirst();
                Integer actual = sad1.removeFirst();
                testMesg += ("removeFirst()\n");
                assertEquals(testMesg, expected, actual);

            }
            else if (randFourMethod == 3 && (! notSad1.isEmpty())) {
                Integer expected = notSad1.removeLast();
                Integer actual = notSad1.removeLast();
                testMesg += ("removeLast()\n");
                assertEquals(testMesg, expected, actual);
            }
        }


    }

}
