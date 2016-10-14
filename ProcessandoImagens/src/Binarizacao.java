public class Binarizacao {
    
     public static void main(String args[]) throws Exception{
          String caminho = ImagemUtil.selecionarCaminhoDaImagem();
          
          int imgEntrada[ ][ ] = ImagemUtil.abrirImagemTonsDeCinza(caminho);
     	  //Integer.parseInt( JOptionPane.showInputDialog(null, "Entre com o valor"));
          int numImg;
          int valor;
     	  numImg = Integer.parseInt(caminho.substring(31,33));
          if(numImg == 01 || numImg == 03){
               valor = 138;
          }else{
              valor = 194;
          }
          
          int imgBinaria[][] = binarizar(imgEntrada, valor); 
                   
          ImagemUtil.visualizarImagem(imgBinaria, "Imagem Binária");
     }
     
     public static int[][] binarizar (int img[][], int valor){
         
        int largura = ImagemUtil.pegarLargura( img );
        int altura = ImagemUtil.pegarAltura( img ) ;
	 	
        int img_binaria[][] = new int[largura][altura];
                 
         for (int y =  0;  y < altura; y++){
                    for (int x =  0;  x < largura; x++){
                        if(img[x][y] < valor){
                             img_binaria[x][y] = 0;
                        }else{
                              img_binaria[x][y] = 255;
                        }
                    }
        }
               
                    
                
		 
		 return img_binaria;
     }
     
}
