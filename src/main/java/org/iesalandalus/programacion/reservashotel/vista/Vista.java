package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.reservashotel.controlador.Controlador;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Vista {



    public static final int CAPACIDAD = 100;
    private Huesped[] huespedes;
    private Habitacion[] habitaciones;
    private Reserva[] reservas;
    private int numHuespedes;
    private int numHabitaciones;
    private int numReservas;
    private Controlador controlador;




    public void setControlador(Controlador controlador) {
        if (controlador != null) {
            this.controlador = controlador;
        } else {
            throw new IllegalArgumentException("El controlador no puede ser nulo.");
        }
    }

    public void comenzar() {
        Opcion opcionElegida;
        do {
            Consola.mostrarMenu();
            opcionElegida = Consola.elegirOpcion();
            ejecutarOpcion(opcionElegida);
        } while (opcionElegida != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Gracias por utilizar nuestra aplicaci�n de reservas de hotel.");
    }

    public void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                listarReservasHuesped();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                mostrarReservas();
                break;
            case REALIZAR_CHECKIN:
                realizarCheckin();
                break;
            case REALIZAR_CHECKOUT:
                realizarCheckout();
                break;
            case SALIR:
                System.out.println("Saliendo de la aplicaci�n. �Hasta luego!");
                System.exit(0);
                break;
            default:
                System.out.println("Opci�n no v�lida.");
        }
    }

    private void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();

            if (getNumElementosNoNulos(huespedes) < CAPACIDAD) {
                for (int i = 0; i < huespedes.length; i++) {
                    if (huespedes[i] == null) {
                        huespedes[i] = nuevoHuesped;
                        numHuespedes++;
                        System.out.println("Hu�sped insertado correctamente.");
                        return;
                    }
                }
            } else {
                System.out.println("No hay capacidad para m�s hu�spedes.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void buscarHuesped() {
        System.out.print("Introduce el DNI del hu�sped a buscar: ");
        String dniBuscar = Entrada.cadena();

        Huesped huespedEncontrado = null;
        for (Huesped huesped : huespedes) {
            if (huesped != null && huesped.getDni().equals(dniBuscar)) {
                huespedEncontrado = huesped;
                break;
            }
        }

        if (huespedEncontrado != null) {
            System.out.println("Hu�sped encontrado:\n" + huespedEncontrado);
        } else {
            System.out.println("No se encontr� un hu�sped con ese DNI.");
        }
    }


    private void borrarHuesped() {
        System.out.print("Introduce el DNI del hu�sped a borrar: ");
        String dniBorrar = Entrada.cadena();
        boolean huespedBorrado = false;

        for (int i = 0; i < numHuespedes; i++) {
            if (huespedes[i] != null && huespedes[i].getDni().equals(dniBorrar)) {
                huespedes[i] = null;
                huespedBorrado = true;
                numHuespedes--;
                break;
            }
        }

        if (huespedBorrado) {
            System.out.println("Hu�sped borrado correctamente.");
        } else {
            System.out.println("No se encontr� un hu�sped con ese DNI.");
        }
    }


    private void mostrarHuespedes() {
        if (getNumElementosNoNulos(huespedes) > 0) {
            Arrays.sort(huespedes, Comparator.comparing(Huesped::getNombre));// Ordenar los hu�spedes alfab�ticamente por nombre

            System.out.println("Listado de Hu�spedes:");
            for (Huesped huesped : huespedes) {
                if (huesped != null) {
                    System.out.println(huesped);
                }
            }
        } else {
            System.out.println("No hay hu�spedes registrados.");
        }
    }


    private void insertarHabitacion() {
        Habitacion nuevaHabitacion = Consola.leerHabitacion();

        if (getNumElementosNoNulos(habitaciones) < CAPACIDAD) {
            habitaciones[numHabitaciones] = nuevaHabitacion;
            numHabitaciones++;
            System.out.println("Habitaci�n insertada correctamente.");
        } else {
            System.out.println("No hay capacidad para m�s habitaciones.");
        }
    }

    private void buscarHabitacion() {
        System.out.print("Introduce el n�mero de puerta de la habitaci�n a buscar: ");
        int puertaBuscar = Entrada.entero();
        System.out.print("Introduce el n�mero de planta de la habitaci�n a buscar: ");
        int plantaBuscar = Entrada.entero();
        Habitacion habitacionEncontrada = null;

        for (int i = 0; i < numHabitaciones; i++) {
            if (habitaciones[i] != null && habitaciones[i].getPuerta() == puertaBuscar
                    && habitaciones[i].getPlanta() == plantaBuscar) {
                habitacionEncontrada = habitaciones[i];
                break;
            }
        }

        if (habitacionEncontrada != null) {
            System.out.println("Habitaci�n encontrada:\n" + habitacionEncontrada);
        } else {
            System.out.println("No se encontr� una habitaci�n con ese n�mero de puerta y planta.");
        }
    }

    private void borrarHabitacion() {
        System.out.print("Introduce el n�mero de puerta de la habitaci�n a borrar: ");
        int puertaBorrar = Entrada.entero();
        System.out.print("Introduce el n�mero de planta de la habitaci�n a borrar: ");
        int plantaBorrar = Entrada.entero();
        boolean habitacionBorrada = false;

        for (int i = 0; i < numHabitaciones; i++) {
            if (habitaciones[i] != null && habitaciones[i].getPuerta() == puertaBorrar
                    && habitaciones[i].getPlanta() == plantaBorrar) {
                habitaciones[i] = null;
                habitacionBorrada = true;
                numHabitaciones--;
                break;
            }
        }

        if (habitacionBorrada) {
            System.out.println("Habitaci�n borrada correctamente.");
        } else {
            System.out.println("No se encontr� una habitaci�n con ese n�mero de puerta y planta.");
        }
    }

    private void mostrarHabitaciones() {
        if (getNumElementosNoNulos(habitaciones) > 0) {
            Arrays.sort(habitaciones, Comparator.comparing(Habitacion::getPlanta)
                    .thenComparing(Habitacion::getPuerta));// Ordenar las habitaciones primero por planta y luego por puerta

            System.out.println("Listado de Habitaciones:");
            for (Habitacion habitacion : habitaciones) {
                if (habitacion != null) {
                    System.out.println(habitacion);
                }
            }
        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }


    private void insertarReserva() {
        Reserva nuevaReserva = Consola.leerReserva();

        // Verificar disponibilidad antes de insertar
        if (getNumElementosNoNulos(reservas) < CAPACIDAD && consultarDisponibilidad(nuevaReserva)) {
            reservas[numReservas] = nuevaReserva;
            numReservas++;
            System.out.println("Reserva insertada correctamente.");
        } else {
            System.out.println("No hay capacidad para m�s reservas o no hay disponibilidad en el per�odo indicado.");
        }
    }



    private void listarReservasHuesped() {
        System.out.print("Introduce el DNI del hu�sped para listar sus reservas: ");
        String dniBuscar = Entrada.cadena();

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dniBuscar)) {
                reservasHuesped.add(reserva);
            }
        }

        // Ordenar las reservas por fecha de inicio en orden descendente
        reservasHuesped.sort(Comparator.comparing(Reserva::getFechaInicioReserva).reversed()
                .thenComparing(Comparator.comparingInt(r -> r.getHabitacion().getPlanta()))
                .thenComparing(Comparator.comparingInt(r -> r.getHabitacion().getPuerta())));

        // Imprimir las reservas
        for (Reserva reserva : reservasHuesped) {
            System.out.println(reserva);
        }
    }

    private void listarReservasHabitacion() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();

        List<Reserva> reservasHabitacion = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasHabitacion.add(reserva);
            }
        }

        reservasHabitacion.sort(Comparator.comparing(Reserva::getFechaInicioReserva).reversed()// Ordenar las reservas por fecha de inicio en orden descendente
                .thenComparing(Comparator.comparing(r -> r.getHuesped().getNombre())));

        for (Reserva reserva : reservasHabitacion) {
            System.out.println(reserva);
        }
    }


    private Reserva[] getReservasAnulables(String dni) {
        List<Reserva> anulables = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni) &&
                    reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                anulables.add(reserva);
            }
        }

        return anulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        System.out.print("Introduce el DNI del hu�sped para anular una reserva: ");
        String dniBuscar = Entrada.cadena();
        Reserva[] reservasAnulables = getReservasAnulables(dniBuscar);

        if (reservas.length == 0) {
            System.out.println("No hay reservas anulables para ese hu�sped.");
        } else if (reservasAnulables.length == 1) {
            System.out.print("�Desea anular la siguiente reserva? (S/N)\n" + reservas[0]);
            char opcion = Entrada.caracter();
            if (opcion == 'S' || opcion == 's') {
                anularReserva();
                System.out.println("Reserva anulada con �xito.");
            } else {
                System.out.println("Anulaci�n de reserva cancelada.");
            }
        } else {
            System.out.println("Seleccione la reserva a anular (1-" + reservasAnulables.length + "):");
            for (int i = 0; i < reservasAnulables.length; i++) {
                System.out.println((i + 1) + ". " + reservasAnulables[i]);
            }
            int opcion = Entrada.entero();
            if (opcion >= 1 && opcion <= reservasAnulables.length) {
                anularReserva();
                System.out.println("Reserva anulada con �xito.");
            } else {
                System.out.println("Opci�n no v�lida. Anulaci�n de reserva cancelada.");
            }
        }
    }

    public void mostrarReservas() {
        System.out.println("Lista de reservas:");

        // Verificar si hay reservas almacenadas
        if (getNumElementosNoNulos(reservas) == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            Arrays.sort(reservas, Comparator.comparing(Reserva::getFechaInicioReserva).reversed()// Ordenar las reservas por fecha de inicio en orden descendente
                    .thenComparing((r1, r2) -> {
                        // Comparar por n�mero de planta y puerta en orden ascendente
                        int comparePlanta = Integer.compare(r1.getHabitacion().getPlanta(), r2.getHabitacion().getPlanta());
                        if (comparePlanta == 0) {
                            return Integer.compare(r1.getHabitacion().getPuerta(), r2.getHabitacion().getPuerta());
                        }
                        return comparePlanta;
                    }));

            // Mostrar todas las reservas almacenadas
            for (Reserva reserva : reservas) {
                if (reserva != null) {
                    System.out.println(reserva);
                }
            }
        }
    }


    private int getNumElementosNoNulos(Object[] array) {
        int count = 0;
        for (Object element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    private boolean consultarDisponibilidad(Reserva nuevaReserva) {
        Habitacion habitacion = nuevaReserva.getHabitacion();
        LocalDate fechaInicio = nuevaReserva.getFechaInicioReserva();
        LocalDate fechaFin = nuevaReserva.getFechaFinReserva();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHabitacion().equals(habitacion)) {
                LocalDate inicioReserva = reserva.getFechaInicioReserva();
                LocalDate finReserva = reserva.getFechaFinReserva();
                if (!(fechaInicio.isAfter(finReserva) || fechaFin.isBefore(inicioReserva))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void realizarCheckin() {
        System.out.print("Introduce el DNI del hu�sped para realizar el check-in: ");
        String dni = Entrada.cadena();

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni)) {
                reservasHuesped.add(reserva);
            }
        }

        if (reservasHuesped.isEmpty()) {
            System.out.println("El hu�sped no tiene reservas para realizar el check-in.");
            return;
        }

        System.out.println("Reservas del hu�sped:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }

        System.out.print("Selecciona el n�mero de reserva para realizar el check-in: ");
        int opcion = Entrada.entero();
        if (opcion < 1 || opcion > reservasHuesped.size()) {
            System.out.println("Opci�n no v�lida.");
            return;
        }

        Reserva reservaSeleccionada = reservasHuesped.get(opcion - 1);

        LocalDateTime fechaActual = LocalDateTime.now();
        if (!reservaSeleccionada.getFechaInicioReserva().equals(fechaActual.toLocalDate())) {
            System.out.println("La reserva no es para el d�a actual, no se puede realizar el check-in.");
            return;
        }

        reservaSeleccionada.getCheckin();
        controlador.realizarCheckin(reservaSeleccionada, fechaActual);
        System.out.println("Check-in realizado con �xito para la reserva:\n" + reservaSeleccionada);
    }



    private void realizarCheckout() {
        System.out.print("Introduce el DNI del hu�sped para realizar el check-out: ");
        String dni = Entrada.cadena();

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni)) {
                reservasHuesped.add(reserva);
            }
        }

        if (reservasHuesped.isEmpty()) {
            System.out.println("El hu�sped no tiene reservas para realizar el check-out.");
            return;
        }

        System.out.println("Reservas del hu�sped:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }

        System.out.print("Selecciona el n�mero de reserva para realizar el check-out: ");
        int opcion = Entrada.entero();
        if (opcion < 1 || opcion > reservasHuesped.size()) {
            System.out.println("Opci�n no v�lida.");
            return;
        }

        Reserva reservaSeleccionada = reservasHuesped.get(opcion - 1);

        LocalDateTime fechaActual = LocalDateTime.now();
        if (reservaSeleccionada.getFechaFinReserva().isBefore(ChronoLocalDate.from(fechaActual.toLocalDate().atStartOfDay()))) {
            System.out.println("La reserva ya ha pasado su fecha de finalizaci�n.");
            return;
        }

        reservaSeleccionada.getCheckout();
        controlador.realizarCheckout(reservaSeleccionada, fechaActual);
        System.out.println("Check-out realizado con �xito para la reserva:\n" + reservaSeleccionada);
    }

}
