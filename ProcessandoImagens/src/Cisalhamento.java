import javax.swing.*;

public class Cisalhamento{

      public static void main(String args[]) throws Exception{
      
      	  String caminho = ImagemUtil.selecionarCaminhoDaImagem();
      	  int img[][] = ImagemUtil.abrirImagemTonsDeCinza(caminho);
      	  int largura = ImagemUtil.pegarLargura(img);
      	  int altura = ImagemUtil.pegarAltura(img);
      	
      	  double pmx = largura / 2;
      	  double pmy = altura / 2;
      	  
      	  int imgNova[][] = new int[largura][altura];
      	  
      	  double sh = 0.5;
      	  
      	  for(int x=0; x < largura; x++){
      	      for(int y=0; y < altura; y++){
      	  		  int cor = img[x][y];
				 
      	  		  double xOrigem = x - pmx;
      	  		  double yOrigem = y - pmy;      	  		  
      	  		  
      	  		  int xNovo = (int) (x + y * sh);
      	  		  int yNovo = y;
      	  		  
      	  		  //yNovo = (int) (xNovo * sh + yNovo);
      	  		  
      	  		  if( ImagemUtil.pertenceAoDominio(imgNova, xNovo, yNovo) ){
      	  		       imgNova[xNovo][yNovo] = cor;
      	  		  }
      	      }
      	  }
      	  
      	  ImagemUtil.visualizarImagem(imgNova, "Cisalhamento");
      
      }

	  
	  public static int[][] getCisalhamento(int img[][], double sh, double sy){
      	  int largura = ImagemUtil.pegarLargura(img);
      	  int altura = ImagemUtil.pegarAltura(img);

	  	  int g[][] = new int[largura][altura];
      	  
      	  
      	  
      	  return g;
      	  
	  }

}