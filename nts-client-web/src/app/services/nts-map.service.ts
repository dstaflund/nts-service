import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {NtsMap} from '../models/nts-map';
import {HttpClient, HttpParams} from '@angular/common/http';
import {SearchParams} from '../models/search-params';
import {PagedResponse} from '../models/paged-response';
import {PagingData} from '../models/paging-data';

const sBaseUri = 'http://127.0.0.1:8080/Gradle___com_github_dstaflund___nts_service_1_0_SNAPSHOT_war__exploded_/json';
const sGetMatchingNames = sBaseUri + '/matching/names';
const sGetMatchingSnippets = sBaseUri + '/matching/snippets';
const sGetMatchingLatitudes = sBaseUri + '/matching/latitudes';
const sGetMatchingLongitudes = sBaseUri + '/matching/longitudes';
const sByNameUrl = sBaseUri + '/nts';

@Injectable({
  providedIn: 'root'
})
export class NtsMapService {

  private static isEmpty(value: string): boolean {
    return value == null || value.trim().length === 0;
  }

  private static setPagingData(params: HttpParams, pagingData: PagingData) {
    return params
      .set('limit', String(pagingData.limit))
      .set('offset', String(pagingData.offset))
      .set('sortField', pagingData.sortField)
      .set('sortOrder', String(pagingData.sortOrder));
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

  getByName(pagingData: PagingData, req: SearchParams): Observable<PagedResponse<NtsMap[]>> {
    let params = new HttpParams();
    if (! NtsMapService.isEmpty(req.name)) {
      params = params.set('name', req.name);
    }
    if (! NtsMapService.isEmpty(req.title)) {
      params = params.set('title', req.title);
    }
    if (! NtsMapService.isEmpty(req.south)) {
      params = params.set('north', req.north);
    }
    if (! NtsMapService.isEmpty(req.south)) {
      params = params.set('south', req.south);
    }
    if (! NtsMapService.isEmpty(req.east)) {
      params = params.set('east', req.east);
    }
    if (! NtsMapService.isEmpty(req.west)) {
      params = params.set('west', req.west);
    }
    params = NtsMapService.setPagingData(params, pagingData);
    return this.http.get<PagedResponse<NtsMap[]>>(sByNameUrl, { params});
  }
}
