package api.tools.image.image_scaling.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

public abstract class ScalingAlgorithm {

	protected final BufferedImage originalImage;
	protected final int newWidth, newHeight;

	protected ScalingAlgorithm(BufferedImage originalImage, int newWidth, int newHeight) {
		this.originalImage = originalImage;
		this.newWidth = newWidth;
		this.newHeight = newHeight;
	}

	public abstract BufferedImage resize();

	protected static int[][] getPixelsFromBufferedImage(BufferedImage image) {
		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
				argb += ((int) pixels[pixel + 1] & 0xff); // blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) pixels[pixel] & 0xff); // blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}

		return result;
	}

	protected static BufferedImage getBufferedImageFromPixels(int[][] pixels) {
		final int width = pixels[0].length;
		final int height = pixels.length;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] outputImagePixelData = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		for (int y=0, pos=0 ; y < height ; y++)
			for (int x=0 ; x < width ; x++, pos++)
				outputImagePixelData[pos] = pixels[y][x] ;

		return image;
	}

}
