package model;

import java.util.Date;
import java.util.Objects;

public class Persona {

    private Integer IDPER;
    private String NOMPER;
    private String APEPATPER;
    private String APEMATPER;
    private String DNIPER;
    private String CELPER;
    private Date FECNACPER = new Date();

    private Ubigeo ubigeo = new Ubigeo();

    private String GENPER;
    private String ESTPER;

    public Persona() {
    }

    public Persona(Integer IDPER, String NOMPER, String APEPATPER, String APEMATPER, String DNIPER, String CELPER,Date FECNACPER, String GENPER, String ESTPER, Ubigeo ubigeo) {
        this.IDPER = IDPER;
        this.NOMPER = NOMPER;
        this.APEPATPER = APEPATPER;
        this.APEMATPER = APEMATPER;
        this.DNIPER = DNIPER;
        this.CELPER = CELPER;
        this.GENPER = GENPER;
        this.ESTPER = ESTPER;
        this.ubigeo = ubigeo;
        this.FECNACPER = FECNACPER;
    }

    
    
    public void clear() {
        IDPER = null;
        NOMPER = null;
        APEPATPER = null;
        APEMATPER = null;
        DNIPER = null;
        CELPER = null;
        FECNACPER = new Date();

        GENPER = null;
        ESTPER = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return Objects.equals(this.DNIPER, other.DNIPER);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.DNIPER);
        return hash;
    }

    public Integer getIDPER() {
        return IDPER;
    }

    public void setIDPER(Integer IDPER) {
        this.IDPER = IDPER;
    }

    public String getNOMPER() {
        return NOMPER;
    }

    public void setNOMPER(String NOMPER) {
        this.NOMPER = NOMPER;
    }

    public String getAPEPATPER() {
        return APEPATPER;
    }

    public void setAPEPATPER(String APEPATPER) {
        this.APEPATPER = APEPATPER.toUpperCase();
    }

    public String getAPEMATPER() {
        return APEMATPER;
    }

    public void setAPEMATPER(String APEMATPER) {
        this.APEMATPER = APEMATPER.toUpperCase();
    }

    public String getDNIPER() {
        return DNIPER;
    }

    public void setDNIPER(String DNIPER) {
        this.DNIPER = DNIPER;
    }

    public Date getFECNACPER() {
        return FECNACPER;
    }

    public void setFECNACPER(Date FECNACPER) {
        this.FECNACPER = FECNACPER;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getCELPER() {
        return CELPER;
    }

    public void setCELPER(String CELPER) {
        this.CELPER = CELPER;
    }

    public String getGENPER() {
        return GENPER;
    }

    public void setGENPER(String GENPER) {
        this.GENPER = GENPER;
    }

    public String getESTPER() {
        return ESTPER;
    }

    public void setESTPER(String ESTPER) {
        this.ESTPER = ESTPER;
    }

}
