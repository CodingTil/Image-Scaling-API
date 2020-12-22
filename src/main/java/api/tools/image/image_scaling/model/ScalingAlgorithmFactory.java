package api.tools.image.image_scaling.model;

import java.awt.image.BufferedImage;

public class ScalingAlgorithmFactory {

	public static ScalingAlgorithm create(ScalingAlgorithms algorithm, BufferedImage originalImage, int newWidth, int newHeight) {
		ScalingAlgorithm result = switch(algorithm) {
			case NEAREST_NEIGHBOR_INTERPOLATION -> new NearestNeighborInterpolation(originalImage, newWidth, newHeight);
			default -> null;
		};
		return result;
	}

}
