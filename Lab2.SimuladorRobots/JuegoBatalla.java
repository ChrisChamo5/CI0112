package Modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class JuegoBatalla {

    // Lista donde se guardan todos los robots que se van creando
    public static ArrayList<Robot> listaDeRobots = new ArrayList<Robot>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Menú principal que se repite hasta que el usuario elija salir (opción 4)
        while (opcion != 4) {
            System.out.println("------ MENÚ ------");
            System.out.println("1. Ingresar Robot");
            System.out.println("2. Iniciar Batalla");
            System.out.println("3. Mostrar Robots");
            System.out.println("4. Salir");
            System.out.println("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Permite registrar un nuevo robot si no hay más de 10
                    if (listaDeRobots.size() < 10) {
                        Robot objetoRobot = new Robot();

                        System.out.println("Ingrese el nombre del personaje: ");
                        String nombre = scanner.next();
                        objetoRobot.nombre = nombre;

                        System.out.println("Ingrese los puntos de vida: (50-100)");
                        int puntosVida = scanner.nextInt();
                        // Validamos que esté en el rango permitido
                        while (puntosVida < 50 || puntosVida > 100) {
                            System.out.println("Los puntos de vida deben estar en el rango: (50-100)");
                            puntosVida = scanner.nextInt();
                        }
                        objetoRobot.puntosVida = puntosVida;

                        System.out.println("Ingrese el poder de ataque: (10-20)");
                        int ataque = scanner.nextInt();
                        // Validamos que el ataque esté en rango
                        while (ataque < 10 || ataque > 20) {
                            System.out.println("El nivel de ataque debe estar en el rango: (10-20)");
                            ataque = scanner.nextInt();
                        }
                        objetoRobot.ataque = ataque;

                        System.out.println("Ingrese el valor de la defensa: (Máximo 10)");
                        int defensa = scanner.nextInt();
                        // Validamos defensa entre 0 y 10
                        while (defensa < 0 || defensa > 10) {
                            System.out.println("El valor de la defensa es Máximo de: 10 puntos");
                            defensa = scanner.nextInt();
                        }
                        objetoRobot.defensa = defensa;

                        // Agregamos el robot a la lista
                        listaDeRobots.add(objetoRobot);
                    } else {
                        System.out.println("Capacidad excedida. Solo se permiten 10 Robots.");
                    }
                    break;

                case 2:
                    // Solo iniciamos la batalla si hay al menos 2 robots
                    if (listaDeRobots.size() >= 2) {
                        iniciarBatalla();
                    } else {
                        System.out.println("Se necesitan al menos 2 robots para iniciar la batalla.");
                    }
                    break;

                case 3:
                    // Mostramos la información de todos los robots actuales
                    for (Robot items : listaDeRobots) {
                        System.out.println(items.toString());
                    }
                    break;

                case 4:
                    // Cerramos el programa
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;

                default:
                    // Si se digita una opción incorrecta
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

            System.out.println(); // Espacio entre cada iteración del menú
        }

        scanner.close();
    }

    // Método que ejecuta la batalla entre los robots
    public static void iniciarBatalla() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Iniciando batalla!");
        boolean continuar = true;

        // Mientras haya más de un robot y el usuario no detenga
        while (listaDeRobots.size() > 1 && continuar) {
            for (int i = 0; i < listaDeRobots.size(); i++) {
                Robot atacante = listaDeRobots.get(i);

                // Escogemos un objetivo aleatorio distinto al atacante
                int indiceObjetivo;
                do {
                    indiceObjetivo = (int) (Math.random() * listaDeRobots.size());
                } while (indiceObjetivo == i); // Aseguramos que no se ataque a sí mismo

                Robot objetivo = listaDeRobots.get(indiceObjetivo);

                // Ejecutamos el ataque usando el método Atacar()
                atacante.Atacar(objetivo);

                // Si el robot atacado ya no tiene vida, lo eliminamos
                if (objetivo.puntosVida <= 0) {
                    System.out.println(objetivo.nombre + " ha sido destruido.");
                    listaDeRobots.remove(indiceObjetivo);

                    // Si el robot eliminado estaba antes del atacante, ajustamos índice
                    if (indiceObjetivo < i) {
                        i--;
                    }
                }

                // Si ya queda solo uno, detenemos el ciclo
                if (listaDeRobots.size() <= 1) {
                    break;
                }
            }

            System.out.println("----- Fin de ronda -----");

            // Preguntamos al usuario si quiere seguir o parar la simulación
            if (listaDeRobots.size() > 1) {
                System.out.print("¿Desea continuar con la siguiente ronda? (s/n): ");
                String respuesta = scanner.next();
                if (!respuesta.equalsIgnoreCase("s")) {
                    continuar = false;
                    System.out.println("Simulación detenida por el usuario.\n");

                    // Mostramos el estado actual de los robots
                    for (Robot items : listaDeRobots) {
                        System.out.println(items.toString());
                    }

                    return;
                }
            }
        }

        // Cuando termina la batalla, mostramos el ganador
        System.out.println("¡Batalla finalizada!");
        MostrarGanador();
    }

    // Muestra quién ganó la batalla
    public static void MostrarGanador() {
        if (listaDeRobots.size() == 1) {
            Robot ganador = listaDeRobots.get(0);
            System.out.println("El ganador es: " + ganador.nombre +
                    " con " + ganador.puntosVida + " puntos de vida restantes.");
        } else {
            System.out.println("No hay ganador, todos los robots fueron eliminados.");
        }
    }
}