package controller;

import dao.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Ubigeo;

@Named(value = "ubigeoC")
@SessionScoped
public class UbigeoC implements Serializable {

    private UbigeoImpl daoUbigeoImpl;
    private List<Ubigeo> listaUbigeo;

    public UbigeoC() {
        daoUbigeoImpl = new UbigeoImpl();
    }

    @PostConstruct
    public void onInit() {
        listar();
    }

    public void listar() {
        try {
            listaUbigeo = daoUbigeoImpl.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ubigeo> getListaUbigeo() {
        return listaUbigeo;
    }

    public void setListaUbigeo(List<Ubigeo> listaUbigeo) {
        this.listaUbigeo = listaUbigeo;
    }

}
