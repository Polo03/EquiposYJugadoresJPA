package Application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class index {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EquiposYJugadores");
        EntityManager em = emf.createEntityManager();
        int indice, edad, idEquipo, tablaAOperar=0;
        float estatura, peso=0;
        String nombreEquipo, nombreEstadio, nombreJugador="";
        do{
            System.out.println("1. Insertar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Buscar");
            System.out.println("5. Ver todos");
            System.out.println("6. Salir");
            System.out.print("Ingrese la operación a realizar: ");
            indice = sc.nextInt();
            sc.nextLine();
            switch (indice) {
                case 1:

                    System.out.print("¿En cual de las dos tablas quiere insertar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){

                        System.out.print("Ingrese el nombre del equipo: ");
                        nombreEquipo = sc.nextLine();
                        System.out.print("Ingrese el nombre del estadio: ");
                        nombreEstadio = sc.nextLine();

                        // Crear una nueva instancia de Equipo
                        Equipo equipo = new Equipo();
                        equipo.setEstadio(nombreEstadio);
                        equipo.setNombre(nombreEquipo);

                        // Iniciar la transacción
                        em.getTransaction().begin();
                        // Persistir el objeto, lo que generará un id automáticamente
                        em.persist(equipo);
                        // Confirmar la transacción
                        em.getTransaction().commit();

                        System.out.println("Equipo insertado correctamente");
                    }else if(tablaAOperar==2){
                        System.out.print("Ingrese el nombre del jugador: ");
                        nombreJugador = sc.nextLine();
                        System.out.print("Ingrese la estatura del jugador en cm: ");
                        estatura = sc.nextFloat();
                        System.out.print("Ingrese el peso sin decimales del jugador en kg: ");
                        peso = sc.nextFloat();
                        System.out.print("Ingrese el edad del jugador: ");
                        edad = sc.nextInt();
                        System.out.print("Ingrese el id del equipo al que pertenece: ");
                        idEquipo = sc.nextInt();

                        // Iniciar la transacción
                        em.getTransaction().begin();
                        // Persistir el objeto, lo que generará un id automáticamente
                        em.persist(new Jugador(nombreJugador, estatura, peso, edad, em.find(Equipo.class, idEquipo)));
                        // Confirmar la transacción
                        em.getTransaction().commit();

                        System.out.println("Jugador insertado correctamente");
                    }else
                        System.out.print("No ha seleccionado ninguna de las dos tablas proporcionadas anteriormente.");
                    break;
            }
        }while(indice!=6);
    }

}
