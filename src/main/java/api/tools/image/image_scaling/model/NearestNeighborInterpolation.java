package api.tools.image.image_scaling.model;

import java.awt.image.BufferedImage;

public class NearestNeighborInterpolation extends ScalingAlgorithm {

	protected NearestNeighborInterpolation(BufferedImage originalImage, int newWidth, int newHeight) {
		super(originalImage, newWidth, newHeight);
	}

	public BufferedImage resize() {
		int originalWidth = originalImage.getWidth();
		int originalHeight = originalImage.getHeight();

		int[][] originalPixels = getPixelsFromBufferedImage(originalImage);
		int[][] newPixels = new int[newHeight][newWidth];

		double x_ratio = originalWidth/(double)newWidth;
		double y_ratio = originalHeight/(double)newHeight;
		double px, py;
		for (int i = 0; i < newHeight; i++) {
			for (int j = 0; j < newWidth; j++) {
				px = Math.floor(j*x_ratio);
				py = Math.floor(i*y_ratio);
				newPixels[i][j] = originalPixels[(int) py][(int) px];
			}
		}

		BufferedImage newImage = getBufferedImageFromPixels(newPixels);
		return newImage;
	}

}
