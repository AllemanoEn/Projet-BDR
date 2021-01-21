package db;

public class Commentaire {

    private String userName;
    private String comment;
    private double note;

    public Commentaire(String userName, String comment, double note) {
        this.userName = userName;
        this.comment = comment;
        this.note = note;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
