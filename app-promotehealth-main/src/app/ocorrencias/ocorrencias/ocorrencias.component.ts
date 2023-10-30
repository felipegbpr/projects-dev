import { Component, OnInit } from '@angular/core';

import { OcorrenciaService } from '../service/ocorrencia.service';
import { Regiao } from './model/regiao';
import { Ocorrencia } from './model/ocorrencia';
import { Subscription } from 'rxjs';

import { RegiaoService } from '../service/regiao.service';
import { FaixaEtariaService } from '../service/faixa-etaria.service';
import { Faixaetaria } from './model/faixaetaria';



@Component({
  selector: 'app-ocorrencias',
  templateUrl: './ocorrencias.component.html',
  styleUrls: ['./ocorrencias.component.css']
})
export class OcorrenciasComponent implements OnInit {

  ocorrencia_exame: Ocorrencia[] = [];
  regioes: Regiao[] = [];
  faixasetarias: Faixaetaria[] = [];


  readonly subscriptions = new Subscription;


  constructor(private ocorrenciaService:OcorrenciaService,
              private regiaoService:RegiaoService,
              private faixaEtariaService: FaixaEtariaService) { }

  ngOnInit(): void {
    this.listarRegioes();
    this.listarOcorrencias();
    this.listarFaixas();
  }

  private listarRegioes(): void {
    const subscription = this.regiaoService.listRegiao().subscribe((regioes => {
      this.regioes = regioes;
    }));

    this.subscriptions.add(subscription);
  }


 private listarOcorrencias(): void{
     const subscriptionOcorrencias = this.ocorrenciaService.listOcorrencias().subscribe((
         ocorrencias => {this.ocorrencia_exame = ocorrencias
       }
     ));

     this.subscriptions.add(subscriptionOcorrencias);
   }

  private listarFaixas(): void {
    const subscriptionFaixas = this.faixaEtariaService.listFaixaEtaria().subscribe((
      faixa => {this.faixasetarias = faixa}
    ));

    this.subscriptions.add(subscriptionFaixas);
  }

}
