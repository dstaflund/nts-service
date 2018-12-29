import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {NtsMap} from '../models/nts-map';
import {HttpClient, HttpParams} from '@angular/common/http';

const sByCoordUrl = 'http://localhost:8080/Gradle___com_github_dstaflund___nts_service_1_0_SNAPSHOT_war__exploded_/json/nts/by/coord';

@Injectable({
  providedIn: 'root'
})
export class NtsMapService {

  constructor(private http:  HttpClient) { }

  getByCoord(lat: number, lng: number): Observable<NtsMap[]> {
    const params = new HttpParams().set('lat', String(lat)).set('lng', String(lng));
    return this.http.get<NtsMap[]>(sByCoordUrl, { params });
  }
}
