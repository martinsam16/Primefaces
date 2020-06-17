package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@Named(value = "dashboardC")
@SessionScoped
public class DashboardC implements Serializable {

    private DashboardModel dashboardModel;

    @PostConstruct
    public void init() {
        dashboardModel = new DefaultDashboardModel();
        DashboardColumn columna1 = new DefaultDashboardColumn();
        DashboardColumn columna2 = new DefaultDashboardColumn();
        DashboardColumn columna3 = new DefaultDashboardColumn();

        columna1.addWidget("id_deporte");

        dashboardModel.addColumn(columna1);
        dashboardModel.addColumn(columna2);
        dashboardModel.addColumn(columna3);
    }

    public void eventoReordenarPanel(DashboardReorderEvent evento) throws Exception {
        System.out.println("Moviste " + evento.getWidgetId() + " a la columna: " + evento.getColumnIndex() + " y fila: "+evento.getItemIndex());
    }
    
    public void eventoCerrarPanel(CloseEvent evento) throws Exception {
        System.out.println("Cerrado: "+evento.getComponent().getId());
    }

    public DashboardModel getModel() {
        return dashboardModel;
    }

}
