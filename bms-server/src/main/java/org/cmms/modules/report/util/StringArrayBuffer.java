package org.cmms.modules.report.util;

/**
 * User: mkeasy
 * Date: 12-9-18
 */
public class StringArrayBuffer {
    private int count = 0;
    private String[] value;

    public StringArrayBuffer() {
        this(20);
    }

    public StringArrayBuffer(int size) {
        this.value = new String[size];
    }

    public void addString(StringBuffer buf) {
        addString(buf == null ? null : buf.toString());
    }

    public void addString(String str) {
        int newCount = count + 1;
        if (newCount > value.length)
            expandCapacity(newCount);
        count = newCount;
        value[count - 1] = str;
    }

    public void addString(String[] strs) {
        if (strs == null || strs.length == 0) return;
        int newCount = count + strs.length;
        if (newCount > value.length)
            expandCapacity(newCount);
        System.arraycopy(strs, 0, value, count - 1, strs.length);
        count = newCount;
    }

    public String[] getStringArray() {
        String[] strArray = new String[count];
        System.arraycopy(value, 0, strArray, 0, count);
        return strArray;
    }

    void expandCapacity(int minimumCapacity) {
        int newCapacity = (value.length + 1) * 2;
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        } else if (minimumCapacity > newCapacity) {
            newCapacity = minimumCapacity;
        }
        value = copyOf(value, newCapacity);
    }

    public static String[] copyOf(String[] original, int newLength) {
        String[] copy = new String[newLength];
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }
}
