package Modelo;

// Esta clase representa un robot que puede participar en una batalla
public class Robot {

    // Atributos principales de cada robot
    public String nombre;
    public int puntosVida;
    public int ataque;
    public int defensa;

    // Constructor vacío (por si se quiere crear el robot y luego asignar los valores)
    public Robot() {
    }

    // Constructor con todos los atributos 
    public Robot(String nombre, int puntosVida, int ataque, int defensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Método que permite a este robot atacar a otro robot
    public void Atacar(Robot otroRobot) {
        // La defensa se suma temporalmente a los puntos de vida del robot objetivo
        otroRobot.puntosVida = otroRobot.puntosVida + otroRobot.defensa;

        // Luego se le aplica el daño del ataque
        otroRobot.puntosVida -= this.ataque;

        // Después de usar la defensa, se deja en 0 (ya no protege más)
        otroRobot.defensa = 0;

        // Mensaje informativo del ataque
        System.out.println(this.nombre + " ataca a " + otroRobot.nombre +
                " causando " + this.ataque + " de daño. Defensa restante: " + otroRobot.defensa);
    }

    // Este método devuelve true si el robot sigue con vida (vida mayor a 0)
    public boolean EstaVivo() {
        boolean respuesta = this.puntosVida > 0;
        return respuesta;
    }

    // Este método imprime los datos del robot en forma legible
    @Override
    public String toString() {
        return "Información del Robot: " +
               "\nNombre: " + nombre +
               "\nPuntos de Vida: " + puntosVida +
               "\nDefensa: " + defensa;
    }

    // Getters y Setters (para acceder o modificar los valores desde otras clases)

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
}