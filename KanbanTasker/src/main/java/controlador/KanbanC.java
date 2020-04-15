package controlador;

import dao.TaskImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import modelo.Task;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
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

    public KanbanC() {
        dao = new TaskImpl();
    }

    @PostConstruct
    public void onInit() {
        try {
            dao.crear();
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void guardar() throws Exception {
        try {
            dao.registrar(task);
            task.clear();
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {

            FacesContext fc = FacesContext.getCurrentInstance();
            Application application = fc.getApplication();

            dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
            dashboard.setId("dashboard");

            DashboardModel model = new DefaultDashboardModel();
            DashboardColumn backlog = new DefaultDashboardColumn();
            DashboardColumn toDo = new DefaultDashboardColumn();
            DashboardColumn inProgress = new DefaultDashboardColumn();
            DashboardColumn review = new DefaultDashboardColumn();
            DashboardColumn completed = new DefaultDashboardColumn();
            model.addColumn(backlog);
            model.addColumn(toDo);
            model.addColumn(inProgress);
            model.addColumn(review);
            model.addColumn(completed);
            dashboard.setModel(model);

            List<Task> tareas = dao.listar();

            for (Task tarea : tareas) {
                Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
                panel.setId("id_" + String.valueOf(tarea.getId()));
                panel.setHeader(tarea.getTitulo());
//                panel.setClosable(true);
                panel.setToggleable(true);

                dashboard.getChildren().add(panel);
                switch (tarea.getTipo()) {
                    case "0":
                        backlog.addWidget(panel.getId());
                        break;
                    case "1":
                        toDo.addWidget(panel.getId());
                        break;
                    case "2":
                        inProgress.addWidget(panel.getId());
                        break;
                    case "3":
                        review.addWidget(panel.getId());
                        break;
                    case "4":
                        completed.addWidget(panel.getId());
                        break;
                    default:
                        break;
                }
                HtmlOutputText text = new HtmlOutputText();
                text.setId("t" + panel.getId());
                text.setValue(tarea.getDescripcion());

                panel.getChildren().add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleReorder(DashboardReorderEvent event) throws Exception {
        Task t = new Task();
        t.setId(Integer.valueOf(event.getWidgetId().split("_")[1]));
        t.setTipo("" + event.getColumnIndex());
        dao.editar(t);
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
