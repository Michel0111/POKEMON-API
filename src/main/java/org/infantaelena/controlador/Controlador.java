package org.infantaelena.controlador;


import org.infantaelena.excepciones.PokemonNotFoundException;
import org.infantaelena.excepciones.PokemonRepeatedException;
import org.infantaelena.modelo.DAO.PokemonDAOImp;
import org.infantaelena.modelo.entidades.Pokemon;
import org.infantaelena.vista.Vista;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michel Garcia, Diego Scoott, Angel Taladriz
 * @version 1.0
 */
public class Controlador {
    /**
     * Referencia a la vista
     */
    private Vista vista;
    /**
     * Referencia al modelo
     */
    private PokemonDAOImp modelo;

    /**
     * constructor del controlador, dode se establece lo que hacen los botones
     */
    public Controlador() {


        modelo = new PokemonDAOImp();
        vista = new Vista();
        establecerValoresPorDefecto();
        vista.getButtonGuardar().addActionListener(e -> guardar());
        vista.getButtonbuscar().addActionListener(e -> buscar());
        vista.getButtonBorrar().addActionListener(e -> borrar());
        vista.getButtonbuscarTodos().addActionListener(e -> buscarTodos());
        vista.getButtonActualizar().addActionListener(e -> actualizar());


    }

    /**
     * Metodo que actualiza el pokemon si no existe el pokémon o los datos para actualizarlo, son incorrectos,
     * se mostrará un mensaje de error.
     */
    private void actualizar() {
        Pokemon pokemon = convertirDatosInterfazAPokemon();
        if (pokemon != null) {
            try {
                modelo.actualizar(pokemon);
                vista.mostrarVentana("Pokemon " + pokemon.getNombre() + " actualizado con éxito");
                establecerValoresPorDefecto();
            } catch (PokemonNotFoundException e) {
                vista.mostrarVentanaError("El pokemon no se encuentra");

            } catch (RuntimeException e) {
                vista.mostrarVentanaError("No se ha podido actualizar el pokemon");
            }

        }
    }

    /**
     * metodo que te mustra todos los pokemon que hay en la base de datos
     */
    private void buscarTodos() {
        boolean claseValida = false;
        List<Pokemon> pokemones = new ArrayList<>();
        Pokemon.tipos tipo = (Pokemon.tipos) vista.getTextoTipo1().getSelectedItem();
        if (tipo != Pokemon.tipos.SIN_TIPO) {
            claseValida = true;
            try {
                pokemones = modelo.leerPorTipo1(String.valueOf(tipo));
            } catch (RuntimeException e) {
                vista.mostrarVentanaError("Error al realizar la consulta");
            }
        } else if (tipo.equals(Pokemon.tipos.SIN_TIPO)) {
            claseValida = true;
            pokemones = modelo.leerTodos();
        }
        String texto = "";
        if (claseValida) {
            texto = "Nombre\ttipo\n\n";
        }
        for (Pokemon pokemon : pokemones) {
            texto += pokemon.getNombre() + "\t" + pokemon.getTipo1() + "\n";
        }
        if (pokemones.size() == 0 && claseValida) {
            vista.mostrarVentana("No hay ningún pokémon");
        } else {
            vista.getTextArea1().setText(texto);
        }
    }

    /**
     * metodo que borra un pokemon por el nombre
     */
    private void borrar() {
        try {
            String nombre = vista.getTextFieldNombre().getText().trim().toUpperCase();
            modelo.leerPorNombre(nombre);
            int confirmacion = vista.confirmacion();
            if (confirmacion==0) {
                modelo.eliminarPorNombre(nombre);
                vista.mostrarVentana("Se ha eliminado correctamente");
                establecerValoresPorDefecto();
            }

        } catch (PokemonNotFoundException e) {
            vista.mostrarVentanaError("No se ha encontrado el Pokemon");
        }
    }

