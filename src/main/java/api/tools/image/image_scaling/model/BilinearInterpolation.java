package api.tools.image.image_scaling.model;

import java.awt.image.BufferedImage;

public class BilinearInterpolation extends ScalingAlgorithm {

	protected BilinearInterpolation(BufferedImage originalImage, int newWidth, int newHeight) {
		super(originalImage, newWidth, newHeight);
	}

	@Override
	public BufferedImage resize() {
		int originalWidth = originalImage.getWidth();
		int originalHeight = originalImage.getHeight();

		int[][] originalPixels = getPixelsFromBufferedImage(originalImage);
		int[][] newPixels = new int[newHeight][newWidth];

		double x_ratio = (originalWidth - 1)/(double)newWidth;
		double y_ratio = (originalHeight - 1)/(double)newHeight;

		int a, b, c, d, x, y;
		double x_diff, y_diff, blue, red, green;

		for (int i = 0; i < newHeight; i++) {
			for (int j = 0; j < newWidth; j++) {
				x = (int) (x_ratio * j);
				y = (int) (y_ratio * i);
				x_diff = (x_ratio * j) - x;
				y_diff = (y_ratio * i) - y;;
				a = originalPixels[y][x];
				b = originalPixels[y][x + 1];
				c = originalPixels[y + 1][x];
				d = originalPixels[y + 1][x + 1];

				blue = (a&0xff)*(1-x_diff)*(1-y_diff) + (b&0xff)*(x_diff)*(1-y_diff)
						+ (c&0xff)*(y_diff)*(1-x_diff) + (d&0xff)*(x_diff*y_diff);

				green = ((a>>8)&0xff)*(1-x_diff)*(1-y_diff) + ((b>>8)&0xff)*(x_diff)*(1-y_diff)
						+ ((c>>8)&0xff)*(y_diff)*(1-x_diff) + ((d>>8)&0xff)*(x_diff*y_diff);

				red = ((a>>16)&0xff)*(1-x_diff)*(1-y_diff) + ((b>>16)&0xff)*(x_diff)*(1-y_diff)
						+ ((c>>16)&0xff)*(y_diff)*(1-x_diff) + ((d>>16)&0xff)*(x_diff*y_diff);

				newPixels[i][j] = 0xff000000
						| ((((int)red)<<16)&0xff0000)
						| ((((int)green)<<8)&0xff00)
						| ((int)blue) ;
			}
		}

		BufferedImage newImage = getBufferedImageFromPixels(newPixels);
		return newImage;
	}
}
