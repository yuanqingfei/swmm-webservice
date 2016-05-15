/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.jersey;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;


/**
 *<filter>
 <filter-name>Jersey Filter</filter-name>
 <filter-class>org.glassfish.jersey.servlet.ServletContainer</filter-class>
 <init-param>
 <param-name>jersey.config.server.provider.packages</param-name>
 <param-value>org.example</param-value>
 </init-param>
 <init-param>
 <param-name>jersey.config.servlet.filter.staticContentRegex</param-name>
 <param-value>/.*html</param-value>
 </init-param>
 </filter>
 *
 */

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(SwmmEndpoint.class);
		register(MultiPartFeature.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}

}
