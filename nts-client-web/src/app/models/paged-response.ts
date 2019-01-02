export interface PagedResponse<T> {
  limit: number;
  offset: number;
  sort?: string;
  totalCount: number;
  data: T;
}
