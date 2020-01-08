package si.rso.notifications.email.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("notifications.mail")
public class EmailConfig {
    
    @ConfigValue("host")
    private String host;
    
    @ConfigValue("port")
    private Integer port;
    
    @ConfigValue("display-name")
    private String displayName;
    
    @ConfigValue("username")
    private String username;
    
    @ConfigValue("password")
    private String password;
    
    @ConfigValue("tmp-dir")
    private String tmpDir;
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public Integer getPort() {
        return port;
    }
    
    public void setPort(Integer port) {
        this.port = port;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTmpDir() {
        return tmpDir;
    }
    
    public void setTmpDir(String tmpDir) {
        this.tmpDir = tmpDir;
    }
}
