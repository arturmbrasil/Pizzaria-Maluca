package estrutura_de_dados;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class TabuleiroPrincipal {
	static ListaSimples listaJogadores = new ListaSimples();	
	static ListaDupla tabuleiro = new ListaDupla();
	static int j1 = 0, j2 = 0, j3 = 0, j4 = 0, j5 = 0; //posicao de cada jogador
	static PizzaCalabresa calabresa = new PizzaCalabresa(0);
	static PizzaMarguerita marguerita = new PizzaMarguerita(0);
	static PizzaPortuguesa portuguesa = new PizzaPortuguesa(0);
	static PizzaRomana romana = new PizzaRomana(0);
	static PizzaToscana toscana = new PizzaToscana(0);
	static PizzaVegetariana vegetariana = new PizzaVegetariana(0);
	static boolean jogaDadoNovamente = false;
	static Pilha cartas = new Pilha();
	static ListaSimplesCartas auxCartas = new ListaSimplesCartas();	

	public static void main(String[] args) {
		int nJogadores = menuJogo();
		
		//Cada Jogador escolhe uma pizza
		for(int i = 1; i<=nJogadores ; i++){
			int pizza = Integer.parseInt(JOptionPane.showInputDialog("Jogador "+ i + "\n"  
					+ "Escolha sua pizza:\n"
					+ "1 - Calabresa(calabresa. azeitona, brócolis, ovo, ervilha)\n"
					+ "2 - Marguerita(calabresa, brócolis, tomate, queijo, milho)\n"
					+ "3 - Portuguesa(presunto, azeitona, queijo, ovo, milho)\n"
					+ "4 - Romana(queijo, cebola, milho, presunto, ervilha)\n"
					+ "5 - Toscana(tomate, presunto, azeitona, calabresa, cebola)\n"
					+ "6 - Vegetariana(brócolis, tomate, ervilha, cebola, ovos)"));
			switch (pizza) {
			case 1:
				if(calabresa.getJogador() != 0){
					System.out.println("Essa Pizza é do jogador " + calabresa.getJogador() + "! Escolha outra.");
					i--;
				}
				else{
					calabresa = new PizzaCalabresa(i);	
				}
				break;
			case 2:
				if(marguerita.getJogador() != 0){
					System.out.println("Essa Pizza é do jogador " + marguerita.getJogador() + "! Escolha outra.");
					i--;
				}
				else
					marguerita = new PizzaMarguerita(i);
				break;
			case 3:
				if(portuguesa.getJogador() != 0){
					System.out.println("Essa Pizza é do jogador " + portuguesa.getJogador() + "! Escolha outra.");
					i--;
				}
				else
					portuguesa = new PizzaPortuguesa(i);
				break;
			case 4:
				if(romana.getJogador() != 0){
					System.out.println("Essa Pizza é do jogador " + romana.getJogador() + "! Escolha outra.");
					i--;
				}
				else
					romana = new PizzaRomana(i);
				break;
			case 5:
				if(toscana.getJogador() != 0){
					System.out.println("Essa Pizza é do jogador " + toscana.getJogador() + "! Escolha outra.");
					i--;
				}
				else
					toscana = new PizzaToscana(i);
				break;
			case 6:
				if(vegetariana.getJogador() != 0){
					System.out.println("Essa Pizza é do jogador " + vegetariana.getJogador() + "! Escolha outra.");
					i--;
				}
				else
					vegetariana = new PizzaVegetariana(i);
				break;
			}
		}
		
		montaTabuleiro();
		
		while(true){
			for(int i = 1; i<= nJogadores; i++){
				if(cartas.estaVazia()){
					System.out.println("Embaralhando as cartas de Sorte ou Azar\n");
					SorteOuAzar();						
				}
				
				if(calabresa.rodadaSemJogar && i == calabresa.getJogador()){
					System.out.println("Jogador "+i+", essa rodada você não pode jogar!");
					calabresa.setRodadaSemJogar(false);
					i++;
				}
				else if(marguerita.rodadaSemJogar && i == marguerita.getJogador()){
					System.out.println("Jogador "+i+", essa rodada você não pode jogar!");
					marguerita.setRodadaSemJogar(false);
					i++;
				}
				else if(portuguesa.rodadaSemJogar && i == portuguesa.getJogador()){
					System.out.println("Jogador "+i+", essa rodada você não pode jogar!");
					portuguesa.setRodadaSemJogar(false);
					i++;
				}
				else if(romana.rodadaSemJogar && i == romana.getJogador()){
					System.out.println("Jogador "+i+", essa rodada você não pode jogar!");
					romana.setRodadaSemJogar(false);
					i++;
				}
				else if(toscana.rodadaSemJogar && i == toscana.getJogador()){
					System.out.println("Jogador "+i+", essa rodada você não pode jogar!");
					toscana.setRodadaSemJogar(false);
					i++;
				}
				else if(vegetariana.rodadaSemJogar && i == vegetariana.getJogador()){
					System.out.println("Jogador "+i+", essa rodada você não pode jogar!");
					vegetariana.setRodadaSemJogar(false);
					i++;
				}
				
				rodada(i);	
				
				if(jogaDadoNovamente){//Se o jogador pega a carta de jogar novamente
					i--;
					jogaDadoNovamente = false;
				}
				
				
			}
		}
		
	}
	
	//cria cartas sorte ou azar
	public static void SorteOuAzar(){
		
		//coloca as cartas em uma listasimples primeiro para depois embaralhar
		auxCartas.insereUltimo(new SorteOuAzar("Pegue um ingrediente de sua escolha (Sorte)", 0));
		auxCartas.insereUltimo(new SorteOuAzar("Pegue um ingrediente de sua escolha (Sorte)", 0));
		auxCartas.insereUltimo(new SorteOuAzar("Pegue um ingrediente de sua escolha (Sorte)", 0));
		auxCartas.insereUltimo(new SorteOuAzar("Pegue um ingrediente de sua escolha (Sorte)", 0));
		auxCartas.insereUltimo(new SorteOuAzar("Pegue um ingrediente de sua escolha (Sorte)", 0));

		auxCartas.insereUltimo(new SorteOuAzar("Perca um ingrediente aleatório (Azar)", 4));
		auxCartas.insereUltimo(new SorteOuAzar("Perca um ingrediente aleatório (Azar)", 4));
		auxCartas.insereUltimo(new SorteOuAzar("Perca um ingrediente aleatório (Azar)", 4));
		auxCartas.insereUltimo(new SorteOuAzar("Perca um ingrediente aleatório (Azar)", 4));
		auxCartas.insereUltimo(new SorteOuAzar("Perca um ingrediente aleatório (Azar)", 4));

		auxCartas.insereUltimo(new SorteOuAzar("Pegue dois ingredientes de sua escolha (Sorte)", 1));
		auxCartas.insereUltimo(new SorteOuAzar("Pegue dois ingredientes de sua escolha (Sorte)", 1));
		
		auxCartas.insereUltimo(new SorteOuAzar("Sua pizza queimou, perca todos os ingredientes (Azar)", 5));
		auxCartas.insereUltimo(new SorteOuAzar("Sua pizza queimou, perca todos os ingredientes (Azar)", 5));

		auxCartas.insereUltimo(new SorteOuAzar("Jogue os dados novamente (Sorte)", 2));
		auxCartas.insereUltimo(new SorteOuAzar("Jogue os dados novamente (Sorte)", 2));
		auxCartas.insereUltimo(new SorteOuAzar("Jogue os dados novamente (Sorte)", 2));

		auxCartas.insereUltimo(new SorteOuAzar("Fique sem jogar por 1 rodada (Azar)", 3));
		auxCartas.insereUltimo(new SorteOuAzar("Fique sem jogar por 1 rodada (Azar)", 3));
		auxCartas.insereUltimo(new SorteOuAzar("Fique sem jogar por 1 rodada (Azar)", 3));
	
		
		//pega da lista simples e coloca na pilha aleatoriamente
		for(int i = 0 ; i<20 ; i++){
			Random n = new Random();
			int num = n.nextInt(auxCartas.comprimento());
			SorteOuAzar cartaEscolhida = auxCartas.retornaDaPosicao(num);
			cartas.empilha(cartaEscolhida);
			auxCartas.removeNaPosicao(num);
			
		}	
	}
			
	private static void verifica(String posicao, int jogador){
		if(posicao.equalsIgnoreCase("PERDE TUDO")){
			System.out.println(" - PERDE TUDO");
			if(calabresa.getJogador() == jogador){
				calabresa.limpaPizza();
				System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
			}
			if(marguerita.getJogador() == jogador){
				marguerita.limpaPizza();
				System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
			}
			if(portuguesa.getJogador() == jogador){
				portuguesa.limpaPizza();
				System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
			}
			if(romana.getJogador() == jogador){
				romana.limpaPizza();
				System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
			}
			if(toscana.getJogador() == jogador){
				toscana.limpaPizza();
				System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
			}
			if(vegetariana.getJogador() == jogador){
				vegetariana.limpaPizza();
				System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
			}
		}
		else if(posicao.equalsIgnoreCase("SORTE OU AZAR")){
			System.out.println(" - SORTE OU AZAR");

			NoPilha noCarta = cartas.desempilha();
			SorteOuAzar Ccarta = noCarta.carta;
			int carta = Ccarta.getNumero();
			
			//Pegue um ingrediente de sua escolha
			if(carta == 0){
				if(calabresa.getJogador() == jogador){
					
					ArrayList<String> array = calabresa.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue um ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								calabresa.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
	
				}
				
				if(marguerita.getJogador() == jogador){
					ArrayList<String> array = marguerita.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue um ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								marguerita.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

				}
				if(portuguesa.getJogador() == jogador){
					ArrayList<String> array = portuguesa.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue um ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								portuguesa.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

					
				}
				if(romana.getJogador() == jogador){
					
					ArrayList<String> array = romana.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue um ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								romana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

				}
				if(toscana.getJogador() == jogador){
					
					ArrayList<String> array = toscana.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue um ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								toscana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

				}
				if(vegetariana.getJogador() == jogador){
					
					ArrayList<String> array = vegetariana.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue um ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								vegetariana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

				}
			}
			
			//Pegue dois ingredientes de sua escolha (Sorte) x2
			if(carta == 1){
				if(calabresa.getJogador() == jogador){
					//Primeiro
					ArrayList<String> array = calabresa.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue dois ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								calabresa.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

				
					//Segundo
					array = calabresa.faltamEssesIngredientes();
					msg = "SORTE OU AZAR\n\n"
							+ "Pegue mais um ingrediente!\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								calabresa.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
				}
				if(marguerita.getJogador() == jogador){
					ArrayList<String> array = marguerita.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue dois ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								marguerita.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);

					//Segundo
					array = marguerita.faltamEssesIngredientes();
					msg = "SORTE OU AZAR\n\n"
							+ "Pegue mais um ingrediente!\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								marguerita.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
				}
				if(portuguesa.getJogador() == jogador){
					ArrayList<String> array = portuguesa.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue dois ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								portuguesa.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					

					
					//Segundo
					array = portuguesa.faltamEssesIngredientes();
					msg = "SORTE OU AZAR\n\n"
							+ "Pegue mais um ingrediente!\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								portuguesa.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
				}
				if(romana.getJogador() == jogador){
					
					ArrayList<String> array = romana.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue dois ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								romana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
					//Segundo
					array = romana.faltamEssesIngredientes();
					msg = "SORTE OU AZAR\n\n"
							+ "Pegue mais um ingrediente!\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								romana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
				}
				if(toscana.getJogador() == jogador){
					
					ArrayList<String> array = toscana.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue dois ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								toscana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
					//Segundo
					array = toscana.faltamEssesIngredientes();
					msg = "SORTE OU AZAR\n\n"
							+ "Pegue mais um ingrediente!\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								toscana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
				}
				if(vegetariana.getJogador() == jogador){
					
					ArrayList<String> array = vegetariana.faltamEssesIngredientes();
					String msg = "SORTE OU AZAR\n\n"
							+ "Pegue dois ingrediente de sua escolha\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					boolean tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								vegetariana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
					//Segundo
					array = vegetariana.faltamEssesIngredientes();
					msg = "SORTE OU AZAR\n\n"
							+ "Pegue mais um ingrediente!\n"
							+ "Esses são os ingredientes que você precisa:\n\n";
					for(int i=0 ; i< array.size()  ; i++){
						msg += array.get(i) + "\n";
					}
					msg += "\nEscolha um (de 1 a " + array.size() + ")";
					tenteNovamente = true;
					do{
						int op = Integer.parseInt(JOptionPane.showInputDialog(msg));
						for (int i = 0; i<array.size();i++){
							if(op-1 == i){
								vegetariana.perdeIngredienteX(array.get(op-1));
								tenteNovamente = false;
							}
						}
						if(tenteNovamente)
							System.out.println("Opa, numero invalido tente novamente!");
					
					}while(tenteNovamente);
					
					
				}
			}
			
			//Jogue os dados novamente (Sorte)
			if(carta == 2){
				System.out.println("SORTE OU AZAR\nJogue os dados novamente (Sorte)");
				jogaDadoNovamente = true;
			}
			
			//Fique sem jogar por 1 rodada (Azar)
			if(carta == 3){
				if(calabresa.getJogador() == jogador){
					calabresa.setRodadaSemJogar(true);
					System.out.println("SORTE OU AZAR\nFique sem jogar por 1 rodada (Azar)");
				}
				if(marguerita.getJogador() == jogador){
					marguerita.setRodadaSemJogar(true);
					System.out.println("SORTE OU AZAR\nFique sem jogar por 1 rodada (Azar)");
				}
				if(portuguesa.getJogador() == jogador){
					portuguesa.setRodadaSemJogar(true);
					System.out.println("SORTE OU AZAR\nFique sem jogar por 1 rodada (Azar)");
				}
				if(romana.getJogador() == jogador){
					romana.setRodadaSemJogar(true);
					System.out.println("SORTE OU AZAR\nFique sem jogar por 1 rodada (Azar)");

				}
				if(toscana.getJogador() == jogador){
					toscana.setRodadaSemJogar(true);
					System.out.println("SORTE OU AZAR\nFique sem jogar por 1 rodada (Azar)");

				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.setRodadaSemJogar(true);
					System.out.println("SORTE OU AZAR\nFique sem jogar por 1 rodada (Azar)");
				}
			}
			
			//Perca um ingrediente aleatório (Azar)
			if(carta == 4){
				if(calabresa.getJogador() == jogador){
					System.out.println("SORTE OU AZAR\nPerca um ingrediente aleatório (Azar)");
					calabresa.ganhaIngredienteAleatorio();
				}
				if(marguerita.getJogador() == jogador){
					System.out.println("SORTE OU AZAR\nPerca um ingrediente aleatório (Azar)");
					marguerita.ganhaIngredienteAleatorio();
				}
				if(portuguesa.getJogador() == jogador){
					System.out.println("SORTE OU AZAR\nPerca um ingrediente aleatório (Azar)");
					portuguesa.ganhaIngredienteAleatorio();
				}
				if(romana.getJogador() == jogador){
					System.out.println("SORTE OU AZAR\nPerca um ingrediente aleatório (Azar)");
					romana.ganhaIngredienteAleatorio();
				}
				if(toscana.getJogador() == jogador){
					System.out.println("SORTE OU AZAR\nPerca um ingrediente aleatório (Azar)");
					toscana.ganhaIngredienteAleatorio();
				}
				if(vegetariana.getJogador() == jogador){
					System.out.println("SORTE OU AZAR\nPerca um ingrediente aleatório (Azar)");
					vegetariana.ganhaIngredienteAleatorio();
				}
			}
			
			//Sua pizza queimou, perca todos os ingredientes (Azar)
			if(carta == 5){
				if(calabresa.getJogador() == jogador){
					calabresa.limpaPizza();
					System.out.println("SORTE OU AZAR\nSua pizza queimou, perca todos os ingredientes (Azar)");
					System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
				}
				if(marguerita.getJogador() == jogador){
					marguerita.limpaPizza();
					System.out.println("SORTE OU AZAR\nSua pizza queimou, perca todos os ingredientes (Azar)");
					System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
				}
				if(portuguesa.getJogador() == jogador){
					portuguesa.limpaPizza();
					System.out.println("SORTE OU AZAR\nSua pizza queimou, perca todos os ingredientes (Azar)");
					System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
				}
				if(romana.getJogador() == jogador){
					romana.limpaPizza();
					System.out.println("SORTE OU AZAR\nSua pizza queimou, perca todos os ingredientes (Azar)");
					System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
				}
				if(toscana.getJogador() == jogador){
					toscana.limpaPizza();
					System.out.println("SORTE OU AZAR\nSua pizza queimou, perca todos os ingredientes (Azar)");
					System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.limpaPizza();
					System.out.println("SORTE OU AZAR\nSua pizza queimou, perca todos os ingredientes (Azar)");
					System.out.println("O Jogador "+ jogador + " queimou a pizza e perdeu tudo!" );
				}
			}
			
			
		}
		else{
			switch (posicao) {
			case "CEBOLA":
				System.out.println(" - CEBOLA");
				if((calabresa.getJogador() == jogador) || (marguerita.getJogador() == jogador) || (portuguesa.getJogador() == jogador)){
					System.out.println("O Jogador "+ jogador + " não precisa de cebola!" );
				}
				if(romana.getJogador() == jogador){
					romana.perdeIngredienteX("cebola");
				}
				if(toscana.getJogador() == jogador){
					toscana.perdeIngredienteX("cebola");
				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.perdeIngredienteX("cebola");
				}
				break;
			case "CALABRESA":
				System.out.println(" - CALABRESA");
				if((portuguesa.getJogador() == jogador) || (romana.getJogador() == jogador) || (vegetariana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de calabresa!" );
				if(calabresa.getJogador() == jogador){
					calabresa.perdeIngredienteX("calabresa");
				}
				if(toscana.getJogador() == jogador){
					toscana.perdeIngredienteX("calabresa");
				}
				if(marguerita.getJogador() == jogador){
					marguerita.perdeIngredienteX("calabresa");
				}
				break;
			case "QUEIJO":
				System.out.println(" - QUEIJO");
				if((calabresa.getJogador() == jogador) || (toscana.getJogador() == jogador) || (vegetariana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de queijo!" );
				if(marguerita.getJogador() == jogador){
					marguerita.perdeIngredienteX("queijo");
				}
				if(portuguesa.getJogador() == jogador){
					portuguesa.perdeIngredienteX("queijo");
				}
				if(romana.getJogador() == jogador){
					romana.perdeIngredienteX("queijo");
				}
				break;
			case "TOMATE":
				System.out.println(" - TOMATE");
				if((calabresa.getJogador() == jogador) || (portuguesa.getJogador() == jogador) || (romana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de tomate!" );
				if(marguerita.getJogador() == jogador){
					marguerita.perdeIngredienteX("tomate");
				}
				if(toscana.getJogador() == jogador){
					toscana.perdeIngredienteX("tomate");
				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.perdeIngredienteX("tomate");
				}
				break;
			case "OVOS":
				System.out.println(" - OVOS");
				if((marguerita.getJogador() == jogador) || (toscana.getJogador() == jogador) || (romana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de ovos!" );
				if(calabresa.getJogador() == jogador){
					calabresa.perdeIngredienteX("ovos");
				}
				if(portuguesa.getJogador() == jogador){
					portuguesa.perdeIngredienteX("ovos");
				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.perdeIngredienteX("ovos");
				}
				break;
			case "AZEITONA":
				System.out.println(" - AZEITONA");
				if((marguerita.getJogador() == jogador) || (vegetariana.getJogador() == jogador) || (romana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de azeitona!" );
				if(calabresa.getJogador() == jogador){
					calabresa.perdeIngredienteX("azeitona");
				}
				if(portuguesa.getJogador() == jogador){
					portuguesa.perdeIngredienteX("azeitona");
				}
				if(toscana.getJogador() == jogador){
					toscana.perdeIngredienteX("azeitona");
				}
				break;
			case "MILHO":
				System.out.println(" - MILHO");
				if((calabresa.getJogador() == jogador) || (vegetariana.getJogador() == jogador) || (toscana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de milho!" );
				if(marguerita.getJogador() == jogador){
					marguerita.perdeIngredienteX("milho");
				}
				if(portuguesa.getJogador() == jogador){
					portuguesa.perdeIngredienteX("milho");
				}
				if(romana.getJogador() == jogador){
					romana.perdeIngredienteX("milho");
				}
				break;
			case "PRESUNTO":
				System.out.println(" - PRESUNTO");
				if((calabresa.getJogador() == jogador) || (marguerita.getJogador() == jogador) || (vegetariana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de presunto!" );
				if(portuguesa.getJogador() == jogador){
					portuguesa.perdeIngredienteX("presunto");
				}
				if(romana.getJogador() == jogador){
					romana.perdeIngredienteX("presunto");
				}
				if(toscana.getJogador() == jogador){
					toscana.perdeIngredienteX("presunto");
				}
				break;
			case "BROCOLIS":
				System.out.println(" - BROCOLIS");
				if((portuguesa.getJogador() == jogador) || (romana.getJogador() == jogador) || (toscana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de brocolis!" );
				if(calabresa.getJogador() == jogador){
					calabresa.perdeIngredienteX("brocolis");
				}
				if(marguerita.getJogador() == jogador){
					marguerita.perdeIngredienteX("brocolis");
				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.perdeIngredienteX("brocolis");
				}
				break;
			case "ERVILHA":
				System.out.println(" - ERVILHA");
				if((marguerita.getJogador() == jogador) || (portuguesa.getJogador() == jogador) || (toscana.getJogador() == jogador))
					System.out.println("O Jogador "+ jogador + " não precisa de ervilha!" );
				if(calabresa.getJogador() == jogador){
					calabresa.perdeIngredienteX("ervilha");
				}
				if(romana.getJogador() == jogador){
					romana.perdeIngredienteX("ervilha");
				}
				if(vegetariana.getJogador() == jogador){
					vegetariana.perdeIngredienteX("ervilha");
				}
				break;
			}
		}
	}
	
	private static void rodada(int jogador){
		switch (jogador) {
		case 1:
			j1 = j1 + dado(jogador);
			if(j1 > 35)
				j1 = j1 - 35;
			System.out.print("-Jogador 1 esta na posicao: "+j1);
			verifica(tabuleiro.moveParaPosicao(j1).getNomeEtapa() , jogador); ////verificar
			break;
		case 2:
			j2 = j2 + dado(jogador);
			if(j2 > 35)
				j2 = j2 - 35;
			System.out.print("-Jogador 2 esta na posicao: "+j2);
			verifica(tabuleiro.moveParaPosicao(j2).getNomeEtapa() , jogador); ////verificar
			
			break;
		case 3:
			j3 = j3 + dado(jogador);
			if(j3 > 35)
				j3 = j3 - 35;
			System.out.print("-Jogador 3 esta na posicao: "+j3);
			verifica(tabuleiro.moveParaPosicao(j3).getNomeEtapa() , jogador); ////verificar
			break;
		case 4:
			j4 = j4 + dado(jogador);
			if(j4 > 35)
				j4 = j4 - 35;
			System.out.print("-Jogador 4 esta na posicao: "+j4);
			verifica(tabuleiro.moveParaPosicao(j4).getNomeEtapa() , jogador); ////verificar
			break;
		case 5:
			j5 = j5 + dado(jogador);
			if(j5 > 35)
				j5 = j5 - 35;
			System.out.print("-Jogador 5 esta na posicao: "+j5);
			verifica(tabuleiro.moveParaPosicao(j5).getNomeEtapa() , jogador); ////verificar
			break;
		}
	}

	private static int dado(int jogador){
		JOptionPane.showMessageDialog(null, "Jogador "+ jogador + " jogue o dado!");
		Random dado = new Random();
		int num = dado.nextInt(6) + 1;
		System.out.println("-Jogador "+ jogador + " tirou " +num+ "!");
		return num;		 
	}
	
	private static void montaTabuleiro(){
		tabuleiro.insereUltimo("PERDE TUDO", 1);
		tabuleiro.insereUltimo("CEBOLA", 2);
		tabuleiro.insereUltimo("SORTE OU AZAR", 3);
		tabuleiro.insereUltimo("CALABRESA", 4);
		tabuleiro.insereUltimo("QUEIJO", 5);
		tabuleiro.insereUltimo("SORTE OU AZAR", 6);
		tabuleiro.insereUltimo("SORTE OU AZAR", 7);
		tabuleiro.insereUltimo("TOMATE", 8);
		tabuleiro.insereUltimo("OVOS", 9);
		tabuleiro.insereUltimo("AZEITONA", 10);
		tabuleiro.insereUltimo("SORTE OU AZAR", 11);
		tabuleiro.insereUltimo("SORTE OU AZAR", 12);
		tabuleiro.insereUltimo("MILHO", 13);
		tabuleiro.insereUltimo("ERVILHA", 14);
		tabuleiro.insereUltimo("SORTE OU AZAR", 15);
		tabuleiro.insereUltimo("PRESUNTO", 16);
		tabuleiro.insereUltimo("TOMATE", 17);
		tabuleiro.insereUltimo("MILHO", 18);
		tabuleiro.insereUltimo("SORTE OU AZAR", 19);
		tabuleiro.insereUltimo("SORTE OU AZAR", 20);
		tabuleiro.insereUltimo("BROCOLIS", 21);
		tabuleiro.insereUltimo("SORTE OU AZAR", 22);
		tabuleiro.insereUltimo("OVOS", 23);
		tabuleiro.insereUltimo("CEBOLA", 24);
		tabuleiro.insereUltimo("SORTE OU AZAR", 25);
		tabuleiro.insereUltimo("CALABRESA", 26);
		tabuleiro.insereUltimo("SORTE OU AZAR", 27);
		tabuleiro.insereUltimo("PRESUNTO", 28);
		tabuleiro.insereUltimo("SORTE OU AZAR", 29);
		tabuleiro.insereUltimo("QUEIJO", 30);
		tabuleiro.insereUltimo("ERVILHA", 31);
		tabuleiro.insereUltimo("SORTE OU AZAR", 32);
		tabuleiro.insereUltimo("BROCOLIS", 33);
		tabuleiro.insereUltimo("AZEITONA", 34);
		tabuleiro.insereUltimo("SORTE OU AZAR", 35);
	}
	
	private static int menuJogo(){		
		
		listaJogadores.inserePrimeiro("j5", 0);
		listaJogadores.inserePrimeiro("j4", 0);
		listaJogadores.inserePrimeiro("j3", 0);
		listaJogadores.inserePrimeiro("j2", 0);
		listaJogadores.inserePrimeiro("j1", 0);
		
		System.out.println("TABULEIRO:\n"
				+ "1 - PERDE TUDO\n"
				+ "2 - CEBOLA\n"
				+ "3 - SORTE OU AZAR\n"
				+ "4 - CALABRESA\n"
				+ "5 - QUEIJO\n"
				+ "6 - SORTE OU AZAR\n"
				+ "7 - SORTE OU AZAR\n"
				+ "8 - TOMATE\n"
				+ "9 - OVOS\n"
				+ "10 - AZEITONA\n"
				+ "11 - SORTE OU AZAR\n"
				+ "12 - SORTE OU AZAR\n"
				+ "13 - MILHO\n"
				+ "14 - ERVILHA\n"
				+ "15 - SORTE OU AZAR\n"
				+ "16 - PRESUNTO\n"
				+ "17 - TOMATE\n"
				+ "18 - MILHO\n"
				+ "19 - SORTE OU AZAR\n"
				+ "20 - SORTE OU AZAR\n"
				+ "21 - BROCOLIS\n"
				+ "22 - SORTE OU AZAR\n"
				+ "23 - OVOS\n"
				+ "24 - CEBOLA\n"
				+ "25 - SORTE OU AZAR\n"
				+ "26 - CALABRESA\n"
				+ "27 - SORTE OU AZAR\n"
				+ "28 - PRESUNTO\n"
				+ "29 - SORTE OU AZAR\n"
				+ "30 - QUEIJO\n"
				+ "31 - ERVILHA\n"
				+ "32 - SORTE OU AZAR\n"
				+ "33 - BROCOLIS\n"
				+ "34 - AZEITONA\n"
				+ "35 - SORTE OU AZAR\n");
		int numJogadores = 0;
		while(numJogadores<2 || numJogadores>5){
			numJogadores = Integer.parseInt(JOptionPane.showInputDialog("Numero de jogadores? (2 à 5)")); 		
		}
		return numJogadores;
		}
	}

