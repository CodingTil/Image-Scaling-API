package api.tools.image.image_scaling.api.converters;

import api.tools.image.image_scaling.model.ScalingAlgorithms;
import org.springframework.core.convert.converter.Converter;

public class ScalingAlgorithmsStringConverter implements Converter<String, ScalingAlgorithms> {

	@Override
	public ScalingAlgorithms convert(String source) {
		try {
			return ScalingAlgorithms.values()[Integer.parseInt(source)]; // May need to cache this one
		}catch (NumberFormatException e) {
			return ScalingAlgorithms.valueOf(source.toUpperCase());
		}
	}
}
