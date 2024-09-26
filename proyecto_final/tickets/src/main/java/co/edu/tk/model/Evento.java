package co.edu.tk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private int idEvento;

    @Column(name = "evento")
    private String evento;

    @Column(name = "ip")
    private String ip;

    @Column(name = "acciones")
    private String acciones;

    @Column(name = "fecha") // Asegúrate de que el nombre aquí coincida con el de tu base de datos
    @Temporal(TemporalType.TIMESTAMP) // Utiliza el tipo adecuado según tu columna en la base de datos
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Evento() {
    }

    public Evento(int idEvento, String evento, String ip, String acciones, Date fecha, Usuario usuario) {
        super();
        this.idEvento = idEvento;
        this.evento = evento;
        this.ip = ip;
        this.acciones = acciones;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
