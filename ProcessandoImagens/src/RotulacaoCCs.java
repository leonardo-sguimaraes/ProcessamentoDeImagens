import java.awt.Point;
import java.util.Stack;


public class RotulacaoCCs {

	public static void main(String args[]) throws Exception{
	     String caminho = ImagemUtil.selecionarCaminhoDaImagem();
	     int img[][] = ImagemUtil.abrirImagemTonsDeCinza(caminho);

    	 
		 int imgBin[][] = RotulacaoCCs.getLimiarizacao(img, 128);
		 
		 
   		 ImagemUtil.visualizarImagem(imgBin, "Binaria");
	     int imgRotulos[][] = pegarRotulos(imgBin, 255, 0);
	     visualizarRotulosColoridos(imgRotulos);
	   
	}
	
	public static int[][] getLimiarizacao(int img[][], int limiar){
		 int largura = ImagemUtil.pegarLargura(img);
    	 int altura = ImagemUtil.pegarAltura(img);
	       
		 int imgBin[][] = new int[largura][altura];
		 for(int x=0; x < largura; x++){
		     for(int y=0; y < altura; y++){
		         if(img[x][y] < limiar)
		             imgBin[x][y] = 255;
		     }
		 }
		 return imgBin;
	}
    
    /**
     * Rotular os componentes 8-conectados utilizando uma pilha
     * @param img
     * @return Devolve uma imagem rotulada das componentes conexas 
     */
    public static int[][] pegarRotulos(int imgEntrada[][], int objeto, int fundo){
    	int largura = ImagemUtil.pegarLargura(imgEntrada);
    	int altura = ImagemUtil.pegarAltura(imgEntrada);
    	boolean imgProcessado[][] = new boolean[largura][altura];
        int imgSaida[][] = new int[largura][altura];
        
        int rotulo = 0;
        
        for(int x=0; x < largura; x++){
            for(int y=0; y < altura; y++){

                if(imgEntrada[x][y] == objeto && imgProcessado[x][y] == false){
                	rotulo += 1;
                    Stack<Point> pilha = new Stack<Point>(); //criando um pilha de pontos (x, y)
                    pilha.push(new Point(x,y)); //empilhando
                    while (!pilha.isEmpty()) {
                    	Point ponto = pilha.pop(); //desempilhando
                    	imgSaida[ponto.x][ponto.y] = rotulo;
                    	imgProcessado[ponto.x][ponto.y] = true;
                    	//empilhando os pixels 8-conexas
                    	for(int i=-1; i <= 1; i++){
                    		for(int j=-1; j <= 1; j++){
                    			if (ImagemUtil.pertenceAoDominio(imgEntrada, ponto.x + i, ponto.y + j) && imgEntrada[ponto.x + i][ponto.y + j] == objeto && imgProcessado[ponto.x + i][ponto.y + j] == false) {			
                    				pilha.push(new Point(ponto.x + i, ponto.y + j));
			                    	imgProcessado[ponto.x + i][ponto.y + j] = true;                    				
                    			}
                    		}
                    	}
                    }
                    
                    
                }
            }
        }
        return imgSaida;
    }



	public static void visualizarRotulosColoridos(int imgEntrada[][]){
    	int largura = ImagemUtil.pegarLargura(imgEntrada);
    	int altura = ImagemUtil.pegarAltura(imgEntrada);
		int max = 0;
		
		//calculando a quantidade de CCs
        for(int x=0; x < largura; x++){
            for(int y=0; y < altura; y++){
				if(max < imgEntrada[x][y]){
				     max = imgEntrada[x][y];
				}
			}
		}		

		//gerando colores aleatorias
    	int vermelho[] = new int[max+1];
    	int verde[] = new int[max+1];
    	int azul[] = new int[max+1];
    	for(int i=0; i <= max; i++){
    		vermelho[i] = (int) (Math.random() * 256); 
    		verde[i] = (int) (Math.random() * 256);
    		azul[i] = (int) (Math.random() * 256);
    	}
    	
    	//criando a imagem de saida
    	int imgSaida[][][] = new int[largura][altura][3];
        for(int x=0; x < largura; x++){
            for(int y=0; y < altura; y++){
            	
            	int rotulo = imgEntrada[x][y];

            	if(rotulo == 0){ //fundo da imagem => cor branca
					imgSaida[x][y][0] = 255;
					imgSaida[x][y][1] = 255;
					imgSaida[x][y][2] = 255;										            	
            	} 
            	else{ //senao, cor aleatoria
					imgSaida[x][y][0] = vermelho[rotulo];
					imgSaida[x][y][1] = verde[rotulo];
					imgSaida[x][y][2] = azul[rotulo];
            	}
            }
        }
       ImagemUtil.visualizarImagem(imgSaida, "Imagem rotulada: " + max + "CCs");
       
	}
    
}
