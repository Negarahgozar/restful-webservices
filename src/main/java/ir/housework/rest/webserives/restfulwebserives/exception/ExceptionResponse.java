package ir.housework.rest.webserives.restfulwebserives.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String meessage;
    private String details;

    public ExceptionResponse(Date timestamp, String meessage, String details) {
        this.timestamp = timestamp;
        this.meessage = meessage;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMeessage() {
        return meessage;
    }

    public void setMeessage(String meessage) {
        this.meessage = meessage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
