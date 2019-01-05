import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AgmMap, LatLngBounds, LatLngLiteral} from '@agm/core';
import {NtsMapService} from './services/nts-map.service';
import {NtsMap} from './models/nts-map';
import {AreaSearchParams} from './models/area-search-params';
import {CoordinateSearchParams} from './models/coordinate-search-params';
import {NameSearchParams} from './models/name-search-params';
import {PagingData} from './models/paging-data';
import {LazyLoadEvent} from 'primeng/api';

const sAreaSearch = 2;
const sCoordSearch = 1;
const sNameSearch = 0;

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

  nameSearchParams = new NameSearchParams();
  coordinateSearchParams = new CoordinateSearchParams();
  areaSearchParams = new AreaSearchParams();

  matchingNames: string[];
  matchingSnippets: string[];
  matchingLatitudes: number[];
  matchingLongitudes: number[];
  matchingNorthLatitudes: number[];
  matchingSouthLatitudes: number[];
  matchingEastLongitudes: number[];
  matchingWestLongitudes: number[];

  ntsMaps: NtsMap[] = [];
  searchResults: NtsMap[] = [];
  selectedMap: NtsMap;

  pagingData = new PagingData();
  totalRecords = 0;
  loading: boolean;

  cols: any[];

  private searchType = sNameSearch;
  private pageInitialized = false;

  constructor(private ntsMapService: NtsMapService) {
  }

  ngOnInit() {
    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'snippet', header: 'Title' },
      { field: 'parent', header: 'Parent' },
      { field: 'north', header: 'North' },
      { field: 'south', header: 'South' },
      { field: 'east', header: 'East' },
      { field: 'west', header: 'West' }
    ];
  }

  ngAfterViewInit() {

  }

  ngOnDestroy() {
  }

  onMapReady(event) {
    console.log(event);
  }

  onMapClick(coords: LatLngLiteral) {
    this.coordinateSearchParams.lat = coords.lat;
    this.coordinateSearchParams.lng = coords.lng;
    this.ntsMapService.getByCoord(this.pagingData, this.coordinateSearchParams).subscribe(maps => {
      this.totalRecords = maps.totalCount;
      this.searchResults = maps.data;
      this.loading = false;
    });
  }

  onBoundsChange(bounds:  LatLngBounds) {
    if (this.zoom  === 0 || ! this.selectedMap) {
      return;
    }

    if (bounds.getNorthEast().lat() < this.selectedMap.north
     || bounds.getSouthWest().lat() > this.selectedMap.south
     || bounds.getNorthEast().lng() < this.selectedMap.east
     || bounds.getSouthWest().lng() > this.selectedMap.west) {
      this.zoom -= 1;
    }
  }

  getMatchingNames(event) {
    this.ntsMapService.getMatchingNames(event.query).subscribe(names => this.matchingNames = names);
  }

  getMatchingSnippets(event) {
    this.ntsMapService.getMatchingSnippets(event.query).subscribe(snippets => this.matchingSnippets = snippets);
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
    this.lazyLoadMaps(null);
  }

  enableSearchByName(): boolean {
    return true;
    // return this.nameSearchParams.isValid();
  }

  getByCoordinate() {
    this.lazyLoadMaps(null);
  }

  enableSearchByCoordinate(): boolean {
    return true;
    // return this.coordinateSearchParams.isValid();
  }

  getByArea() {
    this.lazyLoadMaps(null);
  }

  enableSearchByArea(): boolean {
    return true;
    // return this.areaSearchParams.isValid();
  }

  mapSelected(ntsMap: NtsMap) {
    this.selectedMap = ntsMap;
    this.lat = (ntsMap.north + ntsMap.south) / 2;
    this.lng = (ntsMap.east + ntsMap.west) / 2;
    this.ntsMaps = [ ntsMap ];
    switch (ntsMap.name.length) {
      case 3:
        this.zoom = 6;
        break;

      case 4:
        this.zoom = ntsMap.east - ntsMap.west === 2 ? 8 : (ntsMap.east - ntsMap.west === 4 ? 7 : 6);
        break;

      case 6:
        this.zoom = (ntsMap.east - ntsMap.west === 0.5 ? 10 : (ntsMap.north - ntsMap.south === 1 ? 9 : 8));
    }
  }

  tabChanged(event: any) {
    this.searchType = event.index;
    this.areaSearchParams.clear();
    this.coordinateSearchParams.clear();
    this.nameSearchParams.clear();
  }

  lazyLoadMaps(event: LazyLoadEvent) {
    console.log(event);
    if (! this.pageInitialized) {
      this.pageInitialized = true;
      return;
    }
    if (! event) {
      this.pagingData.offset = 0;
      this.pagingData.sortField = 'name';
      this.pagingData.sortOrder = 1;
    } else {
      this.pagingData.offset = event.first;
      this.pagingData.sortField = event.sortField;
      this.pagingData.sortOrder = event.sortOrder;
    }
    this.loading = true;
    if (this.searchType === sAreaSearch) {
      this.ntsMapService.getByArea(this.pagingData, this.areaSearchParams).subscribe(maps => {
        this.totalRecords = maps.totalCount;
        this.searchResults = maps.data;
        this.loading = false;
      });
    } else if (this.searchType === sCoordSearch) {
      this.ntsMapService.getByCoord(this.pagingData, this.coordinateSearchParams).subscribe(maps => {
        this.totalRecords = maps.totalCount;
        this.searchResults = maps.data;
        this.loading = false;
      });
    } else if (this.searchType === sNameSearch) {
      this.ntsMapService.getByName(this.pagingData, this.nameSearchParams).subscribe(maps => {
        this.totalRecords = maps.totalCount;
        this.searchResults = maps.data;
        this.loading = false;
      });
    }
  }

  getRowStyleClass(currentMap: NtsMap): string {
    if (! this.selectedMap) {
      return 'not-selected';
    }
    return (currentMap.name === this.selectedMap.name)
      ? 'selected'
      : 'not-selected';
  }

}
