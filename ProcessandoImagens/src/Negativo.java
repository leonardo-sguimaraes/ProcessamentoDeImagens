public class Negativo{

     public static void main(String args[]) throws Exception{
          String caminho = ImagemUtil.selecionarCaminhoDaImagem();
          
          int f[ ][ ] = ImagemUtil.abrirImagemTonsDeCinza(caminho);

		  int g[][] = Negativo.getNegativo( f );
          
          ImagemUtil.visualizarImagem(f, "entrada");
          ImagemUtil.visualizarImagem(g, "transformada");
     }
     
     public static int[][] getNegativo(int f[][]){
          int largura = ImagemUtil.pegarLargura( f );
          int altura = ImagemUtil.pegarAltura( f ) ;

          int g[ ][ ] = new int[largura][altura];
     
          for(int x = 0; x < largura; x++){
             for(int y=0; y < altura; y++){
                 g[x][y] = 255 - f[x][y]; 
             }
          }
		  return g;
     }

}