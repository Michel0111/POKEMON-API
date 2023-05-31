package org.infantaelena.modelo.DAO;

import org.infantaelena.excepciones.PokemonNotFoundException;
import org.infantaelena.excepciones.PokemonRepeatedException;
import org.infantaelena.modelo.entidades.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase que implementa los métodos de acceso a datos de la clase Pokemon
 * @author Michel Garcia, Diego Scoott, Angel Taladriz
 * @version 1.0
 */
public class PokemonDAOImp implements PokemonDAO {
    /**
     * Atributo conexión para la base de datos
     */
    private Connection connection;

    /**
     * El constructor que crea la base de datos si no existe y la tabla si no existe.
     */
    public PokemonDAOImp() {
        this("Pokemon");
    }

    public PokemonDAOImp(String nombreBD) {

        try {
            connection = DriverManager.getConnection("jdbc:h2:./data/" + nombreBD, "sa", "");

        } catch (SQLException e) {
            System.err.println("Error al hacer operaciones con la base de datos");
        }

        crearTablaPokemon();


        try (Statement statement = connection.createStatement()) {
            String selectAll = "SELECT * FROM Pokemons;";

            ResultSet resultSet = statement.executeQuery(selectAll);

        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta");
        }

    }
    /**
     * Método para la creación de la tabla donde se van a guardar los pokemons
     */
    private void crearTablaPokemon() {
        try(Statement sentencia= connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Pokemons (\n"
                    + " Numero INTEGER PRIMARY KEY,\n"
                    + " Nombre VARCHAR(30) NOT NULL UNIQUE,\n"
                    + " Tipo1 VARCHAR(25) NOT NULL,\n"
                    + " Tipo2 VARCHAR(25) NOT NULL,\n"
                    + " Ataque INTEGER,\n"
                    + " Vida INTEGER,\n"
                    + " Velocidad INTEGER,\n"
                    + " Defensa INTEGER\n"
                    + ");";
            sentencia.execute(createTableSQL);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Método para añadir los pokemons a la base de datos
     * Se le pasa un pokemon con todos los datos y después se añade a la base de datos
     * Puede dar una excepción si el pokemon ya existe
     *
     * @param pokemon
     * @throws PokemonRepeatedException
     */
    @Override
    public void crear(Pokemon pokemon) throws PokemonRepeatedException {


        try {
            String query = "INSERT into  Pokemons (Numero,Nombre,Tipo1,Tipo2,Ataque,Vida,Velocidad,Defensa) " +
                    "VALUES (%d,'%s','%s','%s',%d,%d,%d,%d);";
            Statement sentencia= connection.createStatement();
            sentencia.executeUpdate(String.format(query,pokemon.getNumero(), pokemon.getNombre(), pokemon.getTipo1(),pokemon.getTipo2(),
                    pokemon.getAtaque(), pokemon.getVida(),  pokemon.getVelocidad(), pokemon.getDefensa()));

        }catch (SQLException e) {
            throw new PokemonRepeatedException("El pokemon ya existe");
        }

    }
    /**
     * Método para leer un pokemon por nombre y devolver los datos del pokemon
     *
     * @param nombre
     * @return un pokemon entero con todos sus datos
     * @throws PokemonNotFoundException
     */
    @Override
    public Pokemon leerPorNombre(String nombre) throws PokemonNotFoundException {
        int contador = 0;

        Pokemon pokemon = null;
        try (Statement sentencia= connection.createStatement();){
            String query = "SELECT * FROM Pokemons WHERE Nombre = "+nombre.toUpperCase()+";";
            ResultSet resultSet = sentencia.executeQuery(query);

            while (resultSet.next()) {
                pokemon= new Pokemon();
                pokemon.setNumero(resultSet.getInt("Numero"));
                pokemon.setNombre(resultSet.getString("Nombre"));
                pokemon.setTipo1(Pokemon.tipos.valueOf(resultSet.getString("Tipo1")));
                pokemon.setTipo2(Pokemon.tipos.valueOf(resultSet.getString("Tipo2")));
                pokemon.setAtaque(resultSet.getInt("Ataque"));
                pokemon.setVida(resultSet.getInt("Vida"));
                pokemon.setVelocidad(resultSet.getInt("Velocidad"));
                pokemon.setDefensa(resultSet.getInt("Defensa"));
                contador++;
            }
        }catch (SQLException e) {
            throw new PokemonNotFoundException("Error al realizar la consulta: " + e.getMessage());
        }if(contador==0){
            throw new PokemonNotFoundException("El Pokemon que quieres no ya existe");
        }
        return pokemon;
    }

    /**
     * Método para leer todos los pokemons
     *
     * @return todos los pokemons de la base de datos
     */
    @Override
    public List<Pokemon> leerTodos() {

        List<Pokemon> listado = new ArrayList<Pokemon>();


        try (Statement sentencia= connection.createStatement();){
            String consulta = "SELECT * FROM Pokemons;";
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()){
                int numero;
                String nombre;
                Pokemon.tipos tipo1;
                Pokemon.tipos tipo2;
                int ataque;
                int vida;
                int velocidad;
                int defensa;
                numero= resultado.getInt("Numero");
                nombre=resultado.getString("Nombre");
                tipo1= Pokemon.tipos.valueOf(resultado.getString("Tipo1"));
                tipo2= Pokemon.tipos.valueOf(resultado.getString("Tipo2"));
                ataque= resultado.getInt("Ataque");
                vida=resultado.getInt("Vida");
                velocidad= resultado.getInt("Velocidad");
                defensa=resultado.getInt("Defensa");
                listado.add(new Pokemon(numero,nombre,tipo1,tipo2,ataque,vida,velocidad,defensa));
                 }
        } catch (SQLException e) {
            System.err.println("Error en la consulta");;
        }

        return listado;

    }
    /**
     * Método para actualizar un pokemon
     *
     * @param pokemon
     * @throws PokemonNotFoundException
     */
    @Override
    public void actualizar(Pokemon pokemon) throws PokemonNotFoundException {
        int contador=0;

        try (Statement sentencia= connection.createStatement();){
            String select = "SELECT * FROM Pokemons where Nombre='" + pokemon.getNombre() + "';";

            ResultSet resultSet = sentencia.executeQuery(select);
            while (resultSet.next()) {
                contador++;
            }
            if (contador == 0) {
                throw new PokemonNotFoundException("Error al realizar la consulta");
            } else {
                String query = "UPDATE Pokemons \n" +
                        "set Nombre=" + pokemon.getNombre() + ",\n" +
                        "Tipo1=" + pokemon.getNombre() + ",\n" +
                        "Tipo2=" + pokemon.getNombre() + ",\n" +
                        "Ataque=" + pokemon.getNombre() + ",\n" +
                        "Vida=" + pokemon.getNombre() + ",\n" +
                        "Velocidad=" + pokemon.getNombre() + ",\n" +
                        "Defensa=" + pokemon.getNombre() + "\n" +
                        "WHERE Numero= " + pokemon.getNumero() + ";";
                sentencia.executeUpdate(query);

            }
        }catch (SQLException E){
            throw new RuntimeException();
        }


    }
    /**
     * Método para mostrar todos los pokemons de una clase pasada como parámetro
     *
     * @param tipo
     * @return todos los pokemons de una clase en concreto
     * @throws RuntimeException
     */
    public List<Pokemon> leerPorTipo1(String tipo) throws RuntimeException{
        List<Pokemon> listado = new ArrayList<Pokemon>();
        try (Statement sentencia= connection.createStatement();){
            String consulta = "SELECT * FROM Pokemons where Tipo1="+tipo+";";
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()){
                int numero;
                String nombre;
                Pokemon.tipos tipo1;
                Pokemon.tipos tipo2;
                int ataque;
                int vida;
                int velocidad;
                int defensa;
                numero= resultado.getInt("Numero");
                nombre=resultado.getString("Nombre");
                tipo1= Pokemon.tipos.valueOf(resultado.getString("Tipo1"));
                tipo2= Pokemon.tipos.valueOf(resultado.getString("Tipo2"));
                ataque= resultado.getInt("Ataque");
                vida=resultado.getInt("Vida");
                velocidad= resultado.getInt("Velocidad");
                defensa=resultado.getInt("Defensa");
                listado.add(new Pokemon(numero,nombre,tipo1,tipo2,ataque,vida,velocidad,defensa));
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta");;
        }

        return listado;
    }
    /**
     * Método para eliminar un pokemon
     *
     * @param nombre
     * @throws PokemonNotFoundException
     */
    @Override
    public void eliminarPorNombre(String nombre) throws PokemonNotFoundException {
        int contador=0;
        try (Statement sentencia= connection.createStatement();){
            String select = "SELECT * FROM Pokemons where Nombre='" + nombre.toUpperCase() + "';";

            ResultSet resultSet = sentencia.executeQuery(select);
            while (resultSet.next()) {
                contador++;
            }
            if (contador == 0) {
                throw new PokemonNotFoundException("Error al realizar la consulta");
            } else {
                String query = "DELETE FROM Pokemons WHERE Nombre =" + nombre.toUpperCase() + ";";
                sentencia.executeUpdate(query);

            }
        }catch (SQLException E){
            throw new PokemonNotFoundException("No se ha encontrado el pokémon");
        }
    }
}
