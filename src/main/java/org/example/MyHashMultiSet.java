package org.example;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyHashMultiSet<T> implements Iterable<T>{
    private static final int INITIAL_CAPACITY = 8;
    private static final double rehash_size = 0.75;
    Node<T>[] node_array;
    private int buffer_size;
    private int size;       // сколько элементов у нас сейчас в массиве (без учета deleted)
    private int size_all;   // сколько элементов у нас сейчас в массиве (с учетом deleted)
    private int general_amount;            //включая повторы


    private int hashFunction(String s, int table_size, int key)
    {
        int hash_result = 0;
        for (int i = 0; i< s.length(); i++) {
            hash_result = (key * hash_result + Math.abs((int)s.charAt(i))) % table_size;
        }
        hash_result = (hash_result * 2 + 1) % table_size;
        return hash_result;
    }
    private int hashFunction1(String s, int table_size){
        return hashFunction(s, table_size,   table_size-1);
    }
    private int hashFunction2(String s, int table_size){
        return hashFunction(s, table_size,   table_size+1);
    }
    public MyHashMultiSet() {
        node_array = new Node[INITIAL_CAPACITY];
        size = 0;
        general_amount=0;
        buffer_size=8;
    }

    private void rehash() {
        size_all = 0;
        size = 0;
        general_amount=0;
        Node<T>[] help_array = new Node[buffer_size];
        System.arraycopy(node_array, 0, help_array, 0, buffer_size);
        Node<T>[] new_array = new Node[buffer_size*2];
        node_array=new_array;
        for(int i=0;i<buffer_size;i++){
            if(help_array[i]!=null && help_array[i].amount!=0){
                for(int j=0;j<help_array[i].amount;j++)
                    add(help_array[i].key);
            }
        }
    }
    private void resize() {
        size_all = 0;
        size = 0;
        general_amount=0;
        Node<T>[] help_array = new Node[buffer_size];
        System.arraycopy(node_array, 0, help_array, 0, buffer_size);
        Node<T>[] new_array = new Node[buffer_size*2];
        buffer_size=buffer_size*2;
        node_array=new_array;
        for(int i=0;i<buffer_size/2;i++){
            if(help_array[i]!=null && help_array[i].amount!=0){
                for(int j=0;j<help_array[i].amount;j++)
                    add(help_array[i].key);
            }
        }
    }

    public boolean add(T value)
    {

        if (size + 1 > rehash_size * buffer_size)
        resize();
        else if (size_all > 2 * size) {
            rehash(); // происходит рехеш, так как слишком много deleted-элементов
        }
        int h1 = hashFunction1(value.toString(), buffer_size);
        int h2 = hashFunction2(value.toString(), buffer_size);;
        int i = 0;
        int first_deleted = -1; // запоминаем первый подходящий элемент
        while (node_array[h1] != null && i < buffer_size)
        {
            if (node_array[h1].key.equals(value)) {
                node_array[h1].amount++;
                first_deleted = h1;
                break;
            }
            if (node_array[h1].amount==0) // находим место для нового элемента
            {
                first_deleted = h1;
                node_array[h1].key = value;
                node_array[h1].amount ++;
                break;
            }
            h1 = (h1 + h2) % buffer_size;
            i++;
        }
        if (first_deleted == -1) // если не нашлось подходящего места, создаем новый Node
        {
            node_array[h1] = new Node(value);
            size_all++; // так как мы заполнили один пробел, не забываем записать, что это место теперь занято
        }
        size++; // и в любом случае мы увеличили количество элементов
        general_amount++;
        return true;
    }
    boolean remove(T value )
    {
        int h1 = hashFunction1(value.toString(), buffer_size);
        int h2 = hashFunction2(value.toString(), buffer_size);
        int i = 0;
        while (node_array[h1] != null && i < buffer_size)
        {
            if (node_array[h1].key.equals(value) && node_array[h1].amount!=0){
                node_array[h1].amount--;
                general_amount--;
                                                                                   //if(node_array[h1].amount==0)
                size--;
                return true;
            }
            h1 = (h1 + h2) % buffer_size;
            i++;
        }
        return false;
    }
    boolean contains(T value)
    {

        int h1 = hashFunction1(value.toString(), buffer_size); // значение, отвечающее за начальную позицию
        int h2 = hashFunction1(value.toString(), buffer_size); // значение, ответственное за "шаг" по таблице
        int i = 0;
        while (i < buffer_size)
        {
            if (node_array[h1] != null && node_array[h1].key.equals(value) && node_array[h1].amount!=0)                    ///!!!!
                return true; // такой элемент есть
            h1 = (h1 + h2) % buffer_size;
            ++i; // если у нас i >=  buffer_size, значит мы уже обошли абсолютно все ячейки, именно для этого мы считаем i, иначе мы могли бы зациклиться.
        }
        return false;
    }
    int getCount(T value)
    {
        int h1 = hashFunction1(value.toString(), buffer_size); // значение, отвечающее за начальную позицию
        int h2 = hashFunction1(value.toString(), buffer_size); // значение, ответственное за "шаг" по таблице
        int i = 0;
        while ( i < buffer_size)
        {
            if (node_array[h1] != null &&node_array[h1].key.equals(value))
                return  node_array[h1].amount; // такой элемент есть
            h1 = (h1 + h2) % buffer_size;
            ++i; // если у нас i >=  buffer_size, значит мы уже обошли абсолютно все ячейки, именно для этого мы считаем i, иначе мы могли бы зациклиться.
        }
        return 0;
    }
    void clear(){
        buffer_size=INITIAL_CAPACITY;
        size=0;
        size_all=0;
        general_amount=0;
        node_array=new Node[INITIAL_CAPACITY];
    }
    void printAll(){
        System.out.println("size= "+size);
        System.out.println("size_all= "+size_all);
        System.out.println("size_buffer= "+ buffer_size);
        System.out.println("general_amount= "+general_amount);
        for(int i=0;i<buffer_size;i++){
            if(node_array[i]!=null) {
                System.out.println("key= " + node_array[i].key+",  amount= "+node_array[i].amount+",  index= "+i);
            }
        }
    }




    @Override
    public Iterator<T> iterator() {
        return new MyHashMultiSetIterator();
    }

    private class MyHashMultiSetIterator implements Iterator<T> {
        private int current_index = -1;
        private int next_index = 0;
        private int remainder_amount = 0;
        private T current_value;

        @Override
        public boolean hasNext() {
            // Проверка наличия следующего элемента
            if(remainder_amount!=0){
                next_index=current_index;
                return true;
            }
            for(int i = current_index+1; i<buffer_size; i++){
                if(node_array[i]!=null && node_array[i].amount!=0) {
                    next_index =i;
                    remainder_amount=node_array[i].amount;
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            // Получение следующего элемента
            current_index=next_index;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T currentKey = node_array[next_index].key;
            current_index=next_index;
            remainder_amount--;
            return currentKey;
        }
    }
    public int size(){
        return size;
    }
}


