import {SearchParams} from './search-params';
import {PagingParams} from './paging-params';

export interface PagedResponse<T> {
  pagingParams?:  PagingParams;
  searchParams?: SearchParams;
  numberOfMatches?: number;
  searchResults?: T;
}
