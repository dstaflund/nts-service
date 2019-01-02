export class CoordinateSearchParams {
  lat?: number;
  lng?: number;
  //
  // isValid(): boolean {
  //   return false;
  // }

  clear() {
    this.lat = null;
    this.lng = null;
  }
}
