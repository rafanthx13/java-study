package rafa_project.exe_teste.teste_junit.main;

import rafa_project.exe_teste.teste_junit.model.Product;
import rafa_project.exe_teste.teste_junit.service.Store;

import java.util.Arrays;
import java.util.List;

public class MainApp {

    /*  Descricao da atividade de teste 08 de Novembro de 2017 - Rafael Morais de Assis:

        Faça um programa que simula um controle de estoque de uma loja.

	    O estoque de uma loja tem vários produtos. Cada produto tem uma descrição,
	    uma quantidade de itens em estoque e um preço unitário.

        Para cada produto é possível efetuar uma venda, onde é passada a quantidade de
        itens vendida, para que a quantidade em estoque do produto seja diminuída da
        quantidade vendida.

	    Para cada produto é possível efetuar uma reposição, onde é passada a quantidade
	    de itens compradas para o estoque para que a quantidade em estoque do produto
	    seja aumentada da respectiva quantidade comprada.

        Para a loja é possivel solicitar uma venda de um produto cuja descrição é x
        e a respectiva venda é de n itens. O resultado da solicitação de venda deve
        ser o valor total de venda do produto, com a respectiva baixa de estoque.

        Para a loja é possivel solicitar uma reposição de estoque de um produto cuja
        descrição é x e a respectiva reposição é de n itens. O resultado da solicitação
        de reposição é somente o respectivo aumento de estoque.

        Para a loja é possível solicitar a quantidade em estoque de um produto cuja
        descrição é x.

	    Para a loja é possível imprimir a tabela de preços de todos os produtos.

	    Para a loja é possível imprimir o estoque de todos os produtos.

	    Faça um main que crie uma loja com 2 produtos:

        	a) bola com 20 itens em estoque a um preço de R$ 25,00 cada;
	        b) tênis com 10 itens em estoque a um preço de R$100,00 cada.

        	Faça uma venda na loja de  10 bolas, e 3 tênis.
            Faça uma reposição em estoque de 5 bolas.
            Mostre qual o estoque dos produtos da loja.

        Crie as classes e métodos que julgar necessários.
        Use adequadamente os conceitos de Orientação a Objetos.
     */

    public static void main(String[] args) throws Exception{
        Store store = new Store();

        Product product1 = new Product();
        product1.setDescription("Ball");
        product1.setQuantity(20);
        product1.setPrice(25.00);

        Product product2 = new Product();
        product2.setDescription("Tennis");
        product2.setPrice(100.00);
        product2.setQuantity(10);

        List<Product> inventory = Arrays.asList(product1,product2);
        store.setInventory(inventory);

        store.printAllInventory();
        store.printAllProduct();
        double valueInitial = 0.0;
        valueInitial += store.requestStoreSell("Ball",10);
        valueInitial += store.requestStoreSell("Tennis",3);
        store.printAllProduct();

        System.out.println(valueInitial);

        store.requestStoreReposition("Ball",5);
        store.printAllInventory();

        System.out.println("END");

    }
}
