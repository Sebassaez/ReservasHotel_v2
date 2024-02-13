package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar hu�sped"),
    BUSCAR_HUESPED("Buscar hu�sped"),
    BORRAR_HUESPED("Borrar hu�sped"),
    MOSTRAR_HUESPEDES("Mostrar hu�spedes"),
    INSERTAR_HABITACION("Insertar habitaci�n"),
    BUSCAR_HABITACION("Buscar habitaci�n"),
    BORRAR_HABITACION("Borrar habitaci�n"),
    MOSTRAR_HABITACIONES("Mostrar habitaciones"),
    INSERTAR_RESERVA("Insertar reserva"),
    ANULAR_RESERVA("Anular reserva"),
    MOSTRAR_RESERVAS("Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad"),
    REALIZAR_CHECKIN("Realizar checkin"),
    REALIZAR_CHECKOUT("Realizar checkout");

    private final String mensajeAMostrar;

    private Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    public String toString() {
        return ordinal() + " .- " + mensajeAMostrar;
    }
}
