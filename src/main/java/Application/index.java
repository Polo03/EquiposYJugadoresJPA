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
            System.out.println("5. Salir");
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
                        equipo.setNombre(nombreEquipo);
                        equipo.setEstadio(nombreEstadio);

                        try{
                            // Iniciar la transacción
                            if (!em.getTransaction().isActive()) {
                                em.getTransaction().begin();
                            }
                            // Persistir el objeto, lo que generará un id automáticamente
                            em.persist(equipo);
                            // Confirmar la transacción
                            em.getTransaction().commit();
                            em.close();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }


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

                        // Crear una nueva instancia de Equipo
                        Jugador jugador = new Jugador();
                        jugador.setNombre(nombreJugador);
                        jugador.setEstatura(estatura);
                        jugador.setPeso(peso);
                        jugador.setEdad(edad);

                        try{
                            // Iniciar la transacción
                            if (!em.getTransaction().isActive()) {
                                em.getTransaction().begin();
                            }
                            // Persistir el objeto, lo que generará un id automáticamente
                            em.persist(jugador);
                            // Confirmar la transacción
                            em.getTransaction().commit();
                            em.close();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                        System.out.println("Jugador insertado correctamente");
                    }else
                        System.out.print("No ha seleccionado ninguna de las dos tablas proporcionadas anteriormente.");
                    break;
                case 2:
                    System.out.print("¿En cual de las dos tablas quiere modificar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        try {
                            // Iniciar una transacción
                            em.getTransaction().begin();

                            // Crear una instancia de Equipo con un ID existente
                            Equipo equipoActualizado = new Equipo();
                            equipoActualizado.setId(1); // ID del equipo existente que deseas actualizar
                            equipoActualizado.setNombre("Barcelona");
                            equipoActualizado.setEstadio("Camp Nou");

                            // Usar merge para sincronizar el objeto con la base de datos
                            Equipo equipoGuardado = em.merge(equipoActualizado);

                            // Confirmar la transacción
                            em.getTransaction().commit();

                            System.out.println("Equipo actualizado:");
                            System.out.println(equipoGuardado.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            em.close();
                            emf.close();
                        }
                    } else if (tablaAOperar==2) {
                        try {
                            // Iniciar una transacción
                            em.getTransaction().begin();

                            // Crear una instancia de Equipo con un ID existente
                            Jugador jugadorActualizado = new Jugador();
                            jugadorActualizado.setId(1); // ID del equipo existente que deseas actualizar
                            jugadorActualizado.setNombre("JugadorPrueba");
                            jugadorActualizado.setEstatura(190F);
                            jugadorActualizado.setPeso(80.8F);
                            jugadorActualizado.setEdad(19);
                            jugadorActualizado.setIdEquipo(em.find(Equipo.class, 1));

                            // Usar merge para sincronizar el objeto con la base de datos
                            Jugador jugadorGuardado = em.merge(jugadorActualizado);

                            // Confirmar la transacción
                            em.getTransaction().commit();

                            System.out.println("Equipo actualizado:");
                            System.out.println(jugadorGuardado.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            em.close();
                            emf.close();
                        }
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;

                case 3:
                    System.out.print("¿En cual de las dos tablas quiere eliminar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        try {
                            int idEliminar = 1; // ID del equipo que deseas eliminar

                            // Iniciar la transacción
                            em.getTransaction().begin();

                            // Buscar la entidad primero (debe estar gestionada)
                            Equipo equipo = em.find(Equipo.class, idEliminar);

                            if (equipo != null) {
                                // Eliminar la entidad
                                em.remove(equipo);
                                System.out.println("Equipo eliminado con ID: " + idEliminar);
                            } else {
                                System.out.println("No se encontró un equipo con el ID: " + idEliminar);
                            }

                            // Confirmar la transacción
                            em.getTransaction().commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            em.close();
                            emf.close();
                        }
                    } else if (tablaAOperar==2) {
                        try {
                            int idEliminar = 1; // ID del equipo que deseas eliminar

                            // Iniciar la transacción
                            em.getTransaction().begin();

                            // Buscar la entidad primero (debe estar gestionada)
                            Jugador jugador = em.find(Jugador.class, idEliminar);

                            if (jugador != null) {
                                // Eliminar la entidad
                                em.remove(jugador);
                                System.out.println("Jugador eliminado con ID: " + idEliminar);
                            } else {
                                System.out.println("No se encontró un jugador con el ID: " + idEliminar);
                            }

                            // Confirmar la transacción
                            em.getTransaction().commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            em.close();
                            emf.close();
                        }
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;
                case 4:
                    System.out.print("¿En cual de las dos tablas quiere insertar? (1. Equipos/2. Jugadores) ");
                    tablaAOperar = sc.nextInt();
                    sc.nextLine();
                    if(tablaAOperar==1){
                        try {
                            // Buscar una entidad por su clave primaria
                            Integer idBuscado = 1; // ID del equipo que deseas buscar
                            Equipo equipo = em.find(Equipo.class, idBuscado);

                            if (equipo != null) {
                                System.out.println("Equipo encontrado:");
                                System.out.println(equipo.toString());
                            } else {
                                System.out.println("No se encontró un equipo con el ID: " + idBuscado);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            em.close();
                            emf.close();
                        }
                    } else if (tablaAOperar==2) {
                        try {
                            // Buscar una entidad por su clave primaria
                            Integer idBuscado = 1; // ID del equipo que deseas buscar
                            Jugador jugador = em.find(Jugador.class, idBuscado);

                            if (jugador != null) {
                                System.out.println("Jugador encontrado:");
                                System.out.println(jugador.toString());
                            } else {
                                System.out.println("No se encontró un jugador con el ID: " + idBuscado);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            em.close();
                            emf.close();
                        }
                    }else
                        System.out.println("No ha seleccionado ninguna de las dos tablas");
                    break;
                case 5:
                    System.out.println("Ha salido de la consola");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while(indice!=6);
    }

}
