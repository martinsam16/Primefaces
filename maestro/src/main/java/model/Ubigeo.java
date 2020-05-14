package model;

public class Ubigeo {

    private String CODUBI;
    private String DEPUBI;
    private String PROVUBI;
    private String DISTUBI;

    public Ubigeo() {
    }

    public Ubigeo(String CODUBI, String DEPUBI, String PROVUBI, String DISTUBI) {
        this.CODUBI = CODUBI;
        this.DEPUBI = DEPUBI;
        this.PROVUBI = PROVUBI;
        this.DISTUBI = DISTUBI;
    }

    public void clear() {
        this.CODUBI = null;
        this.DEPUBI = null;
        this.PROVUBI = null;
        this.DISTUBI = null;
    }

    public String getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(String CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getDEPUBI() {
        return DEPUBI;
    }

    public void setDEPUBI(String DEPUBI) {
        this.DEPUBI = DEPUBI;
    }

    public String getPROVUBI() {
        return PROVUBI;
    }

    public void setPROVUBI(String PROVUBI) {
        this.PROVUBI = PROVUBI;
    }

    public String getDISTUBI() {
        return DISTUBI;
    }

    public void setDISTUBI(String DISTUBI) {
        this.DISTUBI = DISTUBI;
    }

}
