import {AfterViewInit, Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AgmMap, LatLngBounds, LatLngLiteral} from '@agm/core';
import {NtsMapService} from './services/nts-map.service';
import {NtsMap} from './models/nts-map';
import {SearchParams} from './models/search-params';
import {PagingData} from './models/paging-data';
import {LazyLoadEvent, MenuItem} from 'primeng/api';
import {OverlayPanel} from 'primeng/primeng';
import {ControlPosition, MapTypeControlOptions, ZoomControlOptions} from '@agm/core/services/google-maps-types';
import {environment} from '../environments/environment';

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
  @ViewChild('op2') op2: OverlayPanel;

  version = environment.version;

  north = 83.49900396917062 - 10;
  south = 40.571925758975915;
  east = -50.84630119999099;
  west = -136.979113699991;
  lat = (this.north + this.south) / 2;
  lng = (this.east + this.west) / 2;

  zoom = 4;
  mapTypeId = 'hybrid';
  mapTypeControl = true;
  mapTypeControlOptions = { position: ControlPosition.TOP_LEFT } as MapTypeControlOptions;
  zoomControlOptions = { position: ControlPosition.RIGHT_CENTER } as ZoomControlOptions;

  searchParams = new SearchParams();

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

  screenHeight: number;
  screenWidth: number;
  mapHeight = '600px';

  displayInfo = true;

  menuItems: MenuItem[];
  selectedMenuItemName = 'Intro';

  private searchType = sNameSearch;
  private pageInitialized = false;

  constructor(private ntsMapService: NtsMapService) {
    this.onResize();
  }

  @HostListener('window:resize', ['$event'])
  onResize(event?) {
    this.screenHeight = window.innerHeight;
    this.screenWidth = window.innerWidth;
    this.mapHeight = this.screenHeight - 16 + 'px';
  }

  ngOnInit() {
    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'snippet', header: 'Title' },
      { field: 'north', header: 'North' },
      { field: 'south', header: 'South' },
      { field: 'east', header: 'East' },
      { field: 'west', header: 'West' }
    ];

    this.menuItems = [
      {label: 'Intro', command: () => this.selectedMenuItemName = 'Intro' },
      {label: 'API', command: () => this.selectedMenuItemName = 'API' },
      {label: 'Dependencies', command: () => this.selectedMenuItemName = 'Dependencies' },
      {label: 'Release History', command: () => this.selectedMenuItemName = 'Releases' },
      {label: 'Contact', command: () => this.selectedMenuItemName = 'Contact' },
    ];
  }

  ngAfterViewInit() {

  }

  ngOnDestroy() {
  }


  onMapReady(event) {
//    this.op2.show(event);
  }

  onMapClick(coords: LatLngLiteral) {
    // this.coordinateSearchParams.lat = coords.lat;
    // this.coordinateSearchParams.lng = coords.lng;
    // this.ntsMapService.getByCoord(this.pagingData, this.coordinateSearchParams).subscribe(maps => {
    //   this.totalRecords = maps.totalCount;
    //   this.searchResults = maps.data;
    //   this.loading = false;
    // });
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

  lazyLoadMaps(event: LazyLoadEvent) {
    if (! this.pageInitialized) {
      this.pageInitialized = true;
      return;
    }
    if (! event) {
      this.pagingData.offset = 0;
      this.pagingData.sortField = 'name';
      this.pagingData.sortOrder = 1;
    } else {
      this.pagingData.offset = event.first ? event.first : 0;
      this.pagingData.sortField = event.sortField ? event.sortField : 'name';
      this.pagingData.sortOrder = event.sortOrder ? event.sortOrder : 1;
    }
    this.loading = true;
    if (! this.searchResults || this.searchResults.length < 5) {
      this.op2.visible = false;
    }
    this.ntsMapService.getByName(this.pagingData, this.searchParams).subscribe(maps => {
      this.totalRecords = maps.totalCount;
      this.searchResults = maps.data;
      this.loading = false;
      if (! this.searchResults || this.searchResults.length < 5) {
        this.op2.visible = false;
      }
      this.op2.visible = true;
    });
  }

  getRowStyleClass(currentMap: NtsMap): string {
    if (! this.selectedMap) {
      return 'not-selected';
    }
    return (currentMap.name === this.selectedMap.name)
      ? 'selected'
      : 'not-selected';
  }

  keyPressed($event: KeyboardEvent) {
    if ($event.key === 'Enter' && $event.code === 'Enter') {
      this.lazyLoadMaps(null);
    }
  }

  getDisplayName(snippet: string) {
    return (! snippet || snippet.length < 16)
      ? snippet
      : snippet.substr(0, 12) + '...';
  }

  showInfoDialog() {
    this.displayInfo = true;
  }

  hideInfoDialog() {
    this.displayInfo = false;
  }
}
