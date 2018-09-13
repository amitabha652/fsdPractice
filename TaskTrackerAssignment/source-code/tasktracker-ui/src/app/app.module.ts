import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { ReactiveFormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing/app-routing.module';

import { CreateTaskComponent } from './components/create-task/create-task.component';
import { ViewTaskComponent } from './components/view-task/view-task.component';
import { ApiService } from './shared/services/api.service';
import { UtilService } from './shared/services/util.service';
import { SearchContentPipe } from './shared/pipes/search-content.pipe';
import { SearchByTaskPipe } from './shared/pipes/search-by-task.pipe';
import { SearchByParentPipe } from './shared/pipes/search-by-parent.pipe';

@NgModule({
  declarations: [
    AppComponent,
    CreateTaskComponent,
    ViewTaskComponent,
    SearchContentPipe,
    SearchByTaskPipe,
    SearchByParentPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    ReactiveFormsModule
  ],
  providers: [
    ApiService,
    UtilService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
