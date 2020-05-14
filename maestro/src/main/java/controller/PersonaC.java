package controller;

import dao.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Persona;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private PersonaImpl daoPersonaImpl;
    private Persona persona;
    private Persona personaSeleccionada;
    private List<Persona> listaPersona;
    private List<Persona> listaFiltrada;

    public PersonaC() {
        daoPersonaImpl = new PersonaImpl();
        persona = new Persona();
        personaSeleccionada = new Persona();
        listaFiltrada = new ArrayList<>();
    }

    @PostConstruct
    public void onInit() {
        listar();
    }

    public void registrar() {
        try {
            if (listaPersona.contains(persona) || listaFiltrada.contains(persona)) {
                 mensaje("La persona ya existe", FacesMessage.SEVERITY_ERROR);
                return;
            }
            daoPersonaImpl.registrar(persona);
            persona.clear();
            listar();
            mensaje("Registro exitoso", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editar() {
        try {
            daoPersonaImpl.editar(personaSeleccionada);
            listar();
            mensaje("Modificación exitosa", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() {
        try {
            daoPersonaImpl.eliminar(personaSeleccionada);
            listar();
            mensaje("Eliminación exitosa", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            listaPersona = daoPersonaImpl.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mensaje(String mensaje, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, mensaje, null));
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public List<Persona> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Persona> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

}
