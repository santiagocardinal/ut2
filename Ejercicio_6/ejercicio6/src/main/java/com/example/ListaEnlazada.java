package com.example;

import java.util.Comparator;
import java.util.function.Predicate;

public class ListaEnlazada<T> implements TDALista<T> {
    
    protected Nodo<T> cabeza;
    
    public ListaEnlazada(){
        cabeza = null;
    }

    @Override
    public boolean agregar(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);

        if(cabeza == null){
            cabeza = nuevo;
        }else{
            Nodo<T> temp = cabeza;
            while (temp.getSiguiente() !=null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);

        }
        return true;
    }


    public void agregar(int indice, T elemento) 
    {
        if (indice < 0) throw new IndexOutOfBoundsException("Índice: " + indice);
        if (elemento == null) throw new IllegalArgumentException("No se permiten null");

        Nodo<T> nuevo = new Nodo<>(elemento);
        if (indice == 0) 
        {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            return;
        }

        Nodo<T> actual = cabeza;
        int contador = 0;

        while (actual != null) 
        {
            // cuando estamos en indice - 1
            if (contador == indice - 1) 
            {
                nuevo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevo);
                return;
            }
            actual = actual.getSiguiente();
            contador++;
        }
        // Si salimos del while, el índice no existe
        throw new IndexOutOfBoundsException("Índice: " + indice);
    }

    @Override
    public T obtener(int indice) 
    {
        if (indice < 0) return null;

        Nodo<T> actual = cabeza;
        int i = 0;

        while (actual != null) 
        {
            if (i == indice) 
            {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
            i++;
        }
        return null; //indice fuera de rango
    }

    // QUITAR Y ELIMINAR POR ELEMENTO

    public T quitar(T elemento) 
    {
        if (cabeza == null || elemento == null) 
        {
            return null;
        }

        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;

        while (actual != null) 
        {
            if (actual.getDato().equals(elemento)) 
            {
                if (anterior == null) 
                {
                    cabeza = actual.getSiguiente();
                } 
                else 
                {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                actual.setSiguiente(null); // desvincular
                return actual.getDato();   // devolver el dato
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return null;
    }


    public boolean eliminar(T elemento) 
    {
        if (cabeza == null || elemento == null) 
        {
            return false;
        }

        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;

        while (actual != null) 
        {
            if (actual.getDato().equals(elemento)) 
            {
                if (anterior == null) 
                {
                    cabeza = actual.getSiguiente();
                } 
                else 
                {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }
    

    //QUITAR Y ELIMINAR POR INDICE


    public T quitar(int indice) 
    {
        T elemento = obtener(indice);
        if (elemento == null) 
        {
            return null;
        }

        // Caso: cabeza
        if (indice == 0) 
        {
            T dato = cabeza.getDato();
            Nodo<T> aux = cabeza;
            cabeza = cabeza.getSiguiente();
            aux.setSiguiente(null); // desvincular
            return dato;
        }

        Nodo<T> anterior = cabeza;
        int i = 0;

        while (anterior != null && i < indice - 1) 
        {
            anterior = anterior.getSiguiente();
            i++;
        }

        Nodo<T> nodoAEliminar = anterior.getSiguiente();
        anterior.setSiguiente(nodoAEliminar.getSiguiente());

        nodoAEliminar.setSiguiente(null); // desvincular

        return nodoAEliminar.getDato();
    }

    public boolean eliminar(int indice) 
    {
        T elemento = obtener(indice);
        if (elemento == null) 
        {
            return false;
        }

        // Caso: cabeza
        if (indice == 0) 
        {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        Nodo<T> anterior = cabeza;
        int i = 0;

        while (anterior != null && i < indice - 1) 
        {
            anterior = anterior.getSiguiente();
            i++;
        }

        Nodo<T> nodoAEliminar = anterior.getSiguiente();
        anterior.setSiguiente(nodoAEliminar.getSiguiente());

        return true;
    }


    @Override
    public boolean contiene(T elemento) 
    {
        return indiceDe(elemento) != -1;
    }
    
    @Override
    public int indiceDe(T dato) 
    {
        if (dato == null) 
        {
            return -1;
        }
        Nodo<T> actual = cabeza;
        int indice = 0;
        while (actual != null) 
        {
            if (actual.getDato().equals(dato)) 
            {
                return indice;
            }
            actual = actual.getSiguiente();
            indice++;
        }
        return -1;
    }


    @Override
    public T buscar(Predicate<T> criterio) {
        
        if (criterio == null) return null;
        Nodo<T> temp = cabeza;

        while (temp !=null) {
            if(criterio.test(temp.getDato())){
                return temp.getDato();
            }
            temp= temp.getSiguiente();
        }
        return null;
    }    


    /*@Override
    public void ordenar(Comparator<T> comp) {
        if (cabeza == null) return;

        Nodo<T> actual = cabeza;

        while (actual != null) {
            Nodo<T> menor = actual;
            Nodo<T> temp = actual.getSiguiente();

            while (temp != null) {
                if (comp.compare(temp.getDato(), menor.getDato()) < 0) {
                    menor = temp;
                }
                temp = temp.getSiguiente();
            }

            T aux = actual.getDato();
            actual.setDato(menor.getDato());
            menor.setDato(aux);

            actual = actual.getSiguiente();
        }
    }*/



    // ORDENAR TOTAL
    public TDALista<T> ordenarTotal(Comparator<T> comp) 
    {
        if (cabeza == null) return this;

        Nodo<T> actual = cabeza;

        while (actual != null) 
        {
            Nodo<T> menor = actual;
            Nodo<T> temp = actual.getSiguiente();

            while (temp != null) 
            {
                if (comp.compare(temp.getDato(), menor.getDato()) < 0) 
                {
                    menor = temp;
                }
                temp = temp.getSiguiente();
            }

            // swap
            T aux = actual.getDato();
            actual.setDato(menor.getDato());
            menor.setDato(aux);

            actual = actual.getSiguiente();
        }

        return this; // ✅ ahora sí, al final
    }

    //ORDENAR PARCIAL
    @Override
    public TDALista<T> ordenarParcial(Comparator<T> comp) {
        if (cabeza == null) return this; // lista vacía → devolvemos lista vacía

        // 1. Copiamos los datos a una lista nueva para no modificar la original
        ListaEnlazada<T> resultado = new ListaEnlazada<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            resultado.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }

        // 2. Aplicamos Selection Sort sobre la lista nueva
        Nodo<T> i = resultado.cabeza;
        while (i != null) {
            Nodo<T> menor = i;
            Nodo<T> j = i.getSiguiente();

            // buscamos el menor en el resto de la lista
            while (j != null) {
                if (comp.compare(j.getDato(), menor.getDato()) < 0) {
                    menor = j;
                }
                j = j.getSiguiente();
            }

            // intercambiamos los datos de i y menor
            T aux = i.getDato();
            i.setDato(menor.getDato());
            menor.setDato(aux);

            i = i.getSiguiente();
        }

        return resultado;
    }


    @Override
    public int tamano() 
    {
        int contador = 0;
        Nodo<T> actual = cabeza;

        while (actual != null) 
        {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }

    @Override
    public boolean esVacio() 
    {
        return cabeza == null;
    }

    @Override
    public void vaciar() 
    {
        cabeza = null;
    }

    @Override
    public TDALista<T> invertir() 
    {
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;

        while (actual != null) 
        {
            Nodo<T> siguiente = actual.getSiguiente(); // guardo el resto

            actual.setSiguiente(anterior); // invierto el enlace

            anterior = actual; // avanzo anterior
            actual = siguiente; // avanzo actual
        }

        cabeza = anterior; // nueva cabeza

        return this;
    }

    @Override
    public TDALista<T> concatenar(TDALista<T> otra)
    {
        ListaEnlazada<T> resultado = new ListaEnlazada<>();

        // copiamos todos los de esta lista
        Nodo<T> actual = cabeza;
        while (actual != null)
        {
            resultado.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }

        // copiamos todos los de la otra lista
        for (int i = 0; i < otra.tamano(); i++)
            resultado.agregar(otra.obtener(i));

        return resultado;
    }

    @Override
    public TDALista<T> intercalar(TDALista<T> otra)
    {
        ListaEnlazada<T> resultado = new ListaEnlazada<>();

        Nodo<T> actual = cabeza;
        int j = 0; // índice para recorrer 'otra'

        // mientras ambas tengan elementos, tomamos uno de cada una
        while (actual != null && j < otra.tamano())
        {
            resultado.agregar(actual.getDato());       // uno de esta
            resultado.agregar(otra.obtener(j));        // uno de otra
            actual = actual.getSiguiente();
            j++;
        }

        // si esta lista tiene más elementos, los agregamos
        while (actual != null)
        {
            resultado.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }

        // si 'otra' tiene más elementos, los agregamos
        while (j < otra.tamano())
        {
            resultado.agregar(otra.obtener(j));
            j++;
        }

        return resultado;
    }
    

}
