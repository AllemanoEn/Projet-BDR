package db;

public class Boisson {

    protected String name;
    protected int quantiteStock;
    protected double prixVente;
    protected double prixAchat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
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

    public Boisson(String name, int quantiteStock, double prixVente, double prixAchat) {
        this.name = name;
        this.quantiteStock = quantiteStock;
        this.prixVente = prixVente;
        this.prixAchat = prixAchat;
    }

    @Override
    public String toString() {
        return name;
    }
}
