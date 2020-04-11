import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ItemService } from './../item/item.service';

@Component({
  selector: 'app-item-cadastro',
  templateUrl: './item-cadastro.component.html',
  styleUrls: ['./item-cadastro.component.css']
})
export class ItemCadastroComponent implements OnInit {

  itens = [];

  constructor(private itemService: ItemService) { }

  // esse metodo eh deito depois do contrutor, tipo um segundo construtor
  ngOnInit() {
    // vai fazer a chamada do nosso serviço
    this.itemService.listar()
      .subscribe(dados => this.itens = dados);
  }

  // consultar, arrowFunciont, criar funcao na hora
  consultar() {
    this.itemService.listar().subscribe(dados => this.itens = dados);
  }

  // frm eh uma instancia do nosso formulario, nela podemos mexer aqui
  // esse console.log serve para mostrar algo na tela quando adicionar algo
  adicionar(frm: FormControl) {
    console.log(frm.value);

    this.itemService.adicionar(frm.value)
      .subscribe(() => {
        frm.reset();
        this.consultar();
      });
  }

}
