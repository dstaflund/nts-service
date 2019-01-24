export class SearchParams {
  name?: string;
  title?: string;
  north?: string;
  south?: string;
  east?: string;
  west?: string;

  clear() {
    this.name = null;
    this.title = null;
    this.north = null;
    this.south = null;
    this.east = null;
    this.west = null;
  }
}
