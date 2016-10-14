public class Rotacao{

     public static void main(String args[]) throws Exception{
          String caminho = ImagemUtil.selecionarCaminhoDaImagem();
          
          int f[ ][ ] = ImagemUtil.abrirImagemTonsDeCinza(caminho);
		  
		  int graus = 45;
		  
          int g[][] = Rotacao.getRotacao(f, graus);
          
          ImagemUtil.visualizarImagem(f, "entrada");
          ImagemUtil.visualizarImagem(g, "transformada");
     }
     
     public static int[][] getRotacao(int f[][], double anguloGraus){
		  int largura = ImagemUtil.pegarLargura( f );
          int altura = ImagemUtil.pegarAltura( f ) ;

          int g[ ][ ] = new int[largura][altura];
     	  double angulo = Math.toRadians(anguloGraus);
		  int pmx = largura / 2;
		  int pmy = altura / 2;
          for(int x = 0; x < largura; x++){
             for(int y=0; y < altura; y++){
                   int cor = f[x][y];
                   int xt = x - pmx;
                   int yt = y - pmy;
                   
                   double xRot = xt * Math.cos(angulo) - yt * Math.sin(angulo);
                   double yRot = xt * Math.sin(angulo) + yt * Math.cos(angulo);
                   
                   int xNovo = (int)(xRot + pmx);
                   int yNovo = (int)(yRot + pmy);
                   
                   if(ImagemUtil.pertenceAoDominio(g, xNovo, yNovo)){
                        g[xNovo][yNovo] = cor;
                   }

             }
          }
          return g;
     
     }

}