package org.infantaelena.excepciones;

/**
 * Metodo Clase para lanzar extecion si el pokemon ya existe
 * @author Michel Garcia, Diego Scoott, Angel Taladriz
 * @version 1.0
 */
public class PokemonRepeatedException extends Exception {
    /**
     * Método para generar un texto cuando se da este tipo de error
     * @param mensaje
     */
    public PokemonRepeatedException(String mensaje) {
        super(mensaje);
    }
}
