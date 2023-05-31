package org.infantaelena.modelo.entidades;





public class Pokemon {
    private static final int MAX_VIDA=300;
    private static final int MAX_ATAQUE=250;
    private static final int MAX_DEFENSA=200;
    private static final int MAX_VELOCIDAD=150;

    public enum tipos {SIN_TIPO,FUEGO,AGUA,ACERO,BICHO,DRAGON,ELECTRICO,FANTASMA,HADA,HIELO,LUCHA,NORMAL,PLANTA,PSIQUICO,ROCA,SINIESTRO,TIERRA,VENENO,VOLADOR}
    private int numero;
    private String nombre;
    private tipos tipo1;
    private tipos tipo2;

    private int ataque;
    private int vida;
    private int velocidad;
    private int defensa;

    public Pokemon() {
    this.numero=0;
    this.nombre="";
    this.tipo1=tipos.SIN_TIPO;
    this.tipo2=tipos.SIN_TIPO;
    this.ataque=0;
    this.vida=0;
    this.defensa=0;
    this.velocidad=0;
    }

    public Pokemon(int numero, String nombre, tipos tipo1, tipos tipo2, int ataque,int vida,int velocidad,int defensa) {
        setNumero(numero);
        setNombre(nombre);
        setTipo1(tipo1);
        setTipo2(tipo2);
        setAtaque(ataque);
        setVida(vida);
        setVelocidad(velocidad);
        setDefensa(defensa);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero<0){
            this.numero=0;
        }else {
            this.numero = numero;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try {
            if(nombre.equals(nombre.trim())){
                this.nombre = nombre.toUpperCase();
            }else{
                this.nombre= nombre.trim().toUpperCase();
            }

        }catch (NullPointerException e){

        }

    }

    public tipos getTipo1() {
        return tipo1;
    }

    public void setTipo1(tipos tipo1) {

        this.tipo1 = tipo1;
    }

    public tipos getTipo2() {
        return tipo2;
    }

    public void setTipo2(tipos tipo2) {
        this.tipo2 = tipo2;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque < 0) {
            this.ataque = 0;
        } else if(ataque>MAX_ATAQUE){
            this.ataque = MAX_ATAQUE;
        }else {
            this.ataque=ataque;
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if(vida>MAX_VIDA){
            this.vida = MAX_VIDA;
        }else {
            this.vida=vida;
        }
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        if (velocidad < 0) {
            this.velocidad = 0;
        } else if(velocidad>MAX_VELOCIDAD){
            this.velocidad = MAX_VELOCIDAD;
        }else {
            this.velocidad=velocidad;
        }
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa < 0) {
            this.defensa = 0;
        } else if(defensa>MAX_DEFENSA){
            this.defensa = MAX_DEFENSA;
        }else {
            this.defensa=defensa;
        }
    }
    @Override
    public String toString() {
        return "Pokemon{" +
                "numero = " + numero +
                "nombre='" + nombre + '\'' +
                ", Tipo1=" + tipo1 +
                ", Tipo2=" + tipo1 +
                ", ataque=" + ataque +
                ", vida=" + vida +
                ", velocidad=" + velocidad +
                ", defensa=" + defensa +
                '}';
    }
}







/*public class PokemonApp {
    private List<Pokemon> pokemonList;

    public PokemonApp() {
        pokemonList = new ArrayList<>();
    }

    public List<Pokemon> getAllPokemonFromAPI() {

    }

    public void createPokemon(String nombre, String tipo1, String tipo2, int estadisticas) {
        // Implementar la lógica para crear un nuevo Pokémon con los datos proporcionados
    }

    public void updatePokemon(Pokemon pokemon, String nuevoNombre, String nuevoTipo1, String nuevoTipo2, int nuevasEstadisticas) {
        // Implementar la lógica para actualizar los datos de un Pokémon existente
    }

    public void deletePokemon(Pokemon pokemon) {
        // Implementar la lógica para eliminar un Pokémon de la lista
    }

    public List<Pokemon> filterPokemonByType(String tipo) {
        // Implementar la lógica para filtrar la lista de Pokémon por tipo
    }

    public void saveChangesToFile(String filePath) {
        // Implementar la lógica para guardar los cambios en un archivo local CSV o base de datos SQL
    }

    public void loadPokemonListFromFile(String filePath) {
        // Implementar la lógica para cargar una lista de Pokémon desde un archivo local CSV o base de datos SQL
    }

    // Resto de métodos y funcionalidades...
}
*/
