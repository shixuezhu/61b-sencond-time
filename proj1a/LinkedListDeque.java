import sun.awt.image.ImageWatched;

/**
 * Created by mwang on 5/16/17.
 */
public class LinkedListDeque<Item> implements Deque<Item> {

    public class LinkedList {
        public LinkedList prev;
        public Item item;
        public LinkedList next;

        public LinkedList(LinkedList p, Object i, LinkedList r) {
            prev = p;
            item = (Item)i;
            next = r;
        }
    }

    public LinkedList sentinel;
    public int size;

    public LinkedListDeque() {
        sentinel = new LinkedList(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(Item f) {
        sentinel = new LinkedList(sentinel, null, sentinel);
        sentinel.next = new LinkedList(sentinel, f, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }


    @Override
    public void addFirst(Item x) {
        LinkedList temp = sentinel.next;
        sentinel.next = new LinkedList(sentinel, x, temp);
        temp.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(Item x) {
        LinkedList last = sentinel.prev;
        last.next = new LinkedList(last, x, sentinel);
        sentinel.prev = last.next;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        LinkedList temp = sentinel.next;
        String result = "";
        while (temp.item != null) {
            result += (temp.item + " ");
            temp = temp.next;
        }
        System.out.println(result);
    }

    @Override
    public Item removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        LinkedList first = sentinel.next;
        sentinel.next = first.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    @Override
    public Item removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        LinkedList last = sentinel.prev;
        sentinel.prev = last.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last.item;
    }

    @Override
    public Item get(int index) {
        LinkedList target = sentinel.next;
        while (index != 0) {
            if (target == sentinel) {
                return null;
            }
            target = target.next;
            index -= 1;
        }
        return target.item;
    }

    public Item getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    public Item getRecursive(int index, LinkedList l) {
        if (l == sentinel) {
            return null;
        }
        if (index == 0) {
            return l.item;
        }
        else {
            return getRecursive(index - 1, l.next);
        }
    }
}
