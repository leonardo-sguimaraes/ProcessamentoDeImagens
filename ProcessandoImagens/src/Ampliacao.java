import javax.swing.*;


public class Ampliacao{

     public static void main(String args[]) throws Exception{
          String caminho = ImagemUtil.selecionarCaminhoDaImagem();
          
          int f[ ][ ] = ImagemUtil.abrirImagemTonsDeCinza(caminho);
     	  double sx = 1.5;
     	  double sy = 1.5;
          
          int g[][] = Ampliacao.getImagemAmpliada(f, sx, sy); 
                    
          ImagemUtil.visualizarImagem(f, "entrada");
          ImagemUtil.visualizarImagem(g, "transformada");
     }
     
     public static int[][] getImagemAmpliada(int f[ ][ ], double sx, double sy){
         int largura = ImagemUtil.pegarLargura( f );
         int altura = ImagemUtil.pegarAltura( f ) ;

	     int g[ ][ ] = new int[(int) (largura * sx)][(int) (altura * sy)];

         for(int x = 0; x < largura; x++){
             for(int y=0; y < altura; y++){
                int xNovo = (int)(x * sx);
                int yNovo = (int)(y * sy);
                if(ImagemUtil.pertenceAoDominio(g, xNovo, yNovo))
                   g[xNovo][yNovo] = f[x][y];
             }
         }
		 return g;
     
     }

}