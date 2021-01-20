package db;

public class Biere extends Boisson {


    private double contenance;
    private double noteMoyenne;
    private String provenance;
    private String region;
    private String brasserie;
    private String typeBiere;

    public Biere(String name, double prixVente, double prixAchat, int quantiteStock, double contenance,
                 double noteMoyenne, String provenance, String region, String brasserie, String typeBiere) {
        super(name, prixVente, prixAchat, quantiteStock);
        this.contenance = contenance;
        this.noteMoyenne = noteMoyenne;
        this.provenance = provenance;
        this.region = region;
        this.brasserie = brasserie;
        this.typeBiere = typeBiere;
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

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
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
