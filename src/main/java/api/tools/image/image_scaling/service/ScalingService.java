package api.tools.image.image_scaling.service;

import api.tools.image.image_scaling.model.ImageData;
import api.tools.image.image_scaling.model.ScalingAlgorithm;
import api.tools.image.image_scaling.model.ScalingAlgorithmFactory;
import api.tools.image.image_scaling.model.ScalingAlgorithms;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class ScalingService {

	public ImageData resize(ImageData imageData, ScalingAlgorithms algo, int newWidth, int newHeight) {
		try {
			BufferedImage oldImage = imageData.asBufferedImage();
			ScalingAlgorithm algorithm = ScalingAlgorithmFactory.create(algo, oldImage, newWidth, newHeight);
			BufferedImage newImage = algorithm.resize();
			return ImageData.fromBufferedImage(newImage);
		} catch (IOException e) {
			new RuntimeException(e);
			return null;
		}
	}

}
