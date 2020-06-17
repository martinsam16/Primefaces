package controlador;

import dao.TaskImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import modelo.Task;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import service.TaskService;

@Named(value = "kanbanC")
@SessionScoped
public class KanbanC implements Serializable {

    private Dashboard dashboard;
    private TaskImpl dao;
    private Task task = new Task();
    List<Task> tareas;

    public KanbanC() {
        dao = new TaskImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            dao.crearTabla();
            setearTareasDashboard();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void guardar() throws Exception {
        try {
            dao.registrar(task);
            System.out.println("Registrado: " + task);
            setearTareasDashboard();
            task.clear();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setearTareasDashboard() {
        try {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            Application application = facesContext.getApplication();
            
            dashboard = (Dashboard) application
                    .createComponent(facesContext,
                            "org.primefaces.component.Dashboard",
                            "org.primefaces.component.DashboardRenderer");
            dashboard.setId("dashboard");

            DashboardColumn columnaBacklog = new DefaultDashboardColumn();
            DashboardColumn columnaToDo = new DefaultDashboardColumn();
            DashboardColumn columnaInProgress = new DefaultDashboardColumn();
            DashboardColumn columnaReview = new DefaultDashboardColumn();
            DashboardColumn columnaCompleted = new DefaultDashboardColumn();

            DashboardModel dashboardModel = new DefaultDashboardModel();
            dashboardModel.addColumn(columnaBacklog);
            dashboardModel.addColumn(columnaToDo);
            dashboardModel.addColumn(columnaInProgress);
            dashboardModel.addColumn(columnaReview);
            dashboardModel.addColumn(columnaCompleted);
            dashboard.setModel(dashboardModel);

            AjaxBehavior eventoAjaxCerrar = TaskService.crearEvento(facesContext);

            List<Task> tareas = dao.listar();
            for (Task tarea : tareas) {
                Panel panel = (Panel) application.createComponent(facesContext, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
                panel.setId("idPanel_" + tarea.getId());
                panel.setHeader(tarea.getTitulo());
                panel.setClosable(true);
                panel.setToggleable(true);
                panel.setCollapsed(true);
                panel.addClientBehavior("close", eventoAjaxCerrar);

                dashboard.getChildren().add(panel);
                switch (tarea.getTipo()) {
                    case "0":
                        columnaBacklog.addWidget(panel.getId());
                        break;
                    case "1":
                        columnaToDo.addWidget(panel.getId());
                        break;
                    case "2":
                        columnaInProgress.addWidget(panel.getId());
                        break;
                    case "3":
                        columnaReview.addWidget(panel.getId());
                        break;
                    case "4":
                        columnaCompleted.addWidget(panel.getId());
                        break;
                    default:
                        break;
                }
                HtmlOutputText descripcionPanel = new HtmlOutputText();
                descripcionPanel.setId("idText_" + panel.getId());
                descripcionPanel.setValue(tarea.getDescripcion());

                panel.getChildren().add(descripcionPanel);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eventoReordenarPanel(DashboardReorderEvent evento) throws Exception {
        Task t = new Task();
        t.setId(Integer.valueOf(evento.getWidgetId().split("_")[1]));
        t.setTipo("" + evento.getColumnIndex());
        dao.editar(t);
        System.out.println("Editado a: " + t);
    }

    public void eventoCerrarPanel(CloseEvent evento) throws Exception {
        Task t = new Task();
        t.setId(Integer.valueOf(evento.getComponent().getId().split("_")[1]));
        dao.eliminar(t);
        System.out.println("Eliminado: " + t.getId());
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
