package si.rso.notifications.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailNotification {
    
    private String email;
    
    private String subject;
    
    private String htmlContent;
    
    private String textContent;
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getHtmlContent() {
        return htmlContent;
    }
    
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    
    public String getTextContent() {
        return textContent;
    }
    
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
