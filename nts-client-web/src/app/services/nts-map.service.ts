import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {NtsMap} from '../models/nts-map';
import {HttpClient, HttpParams} from '@angular/common/http';
import {NameSearchParams} from '../models/name-search-params';
import {CoordinateSearchParams} from '../models/coordinate-search-params';
import {AreaSearchParams} from '../models/area-search-params';
import {PagedResponse} from '../models/paged-response';
import {PagingData} from '../models/paging-data';

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

  getByCoord(pagingData: PagingData, req: CoordinateSearchParams): Observable<PagedResponse<NtsMap[]>> {
    let params = new HttpParams()
      .set('lat', String(req.lat))
      .set('lng', String(req.lng));
    params = this.setPagingData(params, pagingData);
    return this.http.get<PagedResponse<NtsMap[]>>(sByCoordUrl, { params });
  }

  getByName(pagingData: PagingData, req: NameSearchParams): Observable<PagedResponse<NtsMap[]>> {
    let params = new HttpParams();
    if (! NtsMapService.isEmpty(req.name)) {
      params = params.set('name', req.name);
    }
    if (! NtsMapService.isEmpty(req.snippet)) {
      params = params.set('snippet', req.snippet);
    }
    params = this.setPagingData(params, pagingData);
    return this.http.get<PagedResponse<NtsMap[]>>(sByNameUrl, { params});
  }

  getByArea(pagingData: PagingData, req: AreaSearchParams): Observable<PagedResponse<NtsMap[]>> {
    let params = new HttpParams()
      .set('n', String(req.north))
      .set('s', String(req.south))
      .set('e', String(req.east))
      .set('w', String(req.west));
    params = this.setPagingData(params, pagingData);
    return this.http.get<PagedResponse<NtsMap[]>>(sByAreaUrl, { params });
  }

  setPagingData(params: HttpParams, pagingData: PagingData) {
    params = params.set('limit', String(pagingData.limit));
    params = params.set('offset', String(pagingData.offset));
    if (pagingData.sort && pagingData.sort.trim().length > 0) {
      params = params.set('sort', pagingData.sort);
    }
    return params;
  }
}
