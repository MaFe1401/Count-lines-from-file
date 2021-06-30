package count.lines;

import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("app")
public class App extends Application {
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);
        return props;
    }
    public static long countLines(String filePath) {

        Path path = Paths.get(filePath);
  
        long lines = 0;
        try {
  
            // much slower, this task better with sequence access
            //lines = Files.lines(path).parallel().count();
  
            lines = Files.lines(path).count();
  
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return lines;
        
  
    }
    public static void main(String[] args) {
        long lines = countLines("file");
        System.out.println(lines);
    }
}
