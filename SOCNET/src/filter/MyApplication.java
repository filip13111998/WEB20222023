package filter;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import controller.AuthenticationController;
import controller.UserController;

public class MyApplication extends Application{
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        // Add your resource classes here
        classes.add(AuthenticationController.class);
        classes.add(UserController.class);
        // Add the CorsFilter class as a provider
        classes.add(CorsFilter.class);
        return classes;
    }
}
