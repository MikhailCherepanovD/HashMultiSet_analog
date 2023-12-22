package org.example;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.junit.Test;

import java.util.Collections;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class MyHashMultiSetTest {

    @Test
    public void testAdd() {
        MyHashMultiSet<Integer> my_ms=new MyHashMultiSet<Integer>();
        int size;
        my_ms.add(1);
        assertEquals(1, my_ms.size());
        my_ms.add(1);
        assertEquals(2, my_ms.size());
        my_ms.add(2);
        assertEquals(3, my_ms.size());
        my_ms.add(3);
        assertEquals(4, my_ms.size());
        my_ms.add(3);                            // такое же значение
        assertEquals(5, my_ms.size());
    }
    @Test
    public void testRemove() {
        MyHashMultiSet<Integer> ms=new MyHashMultiSet<Integer>();
        int size;
        ms.add(1);
        ms.add(1);
        ms.add(2);
        ms.add(3);
        ms.add(3);
        ms.remove(4);// такого нет
        assertEquals(5, ms.size());
        ms.remove(3);
        assertEquals(4, ms.size());
        ms.remove(1);
        assertEquals(3, ms.size());
        ms.remove(1);
        assertEquals(2, ms.size());
        ms.remove(1);                        // уже удалили все 1
        assertEquals(2, ms.size());
        ms.remove(2);
        assertEquals(1, ms.size());
        ms.remove(3);
        assertEquals(0, ms.size());
        ms.remove(3);                            // уже пусто
        assertEquals(0, ms.size());
        ms.add(3);
        assertEquals(1, ms.size());
        ms.remove(3);
        assertEquals(0, ms.size());
    }

    @Test
    public void testСontains() {
        MyHashMultiSet<Integer> ms=new MyHashMultiSet<Integer>();
        int size;
        ms.add(1);
        ms.add(1);
        ms.add(2);
        ms.add(3);
        ms.add(3);
        assertEquals(ms.contains(1), true);
        assertEquals(ms.contains(2), true);
        assertEquals(ms.contains(0), false);
        ms.remove(3);
        assertEquals(ms.contains(3), true);
        ms.remove(3);
        assertEquals(ms.contains(3), false);
        ms.add(3);
        assertEquals(ms.contains(3), true);
    }
    @Test
    public void testСount() {
        MyHashMultiSet<Integer> ms=new MyHashMultiSet<Integer>();
        ms.add(1);
        ms.add(1);
        ms.add(1);
        ms.add(1);
        ms.add(1);
        ms.add(1);
        ms.add(3);
        ms.add(3);
        ms.add(3);
        ms.add(3);
        assertEquals(ms.getCount(1), 6);
        assertEquals(ms.getCount(3), 4);
        ms.remove(1);
        ms.remove(3);
        assertEquals(ms.getCount(1), 5);
        assertEquals(ms.getCount(3), 3);
        ms.remove(1);
        ms.remove(3);
        ms.remove(1);
        ms.remove(3);
        ms.remove(1);
        ms.remove(3);
        assertEquals(ms.getCount(1), 2);
        assertEquals(ms.getCount(3), 0);
        ms.remove(1);
        ms.remove(3);
        ms.remove(1);
        ms.remove(3);
        assertEquals(ms.getCount(1), 0);
        assertEquals(ms.getCount(3), 0);
        for( int i=0;i<10;i++){
            ms.add(1000);
        }
        assertEquals(ms.getCount(1000), 10);
    }
    @Test
    public void testAddString() {
        MyHashMultiSet<String> my_ms = new MyHashMultiSet<>();
        my_ms.add("one");
        assertEquals(1, my_ms.size());
        my_ms.add("one");
        assertEquals(2, my_ms.size());
        my_ms.add("two");
        assertEquals(3, my_ms.size());
        my_ms.add("three");
        assertEquals(4, my_ms.size());
        my_ms.add("three"); // такое же значение
        assertEquals(5, my_ms.size());
    }

    @Test
    public void testRemoveString() {
        MyHashMultiSet<String> ms = new MyHashMultiSet<>();
        ms.add("one");
        ms.add("one");
        ms.add("two");
        ms.add("three");
        ms.add("three");
        ms.remove("four"); // такого нет
        assertEquals(5, ms.size());
        ms.remove("three");
        assertEquals(4, ms.size());
        ms.remove("one");
        assertEquals(3, ms.size());
        ms.remove("one");
        assertEquals(2, ms.size());
        ms.remove("one"); // уже удалили все "one"
        assertEquals(2, ms.size());
        ms.remove("two");
        assertEquals(1, ms.size());
        ms.remove("three");
        assertEquals(0, ms.size());
        ms.remove("three"); // уже пусто
        assertEquals(0, ms.size());
        ms.add("three");
        assertEquals(1, ms.size());
        ms.remove("three");
        assertEquals(0, ms.size());
    }

    @Test
    public void testСontainsString() {
        MyHashMultiSet<String> ms = new MyHashMultiSet<>();
        ms.add("one");
        ms.add("one");
        ms.add("two");
        ms.add("three");
        ms.add("three");
        assertEquals(ms.contains("one"), true);
        assertEquals(ms.contains("two"), true);
        assertEquals(ms.contains("zero"), false);
        ms.remove("three");
        assertEquals(ms.contains("three"), true);
        ms.remove("three");
        assertEquals(ms.contains("three"), false);
        ms.add("three");
        assertEquals(ms.contains("three"), true);
    }
    @Test
     public void testСountString() {
        MyHashMultiSet<String> ms = new MyHashMultiSet<>();
        ms.add("one");
        ms.add("one");
        ms.add("one");
        ms.add("one");
        ms.add("one");
        ms.add("one");
        ms.add("three");
        ms.add("three");
        ms.add("three");
        ms.add("three");
        assertEquals(ms.getCount("one"), 6);
        assertEquals(ms.getCount("three"), 4);
        ms.remove("one");
        ms.remove("three");
        assertEquals(ms.getCount("one"), 5);
        assertEquals(ms.getCount("three"), 3);
        ms.remove("one");
        ms.remove("three");
        ms.remove("one");
        ms.remove("three");
        ms.remove("one");
        ms.remove("three");
        assertEquals(ms.getCount("one"), 2);
        assertEquals(ms.getCount("three"), 0);
        ms.remove("one");
        ms.remove("three");
        ms.remove("one");
        ms.remove("three");
        assertEquals(ms.getCount("one"), 0);
        assertEquals(ms.getCount("three"), 0);
        for (int i = 0; i < 10; i++) {
            ms.add("thousand");
        }
        assertEquals(ms.getCount("thousand"), 10);
    }
    @Test
    public void testClear() {
        MyHashMultiSet<Integer> ms = new MyHashMultiSet<Integer>();
        ms.clear();
        for(int i=0;i<54;i++)
            ms.add(100000);
        assertEquals(ms.getCount(100000), 54);
        ms.clear();
        assertEquals(ms.getCount(100000), 0);
        assertEquals(ms.size(), 0);

    }
    @Test
    public void testSimilarWithLibraryFunctions() {
        MyHashMultiSet<String> ms = new MyHashMultiSet<String>();
        HashMultiSet<String> jms = new HashMultiSet<String>();
        ms.add("Hi");
        ms.add("my");
        ms.add("friend!!!");
        ms.add("how");
        ms.add("are");
        ms.add("you!!!");


        jms.add("Hi");
        jms.add("my");
        jms.add("friend!!!");
        jms.add("how");
        jms.add("are");
        jms.add("you!!!");

        Vector<String>my_vec= new Vector<String>();
        Vector<String>j_vec= new Vector<String>();;
        for(String s:ms)
            my_vec.add(s);
        for(String s:jms)
            j_vec.add(s);
        Collections.sort(my_vec);               // порядок элементов нам не важен(зависит от хэш-функции), поэтому сортируем
        Collections.sort(j_vec);
        assertEquals(my_vec, j_vec);
        ms.remove("are");
        ms.remove("you!!!");


        jms.remove("you!!!");
        jms.remove("are");
        my_vec.clear();
        j_vec.clear();
        for(String s:ms)
            my_vec.add(s);
        for(String s:jms)
            j_vec.add(s);
        Collections.sort(my_vec);               // порядок элементов нам не важен, поэтому сортируем
        Collections.sort(j_vec);
        assertEquals(my_vec, j_vec);
        for(int i=0;i<100;i++) {
            ms.add("Bro you are really cool today");
            jms.add("Bro you are really cool today");
            ms.add("Hi");
            jms.add("Hi");
        }
        my_vec.clear();
        j_vec.clear();
        for(String s:ms)
            my_vec.add(s);
        for(String s:jms)
            j_vec.add(s);
        Collections.sort(my_vec);               // порядок элементов нам не важен, поэтому сортируем
        Collections.sort(j_vec);
        assertEquals(my_vec, j_vec);
        assertEquals(ms.size(), jms.size());
        assertEquals(ms.contains("Hi"), jms.contains("Hi"));
        assertEquals(ms.getCount("Hi"), jms.getCount("Hi"));
        assertEquals(ms.getCount("bbb"), jms.getCount("bbb"));
        ms.clear();
        jms.clear();
        assertEquals(ms.size(), jms.size());
        for(int i=0;i<100;i++) {
            ms.add("Bro you are really cool today");
            jms.add("Bro you are really cool today");
            ms.add("Hi");
            jms.add("Hi");
        }
        my_vec.clear();
        j_vec.clear();
        for(String s:ms)
            my_vec.add(s);
        for(String s:jms)
            j_vec.add(s);
        Collections.sort(my_vec);               // порядок элементов нам не важен, поэтому сортируем
        Collections.sort(j_vec);
        assertEquals(my_vec, j_vec);
    }
}
