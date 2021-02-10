/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jesús Pacheco Ortiz Institución: ServIT México
 * Descripción: Implementación básica de un árbol N-ario
 */
public class Arbol<K,T> {
    /**
     * @param args the command line arguments
     */
    private Nodo<K,T> raiz;

    public Arbol() {
        raiz = null;
    }

    public Nodo<K,T> getRaiz() {
        return raiz;
    }

    public Nodo<K,T> buscarRecursivo(Nodo<K,T> nodoPadre) {
        return buscarRecursivo(raiz, nodoPadre);
    }

    //Sobre carga de funciones/métodos
    Nodo<K,T> resultado = null;

    private Nodo<K,T> buscarRecursivo(Nodo<K,T> node, Nodo<K,T> nodoPadre) {
        try{
            if (node == null) {
                resultado = null;
            }

            if (nodoPadre == node) {
                resultado = node;
            }

            assert node != null;
            for (Nodo<K,T> tmp : node.getHijos()) {
                buscarRecursivo(tmp, nodoPadre);
            }

            return resultado;
        }catch (AssertionError error){
            return null;
        }
    }

    public synchronized Nodo<K,T> insertarNodo(T data) {
        return insertarNodo(data,null);
    }
    public synchronized Nodo<K,T> insertarNodo(T data, Nodo<K,T> nodoPadre) {
        try{
            assert data != null;
            return insertarNodo(new Nodo<K,T>(data), nodoPadre);
        }catch (AssertionError error){
            System.out.println("No se insertó el nodo");
            return null;
        }
    }

    private Nodo<K,T> insertarNodo(Nodo<K,T> nodoAInsertar, Nodo<K,T> nodoPadre) {
        if (raiz == null) {
            raiz = new Nodo<K,T>(null);
            raiz.getHijos().add(nodoAInsertar);
            if (nodoPadre != null){
                System.out.println("No existe el padre en éste árbol");
            }
        } else {
            if (nodoPadre == null){
                raiz.getHijos().add(nodoAInsertar);
            }else{
                Nodo<K,T> nodoPadreTmp = buscarRecursivo(nodoPadre);
                if (nodoPadreTmp != null) {
                    boolean b = true;
                    for (Iterator<Nodo<K,T>> it = nodoPadre.getHijos().iterator(); it.hasNext(); ) {
                        if (nodoAInsertar.equals(it.next())) {
                            b = false;
                        }
                    }
                    if (b) {
                        nodoPadre.getHijos().add(nodoAInsertar);
                    }
                } else {
                    System.out.println("No existe el padre en éste árbol");
                    return null;
                }
            }
        }
        return nodoAInsertar;
    }

    public void ImprimirArbol() {
        ImprimirArbol(raiz);
    }

    public void ImprimirArbol(Nodo<K,T> nodo) {
        for (Iterator<Nodo<K,T>> it = nodo.getHijos().iterator(); it.hasNext(); ) {
            Nodo<K,T> nodoTmp = it.next();
            System.out.println(nodo.getKey()+" - "+nodoTmp.getData());
            ImprimirArbol(nodoTmp);
        }
    }

    //Devolvemos el numero de nodo, buscandolo por valor
    public int buscarNodo(int valorNodo) {
        return nodosVisitados.indexOf(valorNodo);
    }

    static int contadorNodos = 0;
    static int contadorNulos = 0;
    ArrayList<Nodo<K,T>> nodosVisitados = new ArrayList<Nodo<K,T>>();
    static int nuevoContadorNulos = 0;

    static int tamaño = 0;

    public int obtenerTamaño(Nodo<K,T> nodo, Arbol<K,T> arbol) {
        if (nodo != null) {
            tamaño++;
            for (Nodo<K,T> tmp : nodo.getHijos()) {
                obtenerTamaño(tmp, arbol);
            }
        }
        return tamaño;
    }

//    public void eliminarNodo(Nodo<T> nodoAEliminar) //funcion para árbol binario
//    {
//        Nodo<T> pivote = raiz;
//        Nodo<T> tmp, nodoModificar, previo = null;
//
//        //Inicia la busqueda del nodo a eliminar
//        while (pivote != null && pivote != nodoAEliminar) {
//            previo = pivote;
//            if (pivote.valor < valor)
//                pivote = pivote.derecho;
//            else
//                pivote = pivote.izquierdo;
//        }
//        nodoModificar = pivote;
//
//        if (pivote != null)//Si se encontro el nodo
//        {
//            if (nodoModificar.derecho == null)
//                nodoModificar = nodoModificar.izquierdo;
//            else if (nodoModificar.izquierdo == null)
//                nodoModificar = nodoModificar.derecho;
//            else //Tiene dos hijos
//            {
//                tmp = nodoModificar.izquierdo;
//                while (tmp.derecho != null)
//                    tmp = tmp.derecho;
//                tmp.derecho = nodoModificar.derecho;
//                nodoModificar = nodoModificar.izquierdo;
//            }
//            if (pivote == raiz)
//                raiz = nodoModificar;
//            else if (previo.izquierdo == pivote)
//                previo.izquierdo = nodoModificar;
//            else previo.derecho = nodoModificar;
//        }
//    }
}