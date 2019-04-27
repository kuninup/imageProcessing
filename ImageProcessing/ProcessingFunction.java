package ImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ProcessingFunction {

	// 灰度化参数
	public final static double KR = 0.3;
	public static final double KG = 0.59;
	public final static double KB = 0.11;

	// 读取图像
	public static BufferedImage imageRead(String imgfile) {
		File file = new File(imgfile);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bi;
	}

	// 将图像转化成rgb二维数组
	public static Color[][] img2color(BufferedImage bi) {

		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		Color[][] allcolor = new Color[width - minx][height - miny];
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
				allcolor[i - minx][j - miny] = new Color(pixel);
			}
		}
		return allcolor;
	}

	// 将rgb二维数组转化成图像
	public static BufferedImage color2img(Color[][] allcolor) {
		BufferedImage bi = new BufferedImage(allcolor.length, allcolor[0].length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				bi.setRGB(i, j, allcolor[i][j].getRGB());
			}
		}
		return bi;
	}

	// 灰度化
	public static void graying(Color[][] allcolor) {
		// System.out.println(allcolor.length+" "+allcolor[0].length);
		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				Color color = allcolor[i][j];
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				int grey = (int) (r * KR + g * KG + b * KB);
				allcolor[i][j] = new Color(grey, grey, grey);
				// System.out.println(r+" "+g+" "+b+" "+grey);
			}
		}

	}

	// 反相
	public static void reverseColor(Color[][] allcolor) {
		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				Color color = allcolor[i][j];
				int r = 255 - color.getRed();
				int g = 255 - color.getGreen();
				int b = 255 - color.getBlue();

				allcolor[i][j] = new Color(r, g, b);

			}
		}

	}

	// 高斯模糊
	public static void gaussBlur(Color[][] allcolor, int radius, float sigma) {


		// generate the Gauss Matrix
		float[][] gaussMatrix = new float[radius * 2 + 1][radius * 2 + 1];
		float gaussSum = 0f;
		for (int i = 0, x = -radius; x <= radius; ++x, ++i) {
			for (int j = 0, y = -radius; y <= radius; ++y, ++j) {
				float g = (float) ((1 / (2 * Math.PI * sigma * sigma))
						* Math.pow(Math.E, ((-(x * x + y * y)) / ((2 * sigma) * (2 * sigma)))));

				gaussMatrix[i][j] = g;
				// System.out.print(g+" ");
				gaussSum += g;
			}
			// System.out.println();
		}
		// System.out.println("gaussSum=" + gaussSum);
		for (int i = 0, lengthx = gaussMatrix.length; i < lengthx; ++i) {
			for (int j = 0, lengthy = gaussMatrix[0].length; j < lengthy; ++j) {
				gaussMatrix[i][j] = gaussMatrix[i][j] / gaussSum;
				// System.out.print(gaussMatrix[i][j]+" ");
			}

			// System.out.println(i);
		}

		ProcessingFunction.convolution(gaussMatrix, allcolor);


	}

	// 素描
	public static Color[][] toSketch(Color[][] allcolor) {
		Color[][] allcolor1 = new Color[allcolor.length][allcolor[0].length],
				allcolor2 = new Color[allcolor.length][allcolor[0].length];
		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				allcolor1[i][j] = allcolor[i][j];
				allcolor2[i][j] = allcolor[i][j];
			}
		}
		ProcessingFunction.graying(allcolor1);
		
		ProcessingFunction.graying(allcolor2);
		ProcessingFunction.reverseColor(allcolor2);
		ProcessingFunction.gaussBlur(allcolor2, 6, 5.5f);

		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				Color gra = allcolor1[i][j], gua = allcolor2[i][j];
				int r = Math.min(gra.getRed() + (gra.getRed() * gua.getRed()) / (255 - gua.getRed()), 255);
				int g = Math.min(gra.getGreen() + (gra.getGreen() * gua.getGreen()) / (255 - gua.getGreen()), 255);
				int b = Math.min(gra.getBlue() + (gra.getBlue() * gua.getBlue()) / (255 - gua.getBlue()), 255);

				allcolor[i][j] = new Color(r, g, b);
			}
		}

		return allcolor;
	}

