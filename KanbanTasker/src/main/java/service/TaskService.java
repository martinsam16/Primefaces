package service;

import dao.TaskImpl;
import java.util.List;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import modelo.Task;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DefaultDashboardColumn;

public class TaskService {

    public static AjaxBehavior crearEvento(FacesContext facesContext) {
        AjaxBehavior ajax = new AjaxBehavior();
        Class<?>[] parametrosRecibirMetodo = {CloseEvent.class};

        MethodExpression methodExpression = facesContext.getApplication()
                .getExpressionFactory().
                createMethodExpression(facesContext.getELContext(),
                        "#{kanbanC.eventoCerrarPanel}",
                        void.class,
                        parametrosRecibirMetodo);
        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(methodExpression, methodExpression));

        return ajax;

    }

}
