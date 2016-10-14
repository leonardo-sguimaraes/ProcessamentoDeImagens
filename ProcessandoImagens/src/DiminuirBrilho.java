
public class DiminuirBrilho {
	public static void main(String args[]) throws Exception {
		
		String caminho = ImagemUtil.selecionarCaminhoDaImagem();
		
		int img[][] = ImagemUtil.abrirImagemTonsDeCinza(caminho);
		ImagemUtil.visualizarImagem(img, "Teste antes");
		
		int largura = ImagemUtil.pegarLargura(img);
		int altura = ImagemUtil.pegarAltura(img);
		for(int y=0; y < altura; y++){
			for(int x=0; x < largura; x++){
				int sub = img[x][y] - 50;
				if(sub < 0){
					img[x][y] = 0;
				}else{
					img[x][y] = sub;
				}
			}
		}
		
		
		//manipular
		
		ImagemUtil.visualizarImagem(img, "Teste depois");
		
	}

}
