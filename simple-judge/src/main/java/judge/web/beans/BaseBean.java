package judge.web.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

public abstract class BaseBean {

    protected FacesContext facesContext = FacesContext.getCurrentInstance();

    protected ExternalContext externalContext = this.facesContext.getExternalContext();

    protected void redirect(String url) {
        try {
            externalContext.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Runnable addMessageRunnable(String message) {
        return () -> facesContext.addMessage(null, new FacesMessage(message));
    }

    protected void addMessage(String message) {
        addMessageRunnable(message).run();
    }
}
