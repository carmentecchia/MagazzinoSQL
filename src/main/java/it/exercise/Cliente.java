package it.exercise;



import java.util.Set;


public class Cliente {

    private int idCliente;

    private Set<Ordini> ordini;

    private String nome;

    private String email;

    private String telefono;

    public Cliente() {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int id) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
