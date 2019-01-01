export interface AreaSearchParams {
  north?: number;
  south?: number;
  east?: number;
  west?: number;
  //
  // isValid(): boolean {
  //   return this.northEntered()
  //       && this.southEntered()
  //       && this.eastEntered()
  //       && this.westEntered()
  //       && this.northInAcceptableRange()
  //       && this.southInAcceptableRange()
  //       && this.eastInAcceptableRange()
  //       && this.westInAcceptableRange()
  //       && this.northGreaterThanSouth()
  //       && this.eastGreaterThanWest();
  // }
  //
  // private northEntered() {
  //   return this.north != null;
  // }
  //
  // private southEntered() {
  //   return this.south != null;
  // }
  //
  // private eastEntered() {
  //   return this.east != null;
  // }
  //
  // private westEntered() {
  //   return this.west != null;
  // }
  //
  // private northInAcceptableRange() {
  //   if (this.north == null) {
  //     return true;
  //   }
  //   return false;
  // }
  //
  // private southInAcceptableRange() {
  //   if (this.south == null) {
  //     return true;
  //   }
  //   return false;
  // }
  //
  // private eastInAcceptableRange() {
  //   if (this.east == null) {
  //     return true;
  //   }
  //   return false;
  // }
  //
  // private westInAcceptableRange() {
  //   if (this.west == null) {
  //     return true;
  //   }
  //   return false;
  // }
  //
  // private northGreaterThanSouth() {
  //   if (this.north == null || this.south == null) {
  //     return true;
  //   }
  //   return this.north > this.south;
  // }
  //
  // private eastGreaterThanWest() {
  //   if (this.east == null || this.west == null) {
  //     return true;
  //   }
  //   return this.east > this.west;
  // }
}
