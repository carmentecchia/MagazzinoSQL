package it.exercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Magazzino magazzino = new Magazzino();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nScegli un'operazione:");
            System.out.println("1 - Visualizza i prodotti");
            System.out.println("2 - Aggiungi un prodotto");
            System.out.println("3 - Aggiungi un cliente");
            System.out.println("4 - Elimina un prodotto");
            System.out.println("5 - Effettua un ordine");
            System.out.println("6 - Elimina un ordine");
            System.out.println("7 - Esci");
            System.out.print("Comando: ");
            String comando = scanner.nextLine();


            switch (comando) {
                case "1":
                    magazzino.vediProdotti();
                    break;
                case "2":
                    System.out.print("Inserisci id prodotto: ");
                    int idProdotto = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci id magazzino: ");
                    int idMagazzino = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci la quantità: ");
                    int quantita = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci id categoria: ");
                    int idCategoria = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci il nome del prodotto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Inserisci data inserimento: ");
                    String dataIns = scanner.nextLine();
                    System.out.print("Inserisci data modifica: ");
                    String dataMod = scanner.nextLine();
                    System.out.print("Inserisci prezzo: ");
                    int prezzo = Integer.parseInt(scanner.nextLine());
                    magazzino.aggiungiProdotto(idProdotto, idMagazzino, quantita, idCategoria, nome, dataIns, dataMod, prezzo);
                    break;
                case "3":
                    System.out.print("Inserisci id cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci il nome del cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Inserisci l'email del cliente: ");
                    String emailCliente = scanner.nextLine();
                    System.out.print("Inserisci il telefono del cliente: ");
                    String telefonoCliente = scanner.nextLine();
                    magazzino.aggiungiCliente(idCliente, nomeCliente, emailCliente, telefonoCliente);
                    break;
                case "4":
                    System.out.print("Inserisci l'id del prodotto da eliminare: ");
                    int idProdottoDaEliminare = Integer.parseInt(scanner.nextLine());
                    magazzino.eliminaProdotto(idProdottoDaEliminare);
                    break;
                case "5":
                    System.out.print("Inserisci id cliente per effettuare l'ordine: ");
                    int clienteId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Inserisci la data dell'ordine: ");
                    String dataOrdine = scanner.nextLine();

                    System.out.print("Inserisci i prodotti ordinati: ");
                    String prodottiInput = scanner.nextLine();
                    List<String> prodottiOrdinati = new ArrayList<>();
                    for (String prodotto : prodottiInput.split(",")) {
                        prodottiOrdinati.add(prodotto.trim());
                    }

                    Ordini ordine = magazzino.creaOrdine(clienteId, dataOrdine, prodottiOrdinati);
                    System.out.println("Ordine creato con ID ordine: " + ordine.getIdOrdine());
                    break;

                System.out.print("Inserisci l'ID del cliente: ");
                int idClienteOrdine = Integer.parseInt(scanner.nextLine());

                List<Integer> idProdottiOrdine = new ArrayList<>();
                List<Integer> quantitaProdottiOrdine = new ArrayList<>();

                while (true) {
                    System.out.print("Inserisci l'ID del prodotto da ordinare (o 'fine' per terminare): ");
                    String inputProdotto = scanner.nextLine();

                    if (inputProdotto.equalsIgnoreCase("fine")) {
                        break;
                    }

                    int idProdottoOrdine = Integer.parseInt(inputProdotto);
                    System.out.print("Inserisci la quantità del prodotto con ID " + idProdottoOrdine + ": ");
                    int quantitaProdottoOrdine = Integer.parseInt(scanner.nextLine());

                    idProdottiOrdine.add(idProdottoOrdine);
                    quantitaProdottiOrdine.add(quantitaProdottoOrdine);
                }

                try {
                    magazzino.creaOrdine(idClienteOrdine, idProdottiOrdine, quantitaProdottiOrdine);
                    System.out.println("Ordine creato");
                } catch (Exception e) {
                    System.out.println("Errore durante la creazione dell'ordine: " + e.getMessage());
                }
                break;

                case "6":
                    System.out.print("Inserisci l'ID dell'ordine da eliminare: ");
                    int idOrdineDaEliminare = Integer.parseInt(scanner.nextLine());

                    try {
                        magazzino.eliminaOrdine(idOrdineDaEliminare);
                        System.out.println("Ordine eliminato con successo");
                    } catch (Exception e) {
                        System.out.println("Errore durante l'eliminazione dell'ordine: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Comando non riconosciuto. Riprova.");
                    break;
            }
        }
    }
}