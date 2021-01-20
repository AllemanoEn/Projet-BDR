package db;

public class Boisson {

    protected String name;

    protected double prixVente;
    protected double prixAchat;
    protected int quantiteStock;


    protected Boisson() {

    }

    public Boisson(String name, double prixVente, double prixAchat, int quantiteStock) {
        this.name = name;
        this.prixVente = prixVente;
        this.prixAchat = prixAchat;
        this.quantiteStock = quantiteStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }
}
