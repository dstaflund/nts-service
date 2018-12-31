import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {NtsMap} from '../models/nts-map';
import {HttpClient, HttpParams} from '@angular/common/http';

const sBaseUri = 'http://127.0.0.1:8080/Gradle___com_github_dstaflund___nts_service_1_0_SNAPSHOT_war__exploded_/json';
const sByCoordUrl = sBaseUri + '/nts/by/coord';
const sGetMatchingNames = sBaseUri + '/matching/names';
const sGetMatchingSnippets = sBaseUri + '/matching/snippets';
const sGetMatchingParents = sBaseUri + '/matching/parents';
const sGetMatchingLatitudes = sBaseUri + '/matching/latitudes';
const sGetMatchingLongitudes = sBaseUri + '/matching/longitudes';

@Injectable({
  providedIn: 'root'
})
export class NtsMapService {

  constructor(private http:  HttpClient) { }

  getByCoord(lat: number, lng: number): Observable<NtsMap[]> {
    const params = new HttpParams().set('lat', String(lat)).set('lng', String(lng));
    return this.http.get<NtsMap[]>(sByCoordUrl, { params });
  }

  getMatchingNames(query: any): Observable<string[]> {
    const params = new HttpParams().set('name', String(query));
    return this.http.get<string[]>(sGetMatchingNames, { params });
  }

  getMatchingSnippets(query: any): Observable<string[]> {
    const params = new HttpParams().set('snippet', String(query));
    return this.http.get<string[]>(sGetMatchingSnippets, { params });
  }

  getMatchingParents(query: any): Observable<string[]> {
    const params = new HttpParams().set('parent', String(query));
    return this.http.get<string[]>(sGetMatchingParents, { params });
  }

  getMatchingLatitudes(query: any): Observable<number[]> {
    const params = new HttpParams().set('latitude', String(query));
    return this.http.get<number[]>(sGetMatchingLatitudes, { params });
  }

  getMatchingLongitudes(query: any): Observable<number[]> {
    const params = new HttpParams().set('longitude', String(query));
    return this.http.get<number[]>(sGetMatchingLongitudes, { params });
  }
}
