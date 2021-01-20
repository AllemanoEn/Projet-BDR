package db;

public class Biere extends Boisson {

    private double pourcentage;
    private double contenance;
    private double noteMoyenne;
    private String pays;
    private String region;
    private String brasserie;
    private String typeBiere;

    public Biere(String name, int quantiteStock, double prixVente, double prixAchat, double pourcentage, double contenance, double noteMoyenne, String pays, String region, String brasserie, String typeBiere) {
        super(name, quantiteStock, prixVente, prixAchat);
        this.pourcentage = pourcentage;
        this.contenance = contenance;
        this.noteMoyenne = noteMoyenne;
        this.pays = pays;
        this.region = region;
        this.brasserie = brasserie;
        this.typeBiere = typeBiere;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public double getContenance() {
        return contenance;
    }

    public void setContenance(double contenance) {
        this.contenance = contenance;
    }

    public double getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(String brasserie) {
        this.brasserie = brasserie;
    }

    public String getTypeBiere() {
        return typeBiere;
    }

    public void setTypeBiere(String typeBiere) {
        this.typeBiere = typeBiere;
    }
}
