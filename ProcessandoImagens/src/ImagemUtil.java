import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
public class ImagemUtil {

	public static int pegarLargura(int img[][]){
		return img.length;
	}

	public static int pegarAltura(int img[][]){
		return img[0].length;
	}

	public static int pegarLargura(int img[][][]){
		return img.length;
	}

	public static int pegarAltura(int img[][][]){
		return img[0].length;
	}

	public static boolean pertenceAoDominio(int img[][], int i, int j){
		return i >= 0 && i < pegarLargura(img) && j >= 0 && j < pegarAltura(img);
	}

	public static boolean pertenceAoDominio(int img[][][], int i, int j){
		return i >= 0 && i < pegarLargura(img) && j >= 0 && j < pegarAltura(img);
	}



	public static int[][][] abrirImagemColorida (String fileName) throws IOException {
		Image image = ImageIO.read(new File( fileName));
		BufferedImage bi = null;
		if (image instanceof BufferedImage)
			bi = (BufferedImage) image;
		else {
			int w = image.getWidth(null);
			int h = image.getHeight(null);
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
		}

		int width = bi.getWidth();
		int height = bi.getHeight();
		int pixels[][][] = new int[width][height][3];
		int rgb, r, g, b;
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				rgb = bi.getRGB(w, h);
				r = (int) ((rgb & 0x00FF0000) >>> 16); // Red level
				g = (int) ((rgb & 0x0000FF00) >>> 8); // Green level
				b = (int) (rgb & 0x000000FF); // Blue level
				pixels[w][h][0] = r;
				pixels[w][h][1] = g;
				pixels[w][h][2] = b;
			}
		}
		return pixels;
	}

	public static int[][] abrirImagemTonsDeCinza (String fileName) throws IOException {
		Image image = ImageIO.read(new File( fileName));
		BufferedImage bi = null;
		if (image instanceof BufferedImage)
			bi = (BufferedImage) image;
		else {
			int w = image.getWidth(null);
			int h = image.getHeight(null);
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
		}

		int width = bi.getWidth();
		int height = bi.getHeight();
		int pixels[][] = new int[width][height];
		int rgb, r, g, b;
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				rgb = bi.getRGB(w, h);
				r = (int) ((rgb & 0x00FF0000) >>> 16); // Red level
				g = (int) ((rgb & 0x0000FF00) >>> 8); // Green level
				b = (int) (rgb & 0x000000FF); // Blue level
				pixels[w][h] = (int) Math.round(.299 * r + .587 * g + .114 * b); // convertendo para niveis de cinza
			}
		}
		return pixels;
	}

	public static void visualizarImagem(int pixels[][][], String title){
    	int width = pixels.length;
    	int height = pixels[0].length;

        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();
        for(int h=0;h<height;h++){
            for(int w=0;w<width;w++){
                raster.setSample(w,h,0,pixels[w][h][0]);
                raster.setSample(w,h,1,pixels[w][h][1]);
                raster.setSample(w,h,2,pixels[w][h][2]);
            }
        }
        visualizarImagem(bi, title);
    }


    public static void visualizarImagem(int pixels[][], String title){
    	int width = pixels.length;
    	int height = pixels[0].length;

        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();
        for(int h=0;h<height;h++){
            for(int w=0;w<width;w++){
                raster.setSample(w,h,0,pixels[w][h]);
                raster.setSample(w,h,1,pixels[w][h]);
                raster.setSample(w,h,2,pixels[w][h]);
            }
        }
        visualizarImagem(bi, title);
    }


    private static void visualizarImagem(BufferedImage bi, String title){
    	int width = bi.getWidth();
    	int height = bi.getHeight();

        ImageProducer producer = bi.getSource();
        int[] pix = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(producer, 0, 0, width, height, pix, 0, width);
        try {
            if (!grabber.grabPixels())
                throw new IllegalStateException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MemoryImageSource membo = new MemoryImageSource(width,height,pix,0,width);
        Image img =  Toolkit.getDefaultToolkit().createImage(membo);


        final JFrame dialog = new JFrame();
        dialog.setTitle(title);
        dialog.setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(img));
        label.setBorder(BorderFactory.createTitledBorder((width +"x"+ height)));
        panel.add(label, BorderLayout.CENTER);
        panelPrincipal.add(panel,  BorderLayout.CENTER);

        panelPrincipal.setBackground(Color.LIGHT_GRAY);
        dialog.add(panelPrincipal);
        dialog.pack();
        dialog.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
               dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    public static void salvarImagem(int pixels[][], String path) throws IOException{
    	int width = pixels.length;
    	int height = pixels[0].length;

        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();
        for(int h=0;h<height;h++){
            for(int w=0;w<width;w++){
                raster.setSample(w,h,0,pixels[w][h]);
                raster.setSample(w,h,1,pixels[w][h]);
                raster.setSample(w,h,2,pixels[w][h]);
            }
        }
        ImageIO.write(bi,path.substring(path.length() - 3), new File(path));
    }

	public static String selecionarCaminhoDaImagem(){
		JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int res = fc.showOpenDialog(null);
	    return fc.getSelectedFile().getPath();
	}

    public static void salvarImagem(int pixels[][][], String path) throws IOException{
    	int width = pixels.length;
    	int height = pixels[0].length;

        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();
        for(int h=0;h<height;h++){
            for(int w=0;w<width;w++){
                raster.setSample(w,h,0,pixels[w][h][0]);
                raster.setSample(w,h,1,pixels[w][h][1]);
                raster.setSample(w,h,2,pixels[w][h][2]);
            }
        }
        ImageIO.write(bi,path.substring(path.length() - 3), new File(path));
    }
}