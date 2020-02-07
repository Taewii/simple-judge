package judge.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@FacesConverter("com.judge.LocalDateTimeConverter")
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
        return LocalDateTime.parse(value, formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
        return value.format(formatter);
    }
}