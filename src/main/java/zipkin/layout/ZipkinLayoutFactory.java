/*
 * Copyright 2018-2023 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package zipkin.layout;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import org.springframework.boot.loader.tools.CustomLoaderLayout;
import org.springframework.boot.loader.tools.Layout;
import org.springframework.boot.loader.tools.LayoutFactory;
import org.springframework.boot.loader.tools.LibraryScope;
import org.springframework.boot.loader.tools.LoaderClassesWriter;

public class ZipkinLayoutFactory implements LayoutFactory, CustomLoaderLayout {
  // Name of the layout and the same has to be specified at the client side where the layout is used
  private String name = "zipkin";

  public ZipkinLayoutFactory() {
  }

  public ZipkinLayoutFactory(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public Layout getLayout(File file) {
    return new Layout() {

      // Since the layout is currently used only for modules, there is no launcher provided at the moment
      @Override public String getLauncherClassName() {
        return null;
      }

      // If the scope of the library is CUSTOM, then the libs will be repackaged to "libs/" directory
      @Override public String getLibraryLocation(String libraryName, LibraryScope scope) {
        return "lib/";
      }

      @Override public String getClassesLocation() {
        return "classes/";
      }

      @Override public String getClasspathIndexFileLocation() {
        return Layout.super.getClasspathIndexFileLocation();
      }

      @Override public String getLayersIndexFileLocation() {
        return Layout.super.getLayersIndexFileLocation();
      }

      // Marking the jar as non executable
      @Override public boolean isExecutable() {
        return false;
      }
    };
  }

  @Override public void writeLoadedClasses(LoaderClassesWriter writer) throws IOException {
    writer.writeEntry(this.name, new ByteArrayInputStream(new byte[0]));
  }
}
