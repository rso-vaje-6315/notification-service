package si.rso.notifications.email;

import java.io.File;

public class EmailAttachmentWrapper {
    
    private File file;
    
    private String filename;
    
    private String mediaType;
    
    private String ext;
    
    public EmailAttachmentWrapper(File file, String filename, String mediaType, String ext) {
        this.file = file;
        this.filename = filename;
        this.mediaType = mediaType;
        this.ext = ext;
    }
    
    public EmailAttachmentWrapper() {
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public String getMediaType() {
        return mediaType;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    public String getExt() {
        return ext;
    }
    
    public void setExt(String ext) {
        this.ext = ext;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
