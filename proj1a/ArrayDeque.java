/**
 * Created by mwang on 5/16/17.
 */
public class ArrayDeque<Item> implements Deque<Item> {
    public int size;
    public Item[] myArrayList = (Item []) new Object[8];
    public int arraySize = 8;
    public int firstIndex;
    public int lastIndex;
    public double factor = 0.25;
    public ArrayDeque() {
        myArrayList = (Item []) new Object[arraySize];
        firstIndex = 5;
        lastIndex = 4;
        size = 0;

    }

    public ArrayDeque(Item x) {
        myArrayList = (Item []) new Object[arraySize];
        firstIndex = 4;
        lastIndex = 4;
        myArrayList[firstIndex] = x;
        size = 1;
    }

    public int minusOne(int index) {
        if (index == 0) {
            return arraySize - 1;
        }
        return index - 1;
    }

    public int addOne(int index) {
        if (index == arraySize - 1) {
            return 0;
        }
        return index + 1;
    }
    public void checkAndDoubleResize() {
        if (size == arraySize) {
            arraySize *= 2;
            Item[] doubleArrayList = (Item []) new Object[arraySize];
            if (firstIndex < lastIndex) {
                System.arraycopy(myArrayList, 0, doubleArrayList, 0, size);
            }
            else {
                System.arraycopy(myArrayList, firstIndex, doubleArrayList, 0, size - firstIndex);
                System.arraycopy(myArrayList, 0, doubleArrayList, size - firstIndex, lastIndex + 1);
                firstIndex = 0;
                lastIndex = size - 1;
            }
            myArrayList = doubleArrayList;
        }
    }

    public void checkAndHalfResize() {
        if (arraySize >= 16 && size < factor * arraySize) {
            arraySize /= 2;
            Item[] halfArrayList = (Item []) new Object[arraySize];
            if (firstIndex <= lastIndex) {
                System.arraycopy(myArrayList, firstIndex, halfArrayList, 0, size);
            }
            else {
                System.arraycopy(myArrayList, firstIndex, halfArrayList, 0, arraySize * 2 - firstIndex);
                System.arraycopy(myArrayList, 0, halfArrayList, arraySize * 2 - firstIndex, lastIndex + 1);

            }
            firstIndex = 0;
            lastIndex = size - 1;
            myArrayList = halfArrayList;
        }
    }

    public void addFirst(Item x) {
        firstIndex = minusOne(firstIndex);
        myArrayList[firstIndex] = x;
        size += 1;
        checkAndDoubleResize();
    }

    public void addLast(Item x) {
        lastIndex = addOne(lastIndex);
        myArrayList[lastIndex] = x;
        size += 1;
        checkAndDoubleResize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String result = "";
        if (lastIndex >= firstIndex) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                result += (myArrayList[i] + " ");
            }
        }
        else {
            for (int i = firstIndex; i < arraySize; i++) {
                result += (myArrayList[i] + " ");
            }
            for (int k = 0; k <= lastIndex; k++) {
                result += (myArrayList[k] + " ");
            }
        }
        System.out.println(result);
    }

    public Item removeFirst(){
        Item result = myArrayList[firstIndex];
        myArrayList[firstIndex] = null;
        firstIndex = addOne(firstIndex);
        size -= 1;
        checkAndHalfResize();
        return result;
    }

    public Item removeLast() {
        Item result = myArrayList[lastIndex];
        myArrayList[lastIndex] = null;
        lastIndex = minusOne(lastIndex);
        size -= 1;
        checkAndHalfResize();
        return result;
    }

    public Item get(int index) {
        if (index >= size) {
            return null;
        }

        if (firstIndex <= lastIndex) {
            return myArrayList[firstIndex + index];
        }
        else {
            if (firstIndex + index < arraySize) {
                return myArrayList[firstIndex + index];
            }
            else {
                return myArrayList[index - (arraySize - firstIndex)];
            }
        }
    }
}
