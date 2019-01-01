import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {NtsMap} from '../models/nts-map';
import {HttpClient, HttpParams} from '@angular/common/http';
import {NameSearchParams} from '../models/name-search-params';
import {CoordinateSearchParams} from '../models/coordinate-search-params';
import {AreaSearchParams} from '../models/area-search-params';

const sBaseUri = 'http://127.0.0.1:8080/Gradle___com_github_dstaflund___nts_service_1_0_SNAPSHOT_war__exploded_/json';
const sGetMatchingNames = sBaseUri + '/matching/names';
const sGetMatchingSnippets = sBaseUri + '/matching/snippets';
const sGetMatchingLatitudes = sBaseUri + '/matching/latitudes';
const sGetMatchingLongitudes = sBaseUri + '/matching/longitudes';
const sByNameUrl = sBaseUri + '/nts/by/name';
const sByCoordUrl = sBaseUri + '/nts/by/coord';
const sByAreaUrl = sBaseUri + '/nts/by/area';

@Injectable({
  providedIn: 'root'
})
export class NtsMapService {

  private static isEmpty(value: string): boolean {
    return value == null || value.trim().length === 0;
  }

  constructor(private http:  HttpClient) { }

  getMatchingNames(query: any): Observable<string[]> {
    const params = new HttpParams().set('name', String(query));
    return this.http.get<string[]>(sGetMatchingNames, { params });
  }

  getMatchingSnippets(query: any): Observable<string[]> {
    const params = new HttpParams().set('snippet', String(query));
    return this.http.get<string[]>(sGetMatchingSnippets, { params });
  }

  getMatchingLatitudes(query: any): Observable<number[]> {
    const params = new HttpParams().set('latitude', String(query));
    return this.http.get<number[]>(sGetMatchingLatitudes, { params });
  }

  getMatchingLongitudes(query: any): Observable<number[]> {
    const params = new HttpParams().set('longitude', String(query));
    return this.http.get<number[]>(sGetMatchingLongitudes, { params });
  }

  getByCoord(searchParams:  CoordinateSearchParams): Observable<NtsMap[]> {
    const params = new HttpParams()
      .set('lat', String(searchParams.lat))
      .set('lng', String(searchParams.lng));
    return this.http.get<NtsMap[]>(sByCoordUrl, { params });
  }

  getByName(searchParams: NameSearchParams): Observable<NtsMap[]> {
    let params = new HttpParams();
    if (! NtsMapService.isEmpty(searchParams.name)) {
      params = params.set('name', searchParams.name);
    }
    if (! NtsMapService.isEmpty(searchParams.snippet)) {
      params = params.set('snippet', searchParams.snippet);
    }
    return this.http.get<NtsMap[]>(sByNameUrl, { params});
  }

  getByArea(searchParams: AreaSearchParams): Observable<NtsMap[]> {
    const params = new HttpParams()
      .set('n', String(searchParams.north))
      .set('s', String(searchParams.south))
      .set('e', String(searchParams.east))
      .set('w', String(searchParams.west));
    return this.http.get<NtsMap[]>(sByAreaUrl, { params });
  }
}
