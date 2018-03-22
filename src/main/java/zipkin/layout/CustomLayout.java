package zipkin.layout;

import org.springframework.boot.loader.tools.CustomLoaderLayout;
import org.springframework.boot.loader.tools.Layouts;
import org.springframework.boot.loader.tools.LibraryScope;
import org.springframework.boot.loader.tools.LoaderClassesWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CustomLayout extends Layouts.Jar implements CustomLoaderLayout {
    private String name;

    public CustomLayout(String name) {
        this.name = name;
    }

    @Override
    public void writeLoadedClasses(LoaderClassesWriter writer) throws IOException {
        writer.writeLoaderClasses("META-INF/loader/spring-boot-loader.jar");
        writer.writeEntry(this.name, new ByteArrayInputStream(new byte[0]));
    }

    @Override
    public String getLibraryDestination(String libraryName, LibraryScope scope) {
        return "lib/";
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public String getLauncherClassName() {
        return "org.springframework.boot.loader.PropertiesLauncher";
    }


}
