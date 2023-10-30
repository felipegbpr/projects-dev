import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ocorrencia } from '../ocorrencias/model/ocorrencia';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OcorrenciaService {

  constructor(private http:HttpClient) { }

  listOcorrencias(): Observable<Ocorrencia[]> {

    const caminhourl = '/api/ocorrencias';
    return this.http.get<Ocorrencia[]>(caminhourl);

    // return [
    //   {id: 1, regiao: 'Norte', total_enxames: 1564}
    // ];
  }
}
