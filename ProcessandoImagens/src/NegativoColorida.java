public class NegativoColorida{

     public static void main(String args[]) throws Exception{
          String caminho = ImagemUtil.selecionarCaminhoDaImagem();
          
          int f[ ][ ] [] = ImagemUtil.abrirImagemColorida(caminho);

		  int g[][][] = NegativoColorida.getNegativoColorida( f );
          
          ImagemUtil.visualizarImagem(f, "entrada");
          ImagemUtil.visualizarImagem(g, "transformada");
     }

	 public static int[][][] getNegativoColorida(int f[][][]){
          int largura = ImagemUtil.pegarLargura( f );
          int altura = ImagemUtil.pegarAltura( f ) ;

          int g[ ][ ][ ] = new int[largura][altura][3];
     
          for(int x = 0; x < largura; x++){
             for(int y=0; y < altura; y++){
                 for(int banda=0; banda < 3; banda++){             
                     g[x][y][banda] = 255 - f[x][y][banda];
                 }
             }
          }
          return g;
	 }

}