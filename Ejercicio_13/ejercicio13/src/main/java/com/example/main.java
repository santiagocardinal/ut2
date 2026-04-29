package com.example;

import java.util.List;

import com.example.RegistroNaves.Nave;

public class main {
        public static void main(String[] args) {
        RegistroNaves registro = new RegistroNaves();

        Nave[] naves = {
            new Nave(10,  "Explorador", 0),
            new Nave(20,  "Destructor", 90),
            new Nave(30,  "Médica",     100),
            new Nave(40,  "Explorador", 50),
            new Nave(50,  "Carguero",   20),
            new Nave(60,  "Destructor", 28),
            new Nave(70,  "Explorador", 14),
            new Nave(80,  "Médica",     7),
            new Nave(90,  "Carguero",   23),
            new Nave(100, "Explorador", 26)
        };

        for (Nave n : naves) {
            registro.insertar(n);
        }

        System.out.println("Arbol AVL final (preorden, con altura y balance)");
        registro.imprimirEnPreorden();

        System.out.println();
        List<Integer> codigos = registro.obtenerCodigosExploradoras();
        System.out.println("Códigos de naves exploradoras");
        System.out.println(codigos);

        System.out.println();
        double promedio = registro.promedioCombustibleExplorador();
        System.out.println("Promedio de combustible de exploradoras");
        System.out.println(promedio);
    }

}