    /**
     * Método que coge el campo de nombre, se conecta con la base de datos, y establece cada campo de la interfaz con sus valores correspondientes,
     *si no lo encuentra, saltará un mensaje de error.
     */
    private void buscar() {
        try {
            String nombre = vista.getTextFieldNombre().getText().trim().toUpperCase();
            Pokemon pokemon = modelo.leerPorNombre(nombre);
            vista.getTextFieldNombre().setText(String.valueOf(pokemon.getNumero()));
            vista.getTextFieldNombre().setText(pokemon.getNombre());
            vista.getTextoTipo1().setSelectedItem(pokemon.getTipo1());
            vista.getTextoTipo2().setSelectedItem(pokemon.getTipo2());
            vista.getTextFieldAtaque().setText(String.valueOf(pokemon.getAtaque()));
            vista.getTextFieldVida().setText(String.valueOf(pokemon.getVida()));
            vista.getTextFieldVelocidad().setText(String.valueOf(pokemon.getVelocidad()));
            vista.getTextFieldDefensa().setText(String.valueOf(pokemon.getDefensa()));
        } catch (PokemonNotFoundException e) {
            vista.mostrarVentanaError("No se ha encontrado el Pokemon");
        }
    }

    /**
     * Metodo que guarda el pokemon en la base de datos
     *
     */
    private void guardar() {
        Pokemon pokemon= convertirDatosInterfazAPokemon();
        if (pokemon!=null){
            try {
                modelo.crear(pokemon);

                vista.mostrarVentana("Pokemon " + pokemon.getNombre() + " añadido con éxito");
                establecerValoresPorDefecto();
            } catch (PokemonRepeatedException e) {
                vista.mostrarVentanaError("El pokemon ya existe");
            }
        }
    }

    /**
     * Método para establecer los valores de la interfaz gráfica a unos por defecto.
     */
    private void establecerValoresPorDefecto() {
        vista.getTextFieldNumero().setText("0");
        vista.getTextFieldNombre().setText("");
        vista.getTextoTipo1().setSelectedItem(Pokemon.tipos.SIN_TIPO);
        vista.getTextoTipo2().setSelectedItem(Pokemon.tipos.SIN_TIPO);
        vista.getTextFieldAtaque().setText("0");
        vista.getTextFieldVida().setText("0");
        vista.getTextFieldVelocidad().setText("0");
        vista.getTextFieldDefensa().setText("0");
        vista.getTextArea1().setText("");
    }

    /**
     * Método que verifica que los datos de los campos son correctos, y si los son creará un objeto Pokemon con todos ellos
     * @return
     */
    public Pokemon convertirDatosInterfazAPokemon() {
        String nombre = vista.getTextFieldNombre().getText().trim().toUpperCase();
        Pokemon.tipos tipo1,tipo2;
        tipo1 = (Pokemon.tipos) vista.getTextoTipo1().getSelectedItem();
        tipo2 = (Pokemon.tipos) vista.getTextoTipo2().getSelectedItem();
        int numero = -1;
        int vida = -1;
        int ataque = -1;
        int defensa = -1;
        int velocidad = -1;
        try {
            numero = Integer.parseInt(vista.getTextFieldNumero().getText());
            ataque = Integer.parseInt(vista.getTextFieldAtaque().getText());
            vida = Integer.parseInt(vista.getTextFieldVida().getText());
            velocidad = Integer.parseInt(vista.getTextFieldVelocidad().getText());
            defensa = Integer.parseInt(vista.getTextFieldDefensa().getText());
        }catch (NumberFormatException e){
            System.err.println("datos incorrectos");
        }
        if (numero==0||nombre.isEmpty() || (tipo1 == Pokemon.tipos.SIN_TIPO) || (ataque < 0 || ataque > 250)||(vida < 0 || vida > 300)  || (velocidad < 0 || velocidad > 150)|| (defensa < 0 || defensa > 200) ) {
            vista.mostrarVentanaError("Has introducido mal los datos, no puede haber campos vacíos, tiene que tener una clase y los valores numéricos " +
                    "tienen que estar entre 0 y 200");
        } else {
            Pokemon pokemon = new Pokemon(numero,nombre, tipo1,tipo2, ataque, vida, velocidad, defensa);
            return pokemon;
        }
        return null;
    }

}
