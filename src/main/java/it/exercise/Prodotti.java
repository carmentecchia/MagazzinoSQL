package it.exercise;
import java.util.HashSet;
import java.util.Set;

public class Prodotti {


    private int idProdotto;

    private Integer idMagazzino;

    private int quantita;

    private Integer idCategoria;

    private String nome;

    private String dataIns;

    private String dataMod;

    private Double prezzo;

    Set<OrdiniProdotti> prodottiOrdini = new HashSet<>();

    public Prodotti() {
    }

    public Prodotti(int idProdotto, Integer idMagazzino, int quantita, Integer idCategoria, String nome, String dataIns, String dataMod, Double prezzo, Set<OrdiniProdotti> prodottiOrdini) {
        this.idProdotto = idProdotto;
        this.idMagazzino = idMagazzino;
        this.quantita = quantita;
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.dataIns = dataIns;
        this.dataMod = dataMod;
        this.prezzo = prezzo;
        this.prodottiOrdini = prodottiOrdini;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Integer getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(Integer idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataIns() {
        return dataIns;
    }

    public void setDataIns(String dataIns) {
        this.dataIns = dataIns;
    }

    public String getDataMod() {
        return dataMod;
    }

    public void setDataMod(String dataMod) {
        this.dataMod = dataMod;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Set<OrdiniProdotti> getProdottiOrdini() {
        return prodottiOrdini;
    }

    public void setProdottiOrdini(Set<OrdiniProdotti> prodottiOrdini) {
        this.prodottiOrdini = prodottiOrdini;
    }
}
