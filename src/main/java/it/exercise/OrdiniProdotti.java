package it.exercise;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ordini_prodotti")
public class OrdiniProdotti {

    @Id
    @Column(name = "idordini_prodotti")
    private int idOrdiniProdotti;

    @Column(name = "quantita")
    private int quantita;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ordine")
    Prodotti prodotto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prodotto")
    Ordini ordine ;

    public OrdiniProdotti() {
    }

    public OrdiniProdotti(int idOrdiniProdotti, int quantita, Prodotti prodotto, Ordini ordine) {
        this.idOrdiniProdotti = idOrdiniProdotti;
        this.quantita = quantita;
        this.prodotto = prodotto;
        this.ordine = ordine;
    }

    public Prodotti getProdotto() {
        return prodotto;
    }

    public int getIdOrdiniProdotti() {
        return idOrdiniProdotti;
    }

    public void setIdOrdiniProdotti(int idOrdiniProdotti) {
        this.idOrdiniProdotti = idOrdiniProdotti;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setProdotto(Prodotti prodotto) {
        this.prodotto = prodotto;
    }

    public Ordini getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordini ordine) {
        this.ordine = ordine;
    }
}
