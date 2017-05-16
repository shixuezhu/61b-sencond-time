/**
 * Created by mwang on 5/16/17.
 */
public interface Deque<Item> {

    void addFirst(Item x);

    void addLast(Item x);

    boolean isEmpty();

    int size();

    void printDeque();

    Item removeFirst();

    Item removeLast();

    Item get(int index);

    Item getRecursive(int index);


}
