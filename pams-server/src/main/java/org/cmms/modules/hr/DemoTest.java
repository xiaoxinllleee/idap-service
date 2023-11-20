package org.cmms.modules.hr;

import java.util.ArrayList;
import java.util.List;

public class DemoTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("10");
        list.add("15");


        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");

        list.removeAll(list2);

        list.forEach(s -> System.out.println(s));
    }
}
