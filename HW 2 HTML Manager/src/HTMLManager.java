
/**
 * @author Akshit Patel
 * @Date 10/12/2016
 * CSE 143D DC
 * TA: Melissa Medsker
 * HW #2 File #1 HTMLManager
 */

import java.util.*; // Queues & Lists.

/**
 * This class manages the HTMLTags by providing useful methods like adding the
 * tags, removing all specific HTMLTags, get the tags and a method that fixes
 * potential errors in the HTML.
 *
 */
public class HTMLManager {

    /**
     * This field stores the HTMLTags to be processed or managed.
     */
    private Queue<HTMLTag> tagStorage;

    /**
     * This constructor takes in HTMLTags that make up an HTML page.
     * 
     * @param page Queue of HTMLTags to be processed for using other methods.
     * @throws IllegalArgumentException if the Queue passed is null.
     * 
     * PostCondition: The Queue of HTMLTags passed remains in its original
     * state.
     */
    public HTMLManager(Queue<HTMLTag> page) {
        if (page.equals(null)) {
            throw new IllegalArgumentException("The HTMLTags can't be null!");
        }
        this.tagStorage = new LinkedList<HTMLTag>();// initialize the field.
        int size = page.size();
        for (int i = 0; i < size; i++) {
            this.tagStorage.add(page.peek());// add the tag.
            page.add(page.remove());// update the queue to get next tag.
        }
    }

    /**
     * This method adds the given HTMLTag to the end of the HTMLTags being
     * managed.
     * 
     * @param tag HTMLTag that needs to be added to the already present
     * HTMLtags.
     * @throws IllegalArgumentException if the HTMLTag passed is null.
     */
    public void add(HTMLTag tag) {
        if (tag == null) {
            throw new IllegalArgumentException();
        }
        this.tagStorage.add(tag);
    }

    /**
     * This method removes all occurrences of the given HTMLTag of specific type
     * like opening or closing "b" from the already present HTMLtags.
     * 
     * @param tag HTMLTag that needs to be removed from the HTMLTags.
     * @throws IllegalArgumentException if the HTMLTag passed is null.
     * 
     * PostCondition: The order of HTMLTags that are managed is not changed,
     * only the unwanted tags are removed and there place is taken by next
     * useful tag.
     */
    public void removeAll(HTMLTag tag) {
        if (tag == null) {
            throw new IllegalArgumentException();
        }
        int size = this.tagStorage.size();
        for (int i = 0; i < size; i++) {
            // if statement to check if the current tag equals the one to
            // remove.
            if (this.tagStorage.peek().equals(tag)) {
                this.tagStorage.remove();// remove the tag.
            } else {
                // since the match is not found, add the tag back to preserve
                // order.
                this.tagStorage.add(this.tagStorage.remove());
            }
        }
    }

    /**
     * This method helps to get HTMLTags being managed as an ArrayList of
     * HTMLTags.
     * 
     * @return ArrayList of HTMLTags used to manage or that have been processed.
     */
    public List<HTMLTag> getTags() {
        int resultSize = this.tagStorage.size();
        List<HTMLTag> resultList = new ArrayList<HTMLTag>();
        // For loop to add the contents to the list.
        for (int i = 0; i < resultSize; i++) {
            resultList.add(i, this.tagStorage.peek());
            this.tagStorage.add(this.tagStorage.remove());// restore the order.
        }
        return resultList;// return the List processed.
    }

    /**
     * This method helps to fix the HTMLTags used in HTML if there were any
     * missing or extra tags. The opening tags will be closed and self closing
     * tags will be added. However, if there is an closing tag then the method
     * will fix the HTML until there is a matching opening tag else if not found
     * the closing tag will be discarded.
     * 
     * PostCondition: The intended order and format of the HTML is preserved.
     */
    public void fixHTML() {
        Queue<HTMLTag> output = new LinkedList<HTMLTag>();// stores the output.
        Stack<HTMLTag> oTags = new Stack<HTMLTag>();// keeps track of open tags.
        // while loop to fix HTML until no every tag is checked.
        while (!this.tagStorage.isEmpty()) {
            // if statement to check for opening tag.
            if (this.tagStorage.peek().isOpening()) {
                oTags.push(this.tagStorage.peek()); // store the tag for later.
                output.add(this.tagStorage.remove());// add it to result.
            } else if (this.tagStorage.peek().isSelfClosing()) {
                output.add(this.tagStorage.remove());// add to the result.
            } else if (this.tagStorage.peek().isClosing()) {
                // if the closing tag matches the opening then add it to the
                // correct result.
                if (!oTags.isEmpty()
                        && oTags.peek().matches(this.tagStorage.peek())) {
                    output.add(this.tagStorage.remove());
                    oTags.pop();
                } else {
                    // if the matching is not found then add the matching from
                    // the storage till the matching is found.
                    while (!oTags.isEmpty()
                            && !oTags.peek().matches(this.tagStorage.peek())) {
                        // add to the result & update the storage.
                        output.add(oTags.pop().getMatching());
                    }
                    // if the storage is empty then no opening found.
                    if (oTags.isEmpty()) {
                        this.tagStorage.remove();// remove the unwanted.
                    }
                }
            }
        }
        // if there are opening tags remaining in the storage then add the
        // matching closing tag.
        while (!oTags.isEmpty()) {
            output.add(oTags.pop().getMatching());
        }
        this.tagStorage = output;
    }

}
