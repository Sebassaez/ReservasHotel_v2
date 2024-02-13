package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento", 0),
    ALOJAMIENTO_DESAYUNO("Alojamiento y Desayuno", 15),
    MEDIA_PENSION("Media Pensi�n", 30),
    PENSION_COMPLETA("Pensi�n Completa", 50);

    private final String tipoRegimen;
    private final double incrementoPrecio;

    private Regimen(String tipoRegimen, double incrementoPrecio) {
        this.tipoRegimen = tipoRegimen;
        this.incrementoPrecio = incrementoPrecio;
    }

    public String getTipoRegimen() {
        return tipoRegimen;
    }

    @Override
    public String toString() {
        return ordinal() + 1 + ".-" + tipoRegimen;
    }

}
