package com.example;

import java.util.ArrayList;

import com.example.ABB.ABB;
import com.example.ABB.TDAElemento;

public class SistemaGrimorioArchimago {
    public SistemaGrimorioArchimago(ABB arbol) {
  //      this.tamaño = arbol.cantidadNodos();
    }

    ABB<Hechizo> arbolBinario=new ABB<>();
    public void agregarHechizo(Hechizo hechizo) {
        arbolBinario.insertar(hechizo);
    }
    public TDAElemento<Hechizo> obtenerRaiz() {
        return((TDAElemento<Hechizo>)arbolBinario.obtenerRaiz());
    }

    ArrayList listaHechizosProhibidos = new ArrayList<>();

    public ArrayList consultarHechizosProhibidos(TDAElemento<Hechizo> nodo) {
        if (nodo==null) {
            return(listaHechizosProhibidos);
        }
        if (nodo.getHijoIzquierdo() != null) {
            this.consultarHechizosProhibidos(nodo.getHijoIzquierdo());
        }
        if (nodo.getDato().getId() % 2 != 0) {
            listaHechizosProhibidos.add(nodo.getDato().getId());
        }
        if (nodo.getHijoDerecho() != null) {
            this.consultarHechizosProhibidos(nodo.getHijoDerecho());
        }
        return (listaHechizosProhibidos);
    }
    int contador=0;
    int tamaño;
    
    public String generarCanticoSecreto(TDAElemento<Hechizo> nodo) {
        if (nodo == null) {
            return "";
        }
        if(contador==0) {
            tamaño=nodo.cantidadNodos();
        }
        String nombresHechizosProhibidos = "";
        contador+=1;
        if (nodo.getHijoIzquierdo() != null) {
            String izquierdo = this.generarCanticoSecreto(nodo.getHijoIzquierdo());
            nombresHechizosProhibidos += izquierdo;

        }
        if (nodo.getDato().getId() % 2 != 0) {
            nombresHechizosProhibidos += nodo.getDato().getNombre()+"-";

        }
        if (nodo.getHijoDerecho() != null) {
            String derecho = this.generarCanticoSecreto(nodo.getHijoDerecho());
            nombresHechizosProhibidos += derecho;
        }
        if((tamaño==contador)) {
            if(nombresHechizosProhibidos.endsWith("-")){
            nombresHechizosProhibidos=nombresHechizosProhibidos.substring(0,nombresHechizosProhibidos.length()-1);
            }
        }
        
        return (nombresHechizosProhibidos);
    }
}
