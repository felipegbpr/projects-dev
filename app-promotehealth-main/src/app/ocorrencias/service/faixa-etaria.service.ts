import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Faixaetaria } from '../ocorrencias/model/faixaetaria';

@Injectable({
  providedIn: 'root'
})
export class FaixaEtariaService {

  constructor(private http:HttpClient) { }

  listFaixaEtaria(): Observable<Faixaetaria[]> {
    const caminhourl = '/api/faixasetarias';

    return this.http.get<Faixaetaria[]>(caminhourl);
  }
}
