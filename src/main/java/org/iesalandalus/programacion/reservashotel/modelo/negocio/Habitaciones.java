package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.MainApp;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Habitaciones {

    private List<Habitacion> listaHabitaciones;

    public Habitaciones() {
        this.listaHabitaciones = new ArrayList<>();
    }

    public List<Habitacion> get() {
        return copiaProfundaHabitaciones(listaHabitaciones);
    }

    public void insertar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede insertar una habitación nula.");
        }
        if (buscar(habitacion) != null) {
            throw new IllegalArgumentException("No se admiten habitaciones duplicadas.");
        }
        listaHabitaciones.add(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede buscar una habitación nula.");
        }
        for (Iterator<Habitacion> it = listaHabitaciones.iterator(); it.hasNext();) {
            Habitacion hab = it.next();
            if (hab.equals(habitacion)) {
                return hab;
            }
        }
        return null;
    }

    public void borrar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede borrar una habitación nula.");
        }
        Iterator<Habitacion> it = listaHabitaciones.iterator();
        while (it.hasNext()) {
            Habitacion hab = it.next();
            if (hab.equals(habitacion)) {
                it.remove();
                return;
            }
        }
    }

    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("No se puede obtener habitaciones para un tipo de habitación nulo.");
        }

        List<Habitacion> habitacionesTipoHabitacion = new ArrayList<>();
        Iterator<Habitacion> it = listaHabitaciones.iterator();
        while (it.hasNext()) {
            Habitacion habitacion = it.next();
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                habitacionesTipoHabitacion.add(habitacion);
            }
        }
        return habitacionesTipoHabitacion;
    }

    public int getTamano() {
        return listaHabitaciones.size();
    }

    private List<Habitacion> copiaProfundaHabitaciones(List<Habitacion> habitaciones) {
        return new ArrayList<>(habitaciones);
    }
}
