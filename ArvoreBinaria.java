//package arvore;

public class ArvoreBinaria {

	public static No raiz;

	public static void main(String[] args) {

		inserir(20);
		inserir(10);
		inserir(30);
		inserir(5);
		inserir(15);
		inserir(40);
		inserir(14);
		inserir(16);
		
		System.out.println("\tArvore Binaria");
		exibir(raiz);
		
		
		System.out.println("Removeu " + removeTodos(raiz, 10) + " nos.");
		
		System.out.println("Nivel da arvore: " + contaNiveis(raiz));
		
		/*
		remover(10, raiz);
		remover(16, raiz);
		remover(14, raiz);
		*/
		
		System.out.println("\tArvore Binaria");
		exibir(raiz);
		
		

	}

	// ======================funções============================================================================================

	public static int contaNiveis(No n){
		int  xe=1, xd=1;

		if(n.esquerda != null)
			xe+=contaNiveis(n.esquerda);
		if(n.direita != null)
			xd+=contaNiveis(n.direita);
		
		if(n==raiz){
			xd--;
			xe--;
		}

		if(xd>xe)          
			return xd;
		else
			return xe;
	}
	
	public static No menorEsquerda(No ponteiro) {

		No result = null;
		
		if (ponteiro.esquerda != null) {
			result = menorEsquerda(ponteiro.esquerda);
		}else {
			result =  ponteiro;
		}
		
		return result;
		
	}
	
	public static No remover (int valor, No ponteiro) {
		
		
		if (valor != ponteiro.valor && valor > ponteiro.valor) {
			ponteiro.direita = remover(valor, ponteiro.direita);
		} else if (valor != ponteiro.valor && valor < ponteiro.valor) {
			ponteiro.esquerda = remover(valor, ponteiro.esquerda);
		} else {
			switch (contaFilhos(ponteiro)) {
			case 1:
				if (ponteiro.esquerda == null)
					ponteiro = ponteiro.direita;
				else
					ponteiro = ponteiro.esquerda;
				break;
			case 2:
				No temp = menorEsquerda(ponteiro.direita);
				remover(temp.valor, ponteiro);
				temp.direita = ponteiro.direita;
				temp.esquerda = ponteiro.esquerda;
				
				ponteiro = temp;
				break;

			default:
				ponteiro = null;
				break;
			}
			
			return ponteiro;
		}
		return ponteiro;
	}
	
	public static int contaFilhos(No ponteiro) {
		int result;
		if (ponteiro.direita == null && ponteiro.esquerda == null) {
			result = 0;
		} else if (ponteiro.direita != null && ponteiro.esquerda != null) {
			result = 2;
		} else {
			result = 1;
		}
		return result;
	}
	
	public static void exibir(No ponteiro) {
		if (ponteiro!= null) {
			exibir(ponteiro.esquerda);
			System.out.println(ponteiro.valor);
			exibir(ponteiro.direita);
		}
	}

	public static void inserir(int x) {
		if (raiz == null)
			raiz = new No(x);
		else
			inserirRecursivo(x, raiz);
	}

	private static void inserirRecursivo(int x, No ponteiro) {
		if (x > ponteiro.valor)
			if (ponteiro.direita == null)
				ponteiro.direita = new No(x);
			else
				inserirRecursivo(x, ponteiro.direita);
		else if (ponteiro.esquerda == null)
			ponteiro.esquerda = new No(x);
		else
			inserirRecursivo(x, ponteiro.esquerda);

	}
	
	public static int removeTodos(No ponteiro, int valor) {
    	int result = 0;
    	if (ponteiro != null && ponteiro.valor == valor) {
    		remover(valor, raiz);
    		return 1;
    	} else if (ponteiro != null) {
    		result += removeTodos(ponteiro.esquerda, valor);
    		result += removeTodos(ponteiro.direita, valor);
    	}
    	
    	return result;
    }
	
	public static void noEsquerda(No ponteiro){
		
		if (ponteiro.esquerda != null) {
			noEsquerda(ponteiro.esquerda);
		} else {
			System.out.println(ponteiro.valor);
		}
		
	}
	
	public static int contaNos(No ponteiro) {
		int qtd = 0;
		
		if (ponteiro != null){
			qtd++;
			qtd += contaNos(ponteiro.esquerda);
			qtd += contaNos(ponteiro.direira);
		}
			
		return qtd;
	}
}


