package api.tools.image.image_scaling.api;

import api.tools.image.image_scaling.api.converters.ScalingAlgorithmsIntegerConverter;
import api.tools.image.image_scaling.api.converters.ScalingAlgorithmsStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ScalingConfiguration implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new ScalingAlgorithmsIntegerConverter());
		registry.addConverter(new ScalingAlgorithmsStringConverter());
	}

}
