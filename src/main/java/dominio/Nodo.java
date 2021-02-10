package dominio;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Nodo<K,T> extends Hashtable<K,T> {
    private ArrayList<Nodo<K,T>> hijos;
    private K key;
    private T data;

    Nodo(K key, T newData) {
        hijos = new ArrayList<Nodo<K,T>>();
        data = newData;
    }

    public Nodo(Object key) {

    }

    public Iterator<T> iterator() {
        return null;
    }

    public ArrayList<Nodo<K, T>> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo<K, T>> hijos) {
        this.hijos = hijos;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}