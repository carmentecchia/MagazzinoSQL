package it.exercise;

public class Categoria {


    public int idCategoria;

    public String tipo;

    public Categoria(int idCategoria, String tipo) {
        this.idCategoria = idCategoria;
        this.tipo = tipo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
