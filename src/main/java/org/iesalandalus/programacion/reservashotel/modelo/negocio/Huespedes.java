package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;


import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Huespedes {

    private List<Huesped> listaHuespedes;

    public Huespedes() {
        this.listaHuespedes = new ArrayList<>();
    }

    public List<Huesped> get() {
        return new ArrayList<>(listaHuespedes);
    }

    public int getTamano() {
        return listaHuespedes.size();
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede insertar un hu�sped nulo.");
        }
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("El hu�sped ya existe en la colecci�n.");
        }
        listaHuespedes.add(huesped);
    }

    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede buscar un hu�sped nulo.");
        }
        for (Iterator<Huesped> it = listaHuespedes.iterator(); it.hasNext(); ) {
            Huesped h = it.next();
            if (h.equals(huesped)) {
                return h;
            }
        }
        return null;
    }

    public void borrar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede borrar un hu�sped nulo.");
        }
        Iterator<Huesped> it = listaHuespedes.iterator();
        while (it.hasNext()) {
            Huesped h = it.next();
            if (h.equals(huesped)) {
                it.remove();
                return;
            }
        }
    }
}


