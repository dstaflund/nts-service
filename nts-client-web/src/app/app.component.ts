import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AgmMap, LatLngLiteral} from '@agm/core';
import {NtsMapService} from './services/nts-map.service';
import {NtsMap} from './models/nts-map';
import {AreaSearchParams} from './models/area-search-params';
import {CoordinateSearchParams} from './models/coordinate-search-params';
import {NameSearchParams} from './models/name-search-params';

@Component({
  selector: 'nts-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy, AfterViewInit {
  @ViewChild('agmMap') agmMap: AgmMap;

  title = 'National Topographic Service (NTS) Map Search';
  north = 83.49900396917062 - 10;
  south = 40.571925758975915;
  east = -50.84630119999099;
  west = -136.979113699991;
  lat = (this.north + this.south) / 2;
  lng = (this.east + this.west) / 2;

  fullScreenControl = true;
  zoom = 4;
  mapTypeId = 'hybrid';
  mapTypeControl = true;

  nameSearchParams = {} as NameSearchParams;
  coordinateSearchParams = {} as CoordinateSearchParams;
  areaSearchParams = {} as AreaSearchParams;

  matchingNames: string[];
  matchingSnippets: string[];
  matchingParents: string[];
  matchingLatitudes: number[];
  matchingLongitudes: number[];
  matchingNorthLatitudes: number[];
  matchingSouthLatitudes: number[];
  matchingEastLongitudes: number[];
  matchingWestLongitudes: number[];

  ntsMaps: NtsMap[] = [];
  searchResults: NtsMap[] = [];

  constructor(private ntsMapService: NtsMapService) {
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
  }

  ngOnDestroy() {
  }

  onMapReady(event) {
    console.log(event);
  }

  onMapClick(coords: LatLngLiteral) {
    const coordParams = { lat: coords.lat, lng: coords.lng } as CoordinateSearchParams;
    this.ntsMapService.getByCoord(coordParams).subscribe( ntsMaps => {
      this.ntsMaps = ntsMaps;
      this.searchResults = ntsMaps;
    });
  }

  onBoundsChange(event) {
  }

  getMatchingNames(event) {
    this.ntsMapService.getMatchingNames(event.query).subscribe(names => this.matchingNames = names);
  }

  getMatchingSnippets(event) {
    this.ntsMapService.getMatchingSnippets(event.query).subscribe(snippets => this.matchingSnippets = snippets);
  }

  getMatchingParents(event) {
    this.ntsMapService.getMatchingParents(event.query).subscribe(parents => this.matchingParents = parents);
  }

  getMatchingLatitudes(event) {
    this.ntsMapService.getMatchingLatitudes(event.query).subscribe(latitudes => this.matchingLatitudes = latitudes);
  }

  getMatchingLongitudes(event) {
    this.ntsMapService.getMatchingLongitudes(event.query).subscribe(longitudes => this.matchingLongitudes = longitudes);
  }

  getMatchingNorthLatitudes(event) {
    this.ntsMapService.getMatchingLatitudes(event.query).subscribe(latitudes => this.matchingNorthLatitudes = latitudes);
  }

  getMatchingSouthLatitudes(event) {
    this.ntsMapService.getMatchingLatitudes(event.query).subscribe(latitudes => this.matchingSouthLatitudes = latitudes);
  }

  getMatchingEastLongitudes(event) {
    this.ntsMapService.getMatchingLongitudes(event.query).subscribe(longitudes => this.matchingEastLongitudes = longitudes);
  }

  getMatchingWestLongitudes(event) {
    this.ntsMapService.getMatchingLongitudes(event.query).subscribe(longitudes => this.matchingWestLongitudes = longitudes);
  }

  getByName() {
    this.ntsMapService.getByName(this.nameSearchParams).subscribe(maps => this.searchResults = maps);
  }

  enableSearchByName(): boolean {
    return true;
    // return this.nameSearchParams.isValid();
  }

  getByCoordinate() {
    this.ntsMapService.getByCoord(this.coordinateSearchParams).subscribe(maps => this.searchResults = maps);
  }

  enableSearchByCoordinate(): boolean {
    return true;
    // return this.coordinateSearchParams.isValid();
  }

  getByArea() {
    this.ntsMapService.getByArea(this.areaSearchParams).subscribe(maps => this.searchResults = maps);
  }

  enableSearchByArea(): boolean {
    return true;
    // return this.areaSearchParams.isValid();
  }

  mapSelected(ntsMap: NtsMap) {
    console.log(ntsMap);
    this.lat = (ntsMap.north + ntsMap.south) / 2;
    this.lng = (ntsMap.east + ntsMap.west) / 2;
  }

  clearNameValues() {
    this.nameSearchParams.name = null;
    this.nameSearchParams.snippet = null;
    this.nameSearchParams.parent = null;
  }

  clearCoordinateValues() {
    this.coordinateSearchParams.lat = null;
    this.coordinateSearchParams.lng = null;
  }

  clearAreaValues() {
    this.areaSearchParams.north = null;
    this.areaSearchParams.south = null;
    this.areaSearchParams.east = null;
    this.areaSearchParams.west = null;
  }
}
