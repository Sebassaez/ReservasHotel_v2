package org.iesalandalus.programacion.reservashotel.modelo.negocio;


import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reservas {

    private List<Reserva> listaReservas;

    public Reservas() {
        this.listaReservas = new ArrayList<>();
    }

    public int getTamano() {
        return listaReservas.size();
    }

    public void insertar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede insertar una reserva nula.");
        }
        if (buscar(reserva) != null) {
            throw new IllegalArgumentException("No se admiten reservas duplicadas.");
        }
        listaReservas.add(reserva);
    }

    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede buscar una reserva nula.");
        }
        for (Reserva res : listaReservas) {
            if (res.equals(reserva)) {
                return res;
            }
        }
        return null;
    }

    public void borrar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede borrar una reserva nula.");
        }
        listaReservas.remove(reserva);
    }

    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede obtener reservas para un huésped nulo.");
        }

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : listaReservas) {
            if (reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(reserva);
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("No se puede obtener reservas para un tipo de habitación nulo.");
        }

        List<Reserva> reservasTipoHabitacion = new ArrayList<>();
        for (Reserva reserva : listaReservas) {
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion.add(reserva);
            }
        }
        return reservasTipoHabitacion;
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede obtener reservas futuras para una habitación nula.");
        }

        List<Reserva> reservasFuturas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        for (Reserva reserva : listaReservas) {
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(hoy)) {
                reservasFuturas.add(reserva);
            }
        }
        return reservasFuturas;
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede realizar check-in para una reserva nula.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de check-in no puede ser nula.");
        }

        reserva.setCheckin(reserva.getCheckin());
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede realizar check-out para una reserva nula.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de check-out no puede ser nula.");
        }
        reserva.setCheckout(reserva.getCheckout());
    }
}
