package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.MainApp;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Habitaciones {

        private List<Habitacion> listaHabitaciones;

        public Habitaciones() {
            this.listaHabitaciones = new ArrayList<>();
        }

        public List<Habitacion> get() {
            return new ArrayList<>(listaHabitaciones);
        }

        public void insertar(Habitacion habitacion) {
            if (habitacion == null) {
                throw new IllegalArgumentException("No se puede insertar una habitaci�n nula.");
            }
            if (buscar(habitacion) != null) {
                throw new IllegalArgumentException("No se admiten habitaciones duplicadas.");
            }
            listaHabitaciones.add(habitacion);
        }

        public Habitacion buscar(Habitacion habitacion) {
            if (habitacion == null) {
                throw new IllegalArgumentException("No se puede buscar una habitaci�n nula.");
            }
            for (Habitacion hab : listaHabitaciones) {
                if (hab.equals(habitacion)) {
                    return hab;
                }
            }
            return null;
        }

        public void borrar(Habitacion habitacion) {
            if (habitacion == null) {
                throw new IllegalArgumentException("No se puede borrar una habitaci�n nula.");
            }
            listaHabitaciones.remove(habitacion);
        }

        public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
            if (tipoHabitacion == null) {
                throw new IllegalArgumentException("No se puede obtener habitaciones para un tipo de habitaci�n nulo.");
            }

            List<Habitacion> habitacionesTipoHabitacion = new ArrayList<>();
            for (Habitacion habitacion : listaHabitaciones) {
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
