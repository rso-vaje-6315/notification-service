package si.rso.notifications.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailNotification {
    
    private String email;
    
    private String subject;
    
    private String htmlContent;
    
    private EmailAttachment attachment;
    
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
    
    public EmailAttachment getAttachment() {
        return attachment;
    }
    
    public void setAttachment(EmailAttachment attachment) {
        this.attachment = attachment;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmailAttachment {
        private String url;
        private String name;
    
        public String getUrl() {
            return url;
        }
    
        public void setUrl(String url) {
            this.url = url;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
}
