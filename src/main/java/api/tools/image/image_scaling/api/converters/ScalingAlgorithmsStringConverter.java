package api.tools.image.image_scaling.api.converters;

import api.tools.image.image_scaling.model.ScalingAlgorithms;
import org.springframework.core.convert.converter.Converter;

public class ScalingAlgorithmsStringConverter implements Converter<String, ScalingAlgorithms> {

	@Override
	public ScalingAlgorithms convert(String source) {
		return ScalingAlgorithms.valueOf(source.toUpperCase());
	}
}
