import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {AgmCoreModule} from '@agm/core';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  AutoCompleteModule,
  ButtonModule,
  DialogModule,
  OverlayPanelModule,
  SidebarModule, TabMenuModule,
  TabViewModule,
  TooltipModule
} from 'primeng/primeng';
import {TableModule} from 'primeng/table';
import { AgmSnazzyInfoWindowModule } from '@agm/snazzy-info-window';
import { RouterModule, Routes } from '@angular/router';
import {LinkDirective} from './directives/link.directive';

const appRoutes: Routes = [
  { path: '**', component: AppComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LinkDirective
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyC1iO-vHZ4AdR3oriq_0OXkOv2NDzk7jQU'
    }),
    AgmSnazzyInfoWindowModule,
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AutoCompleteModule,
    ButtonModule,
    DialogModule,
    OverlayPanelModule,
    SidebarModule,
    TableModule,
    TabMenuModule,
    TabViewModule,
    TooltipModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
