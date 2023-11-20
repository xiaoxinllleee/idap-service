package org.cmms.modules.xdgl.grkhpjsx.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class demo {
    public static void main(String[] args) {
        HashMap<String,String> map= new HashMap<>();
        method(map);
        Iterator iter = map.entrySet().iterator();
while (iter.hasNext()) { Map.Entry entry = (Map.Entry) iter.next();
Object key = entry.getKey();
Object val = entry.getValue();
        }
        }


    public static void method(HashMap<String,String> map){
        map.put("abc","123");
        map.put("abcd","1234");
    }
}
