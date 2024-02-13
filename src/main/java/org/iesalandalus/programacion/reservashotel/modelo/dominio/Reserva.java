package org.iesalandalus.programacion.reservashotel.modelo.dominio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
public class Reserva {
    private static final int MAX_NUMERO_MESES_RESERVA = 6;
    private static final LocalTime MAX_HORAS_POSTERIOR_CHECKOUT = LocalTime.of(12, 0);
    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroPersonas;
    private Regimen regimen;
    private LocalDate checkin = null;
    private LocalTime checkout = null;
    private double precio;

    // Constructor con par�metros que har�n uso de los m�todos de modificaci�n
    public Reserva(Habitacion habitacion, Huesped huesped, LocalDate fechaInicio, LocalDate fechaFin, int numeroPersonas, Regimen regimen) {
        if (habitacion == null) {
            throw new NullPointerException("La habitaci�n no puede ser nula.");
        }
        if (fechaInicio == null) {
            throw new NullPointerException("La fecha de inicio no puede ser nula.");
        }
        if (fechaFin == null) {
            throw new NullPointerException("La fecha de fin no puede ser nula.");
        }
        if (regimen == null) {
            throw new NullPointerException("El r�gimen no puede ser nulo.");
        }

        setHabitacion(habitacion);
        setFechaInicioReserva(fechaInicio);
        setFechaFinReserva(fechaFin);
        setNumeroPersonas(numeroPersonas);
        setRegimen(regimen);
        setCheckin(checkin);
        setCheckout(checkout);
        this.precio = (0.0);
    }

    // M�todos de acceso y modificaci�n
    public Huesped getHuesped() {
        return new Huesped(huesped);
    }

    public void setHuesped(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("El hu�sped no puede ser nulo.");
        }
        this.huesped = new Huesped(huesped);
    }

    public Habitacion getHabitacion() {
        return new Habitacion(habitacion);
    }

    public void setHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("La habitaci�n no puede ser nula.");
        }
        this.habitacion = new Habitacion(habitacion);
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicio;
    }

    public void setFechaInicioReserva(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new NullPointerException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior al d�a actual.");
        }
        if (fechaInicio.isAfter(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA))) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a " + MAX_NUMERO_MESES_RESERVA + " meses.");
        }
        if (fechaFin.equals(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser igual a la de inicio.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFin;
    }

    public void setFechaFinReserva(LocalDate fechaFin) {
        if (fechaFin == null) {
            throw new NullPointerException("La fecha de fin no puede ser nula.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        if (numeroPersonas <= 1) {
            throw new IllegalArgumentException("El n�mero de personas debe ser mayor o igual a 1.");
        }
        if (numeroPersonas > habitacion.getTipoHabitacion().getNumeroMaximoPersonas()) {
            throw new IllegalArgumentException("N�mero de personas excede la capacidad de la habitaci�n.");
        }
        this.numeroPersonas = numeroPersonas;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(Regimen regimen) {
        if (regimen == null) {
            throw new NullPointerException("R�gimen no v�lido");
        }
        this.regimen = regimen;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        if (checkin == null) {
            throw new NullPointerException("El checkin no puede ser nulo.");
        }
        if (checkin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("El checkin no puede ser anterior al inicio de la reserva.");
        }
        this.checkin = checkin;
    }

    public LocalTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalTime checkout) {
        if (checkout == null) {
            throw new NullPointerException("El checkout no puede ser nulo.");
        }
        if (checkout.isBefore(checkin.atStartOfDay().toLocalTime())) {
            throw new IllegalArgumentException("El checkout no puede ser anterior al checkin.");
        }
        if (checkout.isAfter(MAX_HORAS_POSTERIOR_CHECKOUT)) {
            throw new IllegalArgumentException("El checkout debe hacerse como m�ximo a las 12:00 horas del d�a de salida.");
        }
        this.checkout = checkout;
        setPrecio(0.0);
    }

    public double getPrecio() {
        return precio;
    }

    private double setPrecio(double precio) {
        double costoHabitacion = habitacion.getPrecio() * numeroPersonas;
        return costoHabitacion * numeroPersonas;
    }


    // Constructor copia
    public Reserva(Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("No se puede copiar una reserva nula.");
        }
        setHabitacion(reserva.habitacion);
        setFechaInicioReserva(reserva.fechaInicio);
        setFechaFinReserva(reserva.fechaFin);
        setNumeroPersonas(reserva.numeroPersonas);
        setRegimen(reserva.regimen);
        if (reserva.checkin != null) {
            setCheckin(reserva.checkin);
        }
        if (reserva.checkout != null) {
            setCheckout(reserva.checkout);
        }
        setPrecio(reserva.precio);
    }

    // M�todos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return habitacion.equals(reserva.habitacion) && fechaInicio.equals(reserva.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicio);
    }

    // M�todo toString
    @Override
    public String toString() {
        return String.format("Reserva: %s - %s, Personas: %d, R�gimen: %s, Precio: %.2f?",
                habitacion.getIdentificador(), fechaInicio, numeroPersonas, regimen.getTipoRegimen(), precio);
    }
}

