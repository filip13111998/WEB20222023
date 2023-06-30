package filter;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApp extends ResourceConfig{
	public MyApp() {
        // Register your resources or controllers here
        register(CorsFilter.class);
    }
}
