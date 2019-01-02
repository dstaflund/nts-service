export class NameSearchParams {
  name?: string;
  snippet?: string;
  //
  // isValid(): boolean {
  //   return false;
  // }

  clear() {
    this.name = null;
    this.snippet = null;
  }
}