//	// 哈哈镜
//	public static void haHaMirror(Color[][] allcolor,int midx,int midy) {
//		float k = 1000f;// 缩放参数
//		int Radius = 190;// 处理范围
////		int cX = allcolor.length * 6/ 10, cY = allcolor[0].length / 4;// 处理中心
//		int cX = midx, cY = midy;// 处理中心
//		// System.out.println("w="+allcolor.length+" h="+allcolor[0].length);
//		Color[][] allcolor1 = new Color[allcolor.length][allcolor[0].length];
//		for (int i = 0; i < allcolor.length; i++) {
//			for (int j = 0; j < allcolor[i].length; j++) {
//				allcolor1[i][j] = allcolor[i][j];
//			}
//		}
//		float theta = 0;// 处理点在坐标系中的幅角
//
//		for (int i = 0; i < allcolor.length; i++) {
//			for (int j = 0; j < allcolor[i].length; j++) {
//				float x = i - cX, y = j - cY;
//				float distance = (float) Math.sqrt(x * x + y * y);
//				if (distance <= Radius) {
//					float R1 = (float) Math.sqrt(distance * k);
//					theta = (float) Math.atan2(y, x);
////					if(x<2)
////					System.out.println("x="+x+" y="+y+" theta="+theta);
//
//					int x1 = (int) (R1 * Math.cos(theta));
//					int y1 = (int) (R1 * Math.sin(theta));
//					int i1 = x1 + cX, j1 = y1 + cY;
//					i1 = i1 >= allcolor.length ? allcolor.length - 1 : i1;
//					i1 = i1 < 0 ? 0 : i1;
//					j1 = j1 < allcolor[0].length ? j1 : (allcolor[0].length - 1);
//					j1 = j1 < 0 ? 0 : j1;
//					// System.out.println("("+x+","+y+")"+"("+x1+","+y1+")");
//					// System.out.println("("+i1+","+j1+")");
//					allcolor[i][j] = allcolor1[i1][j1];
//				}
//			}
//		}
//	}
	
	
	

	// 哈哈镜
	public static Color[][] haHaMirror(Color[][] allcolor,int cX,int cY) {
		if(cX<0||cX>allcolor.length||cY<0||cY>allcolor[0].length) return null;
		float k = 50f;// 缩放参数
		int Radius = 200;// 处理范围
		// System.out.println("w="+allcolor.length+" h="+allcolor[0].length);
		Color[][] allcolor1 = new Color[allcolor.length][allcolor[0].length];
		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				allcolor1[i][j] = allcolor[i][j];
			}
		}
		float theta = 0;// 处理点在坐标系中的幅角

		for (int i = Math.max(0,cX-Radius); i <= Math.min(cX+Radius,allcolor.length-1); i++) {
			for (int j = Math.max(0,cY-Radius); j <=Math.min(cY+Radius,allcolor[0].length-1); j++) {
				float x = i - cX, y = j - cY;
				float distance = (float) Math.sqrt(x * x + y * y);
				if (distance <= Radius) {
					float R1 = (float) Math.sqrt(distance * k);
					theta = (float) Math.atan2(y, x);
//					if(x<2)
//					System.out.println("x="+x+" y="+y+" theta="+theta);

					int x1 = (int) (R1 * Math.cos(theta));
					int y1 = (int) (R1 * Math.sin(theta));
					int i1 = x1 + cX, j1 = y1 + cY;
					i1 = i1 >= allcolor.length ? allcolor.length - 1 : i1;
					i1 = i1 < 0 ? 0 : i1;
					j1 = j1 < allcolor[0].length ? j1 : (allcolor[0].length - 1);
					j1 = j1 < 0 ? 0 : j1;
					// System.out.println("("+x+","+y+")"+"("+x1+","+y1+")");
					// System.out.println("("+i1+","+j1+")");
					//System.out.println("i="+i+" j="+j);
					allcolor[i][j] = 
							allcolor1[i1][j1];
				}
			}
		}
		return allcolor;
	}

	// 卷积操作
	public static void convolution(float[][] operator, Color[][] allcolor) {

		Color[][] allcolor1 = new Color[allcolor.length][allcolor[0].length];
		for (int i = 0; i < allcolor.length; i++) {
			for (int j = 0; j < allcolor[i].length; j++) {
				allcolor1[i][j] = allcolor[i][j];
			}
		}

		int height = allcolor1[0].length, width = allcolor1.length;
		int radius = operator.length / 2;
		//System.out.println(radius);
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {

				float r = 0, g = 0, b = 0;
				float operatorSum = 0;
				for (int i = -radius; i <= radius; ++i) {
					for (int j = -radius; j <= radius; ++j) {
						int k = x + i, h = y + j;
						if (k >= 0 && k < width && h >= 0 && h < height) {

							Color color = allcolor1[k][h];
							int cr = color.getRed();
							int cg = color.getGreen();
							int cb = color.getBlue();

							r += cr * operator[i + radius][j + radius];
							g += cg * operator[i + radius][j + radius];
							b += cb * operator[i + radius][j + radius];

							operatorSum += operator[i + radius][j + radius];
							
						}
					}

				}
				int cr = (int) (r / operatorSum);
				int cg = (int) (g / operatorSum);
				int cb = (int) (b / operatorSum);
				//System.out.println("r="+r+" cr="+cr+" operatorSum="+operatorSum);
				allcolor[x][y] = new Color(cr, cg, cb);
			}
		}
	}
}
