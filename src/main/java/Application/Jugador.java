package Application;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "jugador")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "estatura", nullable = false)
    private Float estatura;

    @Column(name = "peso", nullable = false)
    private Float peso;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idEquipo")
    private Equipo idEquipo;

    public Jugador() {
        super();
    }

    public Jugador(String nombre, Float estatura, Float peso, int edad,  Equipo idEquipo) {
        this.nombre = nombre;
        this.estatura = estatura;
        this.peso = peso;
        this.edad = edad;
        this.idEquipo = idEquipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estatura=" + estatura +
                ", peso=" + peso +
                ", idEquipo=" + idEquipo +
                '}';
    }
}