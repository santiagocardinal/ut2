package com.example;
import com.example.ABB.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SistemaGrimorioArchimago s1=new SistemaGrimorioArchimago(null); //creación del sistema
        s1.agregarHechizo(new Hechizo(42, "fireball")); //agregación de los hechizos mencionados en la letra
        s1.agregarHechizo(new Hechizo(17, "ice Lance"));
        s1.agregarHechizo(new Hechizo(58, "thunder"));
        s1.agregarHechizo(new Hechizo(9, "invisibility"));
        s1.agregarHechizo(new Hechizo(31, "levitate"));
        s1.agregarHechizo(new Hechizo(73, "summon"));
        s1.agregarHechizo(new Hechizo(25, "heal"));
        s1.agregarHechizo(new Hechizo(50, "teleport"));
        s1.agregarHechizo(new Hechizo(65, "shield"));
        s1.agregarHechizo(new Hechizo(88, "curse"));
        System.out.println(s1.consultarHechizosProhibidos(s1.obtenerRaiz()));
        System.out.println(s1.generarCanticoSecreto(s1.obtenerRaiz()));
    }
}
