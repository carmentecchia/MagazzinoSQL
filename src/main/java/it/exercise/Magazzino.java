package it.exercise;

import java.sql.*;
import java.util.List;

import static it.exercise.ConnectionDB.getConnection;

public class Magazzino {
    public void vediProdotti() {
        String query = "SELECT * FROM prodotti";
        try (PreparedStatement stmt = getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Nessun prodotto.");
            } else {
                while (rs.next()) {
                    int idProdotti = rs.getInt("id_prodotto");
                    int idMagazzino = rs.getInt("id_mag");
                    int quantita = rs.getInt("quantita");
                    int idCategoria = rs.getInt("id_categoria");
                    String nome = rs.getString("nome");
                    String dataIns = rs.getString("data_ins");
                    String dataMod = rs.getString("data_modifica");
                    int prezzo = rs.getInt("prezzo");

                    System.out.println("id prodotto: " + idProdotti + ", id magazzino: " + idMagazzino + ", quantità: " + quantita +
                            ", id categoria: " + idCategoria + ", nome: " + nome + ", data inserimento: " + dataIns + ", data modifica: " + dataMod + ", prezzo: " + prezzo + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore nel caricamento dei prodotti: " + e.getMessage());
        }
    }

    public void aggiungiProdotto(int idProdotti, int idMagazzino, int quantita, int idCategoria,
                                 String nome, String dataIns, String dataMod, int prezzo) {
        String query = "INSERT INTO prodotti (id_prodotto, id_mag, quantita, id_categoria, nome, data_ins, data_modifica, prezzo)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idProdotti);
            stmt.setInt(2, idMagazzino);
            stmt.setInt(3, quantita);
            stmt.setInt(4, idCategoria);
            stmt.setString(5, nome);
            stmt.setString(6, dataIns);
            stmt.setString(7, dataMod);
            stmt.setInt(8, prezzo);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Aggiunto nuovo prodotto: " + nome);
            } else {
                System.out.println("Errore.");
            }
        } catch (SQLException e) {
            System.out.println("Errore nel caricamento del prodotto: " + e.getMessage());
        }
    }

    public void eliminaProdotto(int idProdotto) {
        String query = "DELETE FROM prodotti WHERE id_prodotti = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idProdotto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Prodotto eliminato");
            } else {
                System.out.println("Nessun prodotto trovato");
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public void aggiungiCliente(int idCliente, String nome, String email, String telefono) {
        String query = "INSERT INTO clienti (id_cliente, nome, email, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, telefono);
            stmt.executeUpdate();
            System.out.println("Cliente aggiunto");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }


    private int nextIdOrdine = 1;

    public Ordini creaOrdine(int idCliente, String dataOrdine, List<String> prodottiOrdinati) {
        int idOrdine = nextIdOrdine++;
        Ordini nuovoOrdine = new Ordini(idOrdine, idCliente, dataOrdine, prodottiOrdinati);

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO ordini (id_cliente, data_ordine, prodotti_ordinati) VALUES (?, ?, ?)")) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, dataOrdine);
            stmt.setString(3, String.join(",", prodottiOrdinati));

            stmt.executeUpdate();

        } catch (SQLException e) {

        }
        return nuovoOrdine;
    }


    public void creaOrdine(int idCliente, List<Integer> idProdotti, List<Integer> quantitaProdotti) throws Exception {
        Connection connection = null;
        PreparedStatement insertOrdineStmt = null;
        PreparedStatement insertOrdineProdottoStmt = null;
        ResultSet generatedKeys = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String insertOrdine = "INSERT INTO ordini (id_cliente) VALUES (?)";
            insertOrdineStmt = connection.prepareStatement(insertOrdine, Statement.RETURN_GENERATED_KEYS);
            insertOrdineStmt.setInt(1, idCliente);
            insertOrdineStmt.executeUpdate();

            generatedKeys = insertOrdineStmt.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("Creazione ordine fallita");
            }
            int idOrdine = generatedKeys.getInt(1);

            String insertOrdineProdottoSql = "INSERT INTO ordini_prodotti (id_ordine, id_prodotto, quantita) VALUES (?, ?, ?)";
            insertOrdineProdottoStmt = connection.prepareStatement(insertOrdineProdottoSql);

            for (int i = 0; i < idProdotti.size(); i++) {
                int idProdotto = idProdotti.get(i);
                int quantita = quantitaProdotti.get(i);

                insertOrdineProdottoStmt.setInt(1, idOrdine);
                insertOrdineProdottoStmt.setInt(2, idProdotto);
                insertOrdineProdottoStmt.setInt(3, quantita);

                insertOrdineProdottoStmt.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback della transazione eseguito.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new Exception("Errore durante la creazione dell'ordine: " + e.getMessage());
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (insertOrdineStmt != null) insertOrdineStmt.close();
            if (insertOrdineProdottoStmt != null) insertOrdineProdottoStmt.close();
            if (connection != null) ConnectionDB.closeConnection(connection);
        }
    }



    public void eliminaOrdine(int idOrdine) throws Exception {
        Connection connection = null;
        PreparedStatement deleteOrdineProdottiStmt = null;
        PreparedStatement deleteOrdineStmt = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String deleteOrdineProdotti = "DELETE FROM ordini_prodotti WHERE id_ordine = ?";
            deleteOrdineProdottiStmt = connection.prepareStatement(deleteOrdineProdotti);
            deleteOrdineProdottiStmt.setInt(1, idOrdine);
            deleteOrdineProdottiStmt.executeUpdate();

            String deleteOrdine = "DELETE FROM ordini WHERE id_ordine = ?";
            deleteOrdineStmt = connection.prepareStatement(deleteOrdine);
            deleteOrdineStmt.setInt(1, idOrdine);
            int rowsAffected = deleteOrdineStmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Ordine non trovato con id: " + idOrdine);
            }

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Rollback della transazione eseguito.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new Exception("Errore durante l'eliminazione dell'ordine: " + e.getMessage());
        } finally {
            if (deleteOrdineProdottiStmt != null) deleteOrdineProdottiStmt.close();
            if (deleteOrdineStmt != null) deleteOrdineStmt.close();
            if (connection != null) ConnectionDB.closeConnection(connection);
        }
    }
}
