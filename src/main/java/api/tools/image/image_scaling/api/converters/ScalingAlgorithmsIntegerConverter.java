package api.tools.image.image_scaling.api.converters;

import api.tools.image.image_scaling.model.ScalingAlgorithms;
import org.springframework.core.convert.converter.Converter;

public class ScalingAlgorithmsIntegerConverter implements Converter<Integer, ScalingAlgorithms> {

	@Override
	public ScalingAlgorithms convert(Integer source) {
		return ScalingAlgorithms.values()[source]; // May need to cache this one
	}
}
