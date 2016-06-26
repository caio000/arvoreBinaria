

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

import java.util.ArrayList;


public class Arvore {
    static No inicio = null;
    
    public static void main(String[] args) {
       
       
    	Scanner read = new Scanner(System.in);
    	
        //AS LINHAS ABAIXO FORMAM UMA ARVORE CONTROLADA/CONHECIDA PARA TESTES
        insereNo(12);
        insereNo(9);
        insereNo(6);
        insereNo(2);
        insereNo(8);
        insereNo(10);
        insereNo(19);
        insereNo(18);
        insereNo(16);
        insereNo(14);
        insereNo(13);
        insereNo(11);
        insereNo(17);
        insereNo(22);
        insereNo(20);
        insereNo(24);
        insereNo(21);
        insereNo(40);
        insereNo(4);
        insereNo(5);
        insereNo(3);
        insereNo(1);

	    // AS LINHAS ABAIXO INSEREM N ELEMENTOS ALEATORIOS NA ARVORE 
    /*    int N = 3000;
        
        for(int i=1; i<=N; i++)
            insereNo((int)(Math.random()*N));
         */   
        
        
        // O METODO ABAIXO LE OS ELEMENTOS DE UM ARQUIVO TEXTO E INSERE-OS NA ARVORE
        //leArquivo();
        
        
		// O METODO ABAIXO EXIBE UMA ARVORE
        exibeArvore();
        System.out.println("Total de nós: " + contaNos(inicio));
        
        // NA LINHA ABAIXO, ï¿½ EXIBIDO O Nï¿½MERO DE Nï¿½VEIS, UTILIZADO O Mï¿½TODO contaNiveis QUE RETORNA O NUMERO DE NIVEIS DE UMA ARVORE (OU SUB-ARVORE) EM QUE O ELEMENTO RAï¿½Z ï¿½ PASSADO POR PARAMETRO
        // System.out.println(contaNiveis(inicio)+" nï¿½veis");
        
        // NA LINHA ABAIXO, ï¿½ EXIBIDO O Nï¿½MERO DE FOLHAS, UTILIZADO O Mï¿½TODO contaFolhas QUE RETORNA O NUMERO DE FOLHAS DE UMA ARVORE (OU SUB-ARVORE) EM QUE O ELEMENTO RAï¿½Z ï¿½ PASSADO POR PARAMETRO
        //System.out.println(contaFolhas(inicio)+" folhas");
        
        
        // O Mï¿½TODO ABAIXO REMOVE TODAS AS FOLHAS DE UMA ï¿½RVORE EM QUE O ELEMENTO RAï¿½Z ï¿½ PASSADO POR PARAMETRO
        //removeFolhas(inicio);
      
    
     
        // O METODO ABAIXO EXIBE TODAS AS FOLHAS
        //exibeFolhas(inicio);
        
        //System.out.println(inicio.valor);
        
        //REMOCAO DE NOS (PASSADO O VALOR A SER REMOVIDO)
        remover(457);
        
        /*
        System.out.println("Informe o valor a ser removido da arvore..:");
        int valor = Integer.parseInt(read.nextLine());
        
        int removeu = removeTodos(inicio, valor);
        if (removeu == 0)
        	System.out.println("O numero nao existe na arvore");
        else
        	System.out.println("Foi removido " + removeu + " nos da arvore.");
        
        exibeArvore();
        */
        
        /*
        int maiorValor = maiorValor(inicio).valor;
        System.out.println("Maior valor: " + maiorValor);
        
        removePrimos(maiorValor);
        */
        
        /*
        removeImparesDireita(inicio.direita);
        exibeArvore();
        
        removeParEsquerda(inicio.esquerda);
        exibeArvore();
        */
        
        
        removeNoUmFilho(inicio);
        exibeArvore();
        
        System.out.println("Total de nós: " + contaNos(inicio));
        
        
        //conta nï¿½veis
        //System.out.println("\n ï¿½rvore com "+contaNiveis(inicio)+" Nï¿½veis");
        
        //conta folhas
        // System.out.println("\n ï¿½rvore com "+contaFolhas(inicio)+" Folhas");
        
    }
    
    
    static void leArquivo() {

        Scanner ler = new Scanner(System.in);

        String nome = "";
        JFileChooser arquivo = new JFileChooser();
        int retorno = arquivo.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION){
            nome = arquivo.getSelectedFile().getAbsolutePath();

        }
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);
            System.out.println(nome);
            String linha = lerArq.readLine();
            while (linha != null) {
                insereNo(Integer.parseInt(linha));
                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }
    
    static void insereNo(int v){
        if(inicio == null)
            inicio = new No(v);
        else{
            insereNoRec(inicio, v);
        }
    }
    
   static void insereNoRec(No n, int v){
       if(v <= n.valor)
           if(n.esquerda == null)
               n.esquerda = new No(v);
            else
               insereNoRec(n.esquerda,v);
       else
           if(n.direita == null)
               n.direita = new No(v);
            else 
               insereNoRec(n.direita,v);
   }

    static void exibeArvore(){
        if(inicio==null)
            System.out.println("Arvore Vazia...");
        else
            exibeNo(inicio,0);
    }
    

      static void exibeNo(No n, int x){

        if(n.direita != null)
            exibeNo(n.direita, x+1);
        else
            System.out.println();

        for(int i=1; i<=x; i++)
            System.out.print("|  ");
        
        if((n.esquerda == null)&&(n.direita == null))
            System.out.print("["+n.valor+"]");
        else
            System.out.print(n.valor);
   
        if(n.esquerda != null)
            exibeNo(n.esquerda, x+1);
        else
            System.out.println();
        
    }
      
      static int contaNiveis(No n){
          int x=0 , xe=1, xd=1;
               
          if(n.esquerda != null)
               xe+=contaNiveis(n.esquerda);
          if(n.direita != null)
              xd+=contaNiveis(n.direita);
          
          if(xd>xe)          
            return xd;
          else
              return xe;
      }
       
      static int contaFolhas(No n){
          int x=0;
          
          if(n.esquerda != null )
               x+=contaFolhas(n.esquerda);
          if(n.direita != null)
              x+=contaFolhas(n.direita);
          
          if((n.esquerda == null)&&(n.direita == null))
              x++;
              
          return x;
      }   
      
      
      static void exibeFolhas(No n){
          
          if(n.esquerda != null )
               exibeFolhas(n.esquerda);
          if(n.direita != null)
              exibeFolhas(n.direita);
          
          if((n.esquerda == null)&&(n.direita == null))
              System.out.println(n.valor);

      }  
      
   
   // ESSE METODO EXISTE EXCLUISIVAMENTE PARA VERIFICAR SE O VALOR A SER REMOVIDO ESTA NA RAIZ DA ARVORE (inicio)
    static void remover(int x){
	   if(inicio.valor == x){
		   if((inicio.esquerda == null) && (inicio.direita==null))
				inicio = null;
			else 
				if((inicio.esquerda != null) && (inicio.direita != null))
					removeDoisFilhos(inicio,x);
				else
					if(inicio.esquerda == null)
						inicio = inicio.direita;
					else
						inicio = inicio.esquerda;
	   }
	   else
			removeNo(inicio, x);
   } 
    
    static void removeNo(No n, int x){
        
        int f = contaFilhos(n,x);
              
        switch(f){
			case -1: System.out.println("O nï¿½ nï¿½o existe na ï¿½rvore"); break;
			case  0: removeFolha(n,x); break;
			case  1: removeUmFilho(n,x); break; 
			case  2: removeDoisFilhos(n,x); break;
		}
    }
    
    static int contaFilhos(No n, int x){
        int nro_filhos = 0;
        
        if(n.valor == x){
            if(n.esquerda != null)
                nro_filhos++;
            if(n.direita != null)
                nro_filhos++;
        }else{

			if(x < n.valor)
				if(n.esquerda != null)
                    nro_filhos+=contaFilhos(n.esquerda,x);    
                else
					return -1;
                    
			if(x > n.valor)
                if(n.direita != null)
                    nro_filhos+=contaFilhos(n.direita,x);
                else
                    return -1;
        }    
        return nro_filhos;
    }
    
    static void removeFolha(No n, int x) {
        
        if(n.esquerda != null){
            if (n.esquerda.valor == x) {
                n.esquerda = null;
            }else{ 
                if(n.valor > x){
                    removeFolha(n.esquerda, x);
                }
            }
        }

        if(n.direita != null){
            if (n.direita.valor == x) {
                n.direita = null;
            }else{ 
                if(n.valor < x){
                    removeFolha(n.direita, x);
                }
            }
        }
    }
        
    static void removeUmFilho(No n, int x) {
        boolean removeu = false;
        
        if (n.esquerda != null) {
            if (n.esquerda.valor == x) {
                removeu = true;
                if (n.esquerda.esquerda != null) {
                    n.esquerda = n.esquerda.esquerda;
                } else if (n.esquerda.direita != null) {
                    n.esquerda = n.esquerda.direita;
                }
            }
        }

        if (n.direita != null) {
            if (n.direita.valor == x) {
                removeu = true;
                if (n.direita.esquerda != null) {
                    n.direita = n.direita.esquerda;
                } else if (n.direita.direita != null) {
                    n.direita = n.direita.direita;
                }
            }

        }

        if (!removeu) {
            if (x < n.valor) {
                removeUmFilho(n.esquerda, x);
            } else {
                removeUmFilho(n.direita, x);
            }
        }
    }

    static void removeDoisFilhos(No n, int x){
         if(n.valor == x){
			
			No tmp = noMaisAEsquerda(n.direita);
			
			// ISSO SERVE PARA RESOLVER O PROBLEMA DO N.direita SER O NUMERO A SER REMOVIDO
			int novovalor = tmp.valor;
			// ESTA SENDO PASSADO POR PARAMETRO O VALOR DE N (PAI DE N.direita)
			removeNo(n,tmp.valor);
			n.valor = novovalor;
			
		} else{
			if(x<n.valor)
				removeDoisFilhos(n.esquerda, x);
			else
				removeDoisFilhos(n.direita, x);
		}		
    }
    
    static No noMaisAEsquerda(No n){
		No retorno = null;
		if(n.esquerda!=null){
			
			retorno = noMaisAEsquerda(n.esquerda);
		}
			else
				retorno = n;
		
		return retorno;
		
	}
	// ESSE Mï¿½TODO REMOVE TODAS AS FOLHAS EXISTENTES NA ï¿½RVORE ATUAL (NOTE QUE A REMOï¿½ï¿½O DE FOLHAS IRï¿½ GERAR UMA NOVA ï¿½RVORE COM OUTRAS FOLHAS) 
    static void removeFolhas(No n) {
		if (n.esquerda != null) {
			if (temFilho(n.esquerda)) {
				removeFolhas(n.esquerda);
			} else {
				n.esquerda = null;
			}
		}
		
		if (n.direita != null) {
			if (temFilho(n.direita)) {
				removeFolhas(n.direita);
			} else {
				n.direita = null;
			}	
		}
    }
	// Mï¿½TODO QUE VERIFICA SE UM Nï¿½ TEM FILHOS OU Nï¿½O
    static boolean temFilho(No n){
          if((n.esquerda == null) && (n.direita == null))
              return false;
          else
              return true;
      }

    public static int removeTodos(No ponteiro, int valor){
    	int result = 0;
    	
    	if (ponteiro != null && ponteiro.valor == valor) {
    		remover(valor);
    		return 1;
    	} else if (ponteiro != null && ponteiro.valor != valor) {
    		result += removeTodos(ponteiro.esquerda,valor);
    		result += removeTodos(ponteiro.direita,valor);
    	}
    	
    	return result;
    }
    
    public static No maiorValor(No n){
    	
    	No retorno = null;
    	if(n.direita != null){
    		
    		retorno = maiorValor(n.direita);
    	} else
    		retorno = n;
    	
    	return retorno;
    	
    }
    
    public static ArrayList<Integer> numPrimos(int maiorValor){
    	
    	ArrayList<Integer> numPrimos = new ArrayList<>();
    	
    	for (int i = 2; i <= maiorValor; i++) {
    		int cont = 0;
    		for (int j = 1; j <= i; j++) {
    			if (i % j == 0){
    				cont += 1;
    			}
    			
    			if (cont > 2) {
    				break;
    			}
    		}
    		
    		if (cont == 2)
    			numPrimos.add(i);
    			
    		
    	}
    	
    	return numPrimos;
    	
    }
    
    public static void removePrimos(int maiorValor){
    	ArrayList<Integer> numPrimos = numPrimos(maiorValor);
    	
    	for (int i = 0; i < numPrimos.size(); i++) {
    		remover(numPrimos.get(i));
    	}
    	
    }
    
    /**
     * Essa função remove todos os nós com valores impares da sub-arvore a direita.
     * 
     * @author Caio de Freitas Adriano
     * @since 2016-06-26
     * 
     * @param No ponteiro: recebe o nó raiz da sub-arvore a direita.
     */
    public static void removeImparesDireita(No ponteiro) {
    	
    	// verifica se o Nó existe e se ele é impar
    	if (ponteiro != null && ponteiro.valor % 2 == 1)
    		removeNo(inicio, ponteiro.valor);
    	
    	if (ponteiro != null) {
    		removeImparesDireita(ponteiro.esquerda);
        	removeImparesDireita(ponteiro.direita);
        	
        	if (ponteiro != null && ponteiro.valor % 2 == 1)
        		removeNo(inicio, ponteiro.valor);
    	}
    	
    	
    }
    /**
     * Essa função remove todos os nós com valores pares da sub-arvore a esquerda.
     * 
     * @author Caio de Freitas Adriano
     * @since 2016-06-26
     * 
     * @param No ponteiro: recebe o nó raiz da sub-arvore a esquerda.
     */
    public static void removeParEsquerda(No ponteiro) {
    	
    	// verifica se o nó existe e se ele é par
    	if (ponteiro != null && ponteiro.valor % 2 == 0)
    		removeNo(inicio, ponteiro.valor);
    	
    	if (ponteiro != null) {
    		removeParEsquerda(ponteiro.esquerda);
    		removeParEsquerda(ponteiro.direita);
    		
    		if (ponteiro != null && ponteiro.valor % 2 == 0)
        		removeNo(inicio, ponteiro.valor);
    	}
    	
    }
    /**
     * Essa função remove da árvore todos os nós que possu apenas um filho.
     * Sobrando apenas os nós folhas, com dois filhos ou apenas o nó raiz.
     * 
     * @author Caio de Freitas Adriano
     * @since 2016-06-26
     * 
     * @param No ponteiro: raiz da árvore binária.
     */
    public static void removeNoUmFilho(No ponteiro) {
    	
    	if (ponteiro != null && contaFilhos(inicio, ponteiro.valor) == 1)
    		removeNo(inicio, ponteiro.valor);
    	
    	if (ponteiro != null) {
    		removeNoUmFilho(ponteiro.esquerda);
    		removeNoUmFilho(ponteiro.direita);
    	}
    }
    /**
     * Conta quantos nós a árvore possui.
     * 
     * @author Caio de Freitas Adriano
     * @since 26-06-2016
     * 
     * @param No ponteiro: nó raiz da árvore.
     * 
     * @return int qtd: quantidade de nós que a árvore possui.
     */
    public static int contaNos(No ponteiro) {
		int qtd = 0;
		
		if (ponteiro != null){
			qtd++;
			qtd += contaNos(ponteiro.esquerda);
			qtd += contaNos(ponteiro.direita);
		}
			
		return qtd;
	}
    
}