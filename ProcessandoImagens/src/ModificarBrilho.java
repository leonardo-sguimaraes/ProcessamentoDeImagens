import javax.swing.*;

public class ModificarBrilho{

     public static void main(String args[]) throws Exception{
          String caminho = ImagemUtil.selecionarCaminhoDaImagem();
          
          int f[ ][ ] = ImagemUtil.abrirImagemTonsDeCinza(caminho);
     	  
     	  int valor = Integer.parseInt( JOptionPane.showInputDialog(null, "Entre com o valor"));
     	  
          int g[][] = ModificarBrilho.getModificarBrilho(f, valor); 
                    
          ImagemUtil.visualizarImagem(f, "entrada");
          ImagemUtil.visualizarImagem(g, "transformada");
     }
     
     public static int[][] getModificarBrilho(int f[][], int valor){
         int largura = ImagemUtil.pegarLargura( f );
         int altura = ImagemUtil.pegarAltura( f ) ;
	 int soma;	
		 int g[][] = new int[largura][altura];
		 
                 
               
                   for (int y =  0;  y < altura; y++){
                    for (int x =  0;  x < largura; x++){
                         soma =  f[x][y] + (valor);
                         
                         if (soma >  255){
                             g[x][y] = 255;
                         }else if(soma < 0 ){
                             g[x][y] = 0;
                         }else{
                             g[x][y] = soma;
                         }
                     }
                   }
               
                    
                
		 
		 return g;
     }
     
}