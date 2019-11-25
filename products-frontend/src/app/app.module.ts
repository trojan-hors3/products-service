import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './product/product.component';
import { FilterProductsPipe } from './pipes.pipe';

import { OrderModule } from 'ngx-order-pipe';

@NgModule({
   declarations: [
      AppComponent,
      NavBarComponent,
      ProductsComponent,
      ProductComponent,
      FilterProductsPipe
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule,
      OrderModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
