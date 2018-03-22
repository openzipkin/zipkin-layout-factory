/**
 * Copyright 2015-2018 The OpenZipkin Authors
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

import org.springframework.boot.loader.tools.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomLayoutFactory implements LayoutFactory, CustomLoaderLayout {

	private String name = "custom";

	public CustomLayoutFactory() {
	}

	public CustomLayoutFactory(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	private static final Set<LibraryScope> LIB_DESTINATION_SCOPES = new HashSet<>(
			Arrays.asList(
					LibraryScope.CUSTOM)
	);

	@Override
	public Layout getLayout(File file) {
		return new Layout() {
			@Override
			public String getLauncherClassName() {
				return null;
			}

			@Override
			public String getLibraryDestination(String libraryName, LibraryScope scope) {
				if (LIB_DESTINATION_SCOPES.contains(scope)) {
					return "lib/";
				}
				return null;
			}
			@Override
			public String getClassesLocation() {
				return null;
			}

			@Override
			public boolean isExecutable() {
				return false;
			}
		};
	}

	@Override
	public void writeLoadedClasses(LoaderClassesWriter writer) throws IOException {
		writer.writeEntry(this.name, new ByteArrayInputStream( new byte[0]));

	}
}
