
/**
 * @author Akshit Patel
 * @Date 10/12/2016
 * CSE 143D DC
 * TA: Melissa Medsker
 * HW #2 File #2 HTMLManagerTest
 */

import java.util.*; // Queues & List.

/**
 * This program tests the removeAll() method of the HTMLManager class by
 * comparing the result with the correct output.
 */
public class HTMLManagerTest {

    public static void main(String[] args) {
        // Queue of tags to remove.
        Queue<HTMLTag> tags = new LinkedList<HTMLTag>();
        tags.add(new HTMLTag("ul", HTMLTagType.OPENING)); // <ul>
        tags.add(new HTMLTag("li", HTMLTagType.OPENING)); // <li>
        tags.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); // <br/>
        tags.add(new HTMLTag("li", HTMLTagType.OPENING)); // <li>
        tags.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); // <br/>
        tags.add(new HTMLTag("li", HTMLTagType.CLOSING)); // </li>
        tags.add(new HTMLTag("li", HTMLTagType.OPENING)); // <li>
        tags.add(new HTMLTag("li", HTMLTagType.CLOSING)); // </li>
        // give the queue to the HTMLManager.
        HTMLManager manager = new HTMLManager(tags);
        testOpening(manager);// test for opening tags.
        testClosing(manager); // test for closing tags
        testSelfClosing(manager);// test for self closing tags.
        testEmpty(manager);// test for empty situations.
    }

    /**
     * This method tests if the the removeAll() method can remove all the
     * opening tags of specific HTMLTag from the queue given.
     * 
     * @param manager HTMLManager to access the removeAll() method and getTags()
     * method.
     */
    public static void testOpening(HTMLManager manager) {
        // List to store correct output.
        List<HTMLTag> correct = new ArrayList<HTMLTag>();
        correct.add(new HTMLTag("ul", HTMLTagType.OPENING)); // <ul>
        correct.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); // <br/>
        correct.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); // <br/>
        correct.add(new HTMLTag("li", HTMLTagType.CLOSING)); // </li>
        correct.add(new HTMLTag("li", HTMLTagType.CLOSING)); // </li>
        System.out.println("Test 1 initiated to remove <li>");
        // remove <li> from the user queue.
        manager.removeAll(new HTMLTag("li", HTMLTagType.OPENING));
        testAnalysis(1, correct, manager);// evaluate results.
    }

    /**
     * This method tests if the the removeAll() method can remove the closing
     * tags of specific HTMLTag from the queue given.
     * 
     * @param manager HTMLManager to access the removeAll() method and getTags()
     * method.
     */
    public static void testClosing(HTMLManager manager) {
        List<HTMLTag> correct = new ArrayList<HTMLTag>();
        correct.add(new HTMLTag("ul", HTMLTagType.OPENING)); // <ul>
        correct.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); // <br/>
        correct.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); // <br/>
        System.out.println("Test 2 initiated to remove </li>");
        // remove </li> from the user queue.
        manager.removeAll(new HTMLTag("li", HTMLTagType.CLOSING));
        testAnalysis(2, correct, manager);// evaluate results.
    }

    /**
     * This method tests if the the removeAll() method can remove the
     * self-closing tags of specific HTMLTag from the queue given.
     * 
     * @param manager HTMLManager to access the removeAll() method and getTags()
     * method.
     */
    public static void testSelfClosing(HTMLManager manager) {
        List<HTMLTag> correct = new ArrayList<HTMLTag>();
        correct.add(new HTMLTag("ul", HTMLTagType.OPENING)); // <ul>
        System.out.println("Test 3 initiated to remove <br/>");
        // remove <br/> from the user queue.
        manager.removeAll(new HTMLTag("br", HTMLTagType.SELF_CLOSING));
        testAnalysis(3, correct, manager);// evaluate results.
    }

    /**
     * This method tests if the the removeAll() method can remove the last
     * remaining tag of specific HTMLTag from the queue given.
     * 
     * @param manager HTMLManager to access the removeAll() method and getTags()
     * method.
     */
    public static void testEmpty(HTMLManager manager) {
        List<HTMLTag> correct = new ArrayList<HTMLTag>();
        System.out.println("Test 4 initiated to remove <ul>");
        // remove <ul> from the user queue.
        manager.removeAll(new HTMLTag("ul", HTMLTagType.OPENING));
        testAnalysis(4, correct, manager);// evaluate results.
    }

    /**
     * This method evaluates the results of the tests done on the user queue by
     * comparing them to the correct result.
     * 
     * @param num the int representation of the test done.
     * @param correct The correct List of HTMLTags after the removeAll() method.
     * @param manager HTMLManager to access the getTags() method.
     */
    private static void testAnalysis(int num, List<HTMLTag> correct,
            HTMLManager manager) {
        int error = 0;// error counter.
        List<HTMLTag> clientList = manager.getTags();// get the user result.
        if (clientList.size() == correct.size()) {
            // for statement to check for any potential errors.
            for (int i = 0; i < correct.size(); i++) {
                if (!clientList.get(i).equals(correct.get(i))) {
                    error++;
                }
            }
        }
        if (error > 0 || correct.size() != clientList.size()) {
            System.out.println("Your output: " + clientList.toString());
            System.out.println("Correct output: " + correct.toString());
            System.out.println("Test " + num + " Failed!");
            System.out.println();
        } else {
            System.out.println("Test " + num + " passed!");
            System.out.println();
        }
    }
}