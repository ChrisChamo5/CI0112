import java.util.Scanner;

public class JuegoBatalla {
    // Array para almacenar los robots (máximo 10)
    public static Robot[] listaDeRobots = new Robot[10];
    public static int cantidadRobots = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Menú principal que se repite hasta que el usuario elija salir (opción 4)
        while (opcion != 4) {
            System.out.println("------ MENU ------");
            System.out.println("1. Ingresar Robot");
            System.out.println("2. Iniciar Batalla");
            System.out.println("3. Mostrar Robots");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Permite registrar un nuevo robot si no hay más de 10
                    if (cantidadRobots < 10) {
                        Robot objetoRobot = new Robot();

                        System.out.print("Ingrese el nombre del robot: ");
                        String nombre = scanner.next();
                        objetoRobot.setNombre(nombre);

                        System.out.print("Ingrese los puntos de vida (50-100): ");
                        int puntosVida = scanner.nextInt();
                        // Validamos que esté en el rango permitido
                        while (puntosVida < 50 || puntosVida > 100) {
                            System.out.print("Los puntos de vida deben estar en el rango (50-100): ");
                            puntosVida = scanner.nextInt();
                        }
                        objetoRobot.setPuntosVida(puntosVida);

                        System.out.print("Ingrese el poder de ataque (10-20): ");
                        int ataque = scanner.nextInt();
                        // Validamos que el ataque esté en rango
                        while (ataque < 10 || ataque > 20) {
                            System.out.print("El nivel de ataque debe estar en el rango (10-20): ");
                            ataque = scanner.nextInt();
                        }
                        objetoRobot.setAtaque(ataque);

                        System.out.print("Ingrese el valor de la defensa (Máximo 10): ");
                        int defensa = scanner.nextInt();
                        // Validamos defensa entre 0 y 10
                        while (defensa < 0 || defensa > 10) {
                            System.out.print("El valor de la defensa es Máximo de 10 puntos: ");
                            defensa = scanner.nextInt();
                        }
                        objetoRobot.setDefensa(defensa);

                        // Agregamos el robot al array
                        listaDeRobots[cantidadRobots] = objetoRobot;
                        cantidadRobots++;
                        System.out.println("Robot agregado exitosamente!\n");
                    } else {
                        System.out.println("Capacidad excedida. Solo se permiten 10 Robots.\n");
                    }
                    break;

                case 2:
                    // Solo iniciamos la batalla si hay al menos 2 robots
                    if (cantidadRobots >= 2) {
                        iniciarBatalla();
                    } else {
                        System.out.println("Se necesitan al menos 2 robots para iniciar la batalla.\n");
                    }
                    break;

                case 3:
                    // Mostramos la información de todos los robots actuales
                    if (cantidadRobots == 0) {
                        System.out.println("No hay robots registrados.\n");
                    } else {
                        System.out.println("\n--- LISTA DE ROBOTS ---");
                        for (int i = 0; i < cantidadRobots; i++) {
                            System.out.println(listaDeRobots[i].toString() + "\n");
                        }
                    }
                    break;

                case 4:
                    // Cerramos el programa
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    // Si se digita una opción incorrecta
                    System.out.println("Opción inválida. Intente de nuevo.\n");
            }
        }
    }

    // Método que ejecuta la batalla entre los robots
    public static void iniciarBatalla() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n¡Iniciando batalla!");
        boolean continuar = true;

        // Mientras haya más de un robot y el usuario no detenga
        while (cantidadRobots > 1 && continuar) {
            for (int i = 0; i < cantidadRobots; i++) {
                Robot atacante = listaDeRobots[i];

                // Escogemos un objetivo aleatorio distinto al atacante
                int indiceObjetivo;
                do {
                    indiceObjetivo = (int) (Math.random() * cantidadRobots);
                } while (indiceObjetivo == i); // Aseguramos que no se ataque a sí mismo

                Robot objetivo = listaDeRobots[indiceObjetivo];

                // Ejecutamos el ataque usando el método Atacar()
                atacante.Atacar(objetivo);

                // Si el robot atacado ya no tiene vida, lo eliminamos
                if (!objetivo.EstaVivo()) {
                    System.out.println(objetivo.getNombre() + " ha sido destruido.\n");
                    
                    // Movemos todos los elementos después del eliminado una posición hacia atrás
                    for (int j = indiceObjetivo; j < cantidadRobots - 1; j++) {
                        listaDeRobots[j] = listaDeRobots[j + 1];
                    }
                    listaDeRobots[cantidadRobots - 1] = null;
                    cantidadRobots--;

                    // Si el robot eliminado estaba antes del atacante, ajustamos índice
                    if (indiceObjetivo < i) {
                        i--;
                    }
                }

                // Si ya queda solo uno, detenemos el ciclo
                if (cantidadRobots <= 1) {
                    break;
                }
            }

            System.out.println("----- Fin de ronda -----");

            // Preguntamos al usuario si quiere seguir o parar la simulación
            if (cantidadRobots > 1) {
                System.out.print("¿Desea continuar con la siguiente ronda? (s/n): ");
                String respuesta = scanner.next();
                if (!respuesta.equalsIgnoreCase("s")) {
                    continuar = false;
                    System.out.println("\nSimulación detenida por el usuario.");

                    // Mostramos el estado actual de los robots
                    System.out.println("\n--- ESTADO ACTUAL DE LOS ROBOTS ---");
                    for (int i = 0; i < cantidadRobots; i++) {
                        System.out.println(listaDeRobots[i].toString() + "\n");
                    }
                }
            }
        }

        // Cuando termina la batalla, mostramos el ganador
        System.out.println("\n¡Batalla finalizada!");
        MostrarGanador();
    }

    // Muestra quién ganó la batalla
    public static void MostrarGanador() {
        if (cantidadRobots == 1) {
            Robot ganador = listaDeRobots[0];
            System.out.println("El ganador es: " + ganador.getNombre() +
                    " con " + ganador.getPuntosVida() + " puntos de vida restantes.\n");
        } else {
            System.out.println("No hay ganador, todos los robots fueron eliminados.\n");
        }
    }
}