package it.exercise;



import java.util.HashSet;
import java.util.Set;

public class Ordini {

    private int idOrdine;

    private Cliente cliente;

    Set<OrdiniProdotti> ordiniProdotti = new HashSet<>();

    public Ordini() {
    }

    public Ordini(int idOrdine, Cliente cliente, Set<OrdiniProdotti> ordiniProdotti) {
        this.idOrdine = idOrdine;
        this.cliente = cliente;
        this.ordiniProdotti = ordiniProdotti;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<OrdiniProdotti> getOrdiniProdotti() {
        return ordiniProdotti;
    }

    public void setOrdiniProdotti(Set<OrdiniProdotti> ordiniProdotti) {
        this.ordiniProdotti = ordiniProdotti;
    }
}
