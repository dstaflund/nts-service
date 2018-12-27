import {AfterViewInit, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AgmMap, LatLngLiteral} from '@agm/core';
import {NtsMapService} from './services/nts-map.service';
import {NtsMap} from './models/nts-map';

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
  ntsMaps: NtsMap[] = [];

  constructor(private ntsMapService: NtsMapService) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
  }

  ngOnDestroy(): void {
  }

  onMapReady($event): void {
    console.log($event);
  }

  onMapClick(coords: LatLngLiteral): void {
    this.ntsMapService.getByCoord(coords.lat, coords.lng).subscribe( ntsMaps => {
      this.ntsMaps = ntsMaps;
    });
  }

  onBoundsChange(event: any): void {
  }
}
