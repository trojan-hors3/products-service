import { BasketService } from './basket.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProductComponent } from './product/product.component';
import { FilterProductsPipe } from './pipes.pipe';

import { OrderModule } from 'ngx-order-pipe';
import { Basket } from './basket';
import { ShopComponent } from './shop/shop.component';
import { PackageComponent } from './package/package.component';

@NgModule({
   declarations: [
      AppComponent,
      NavBarComponent,
      ProductComponent,
      FilterProductsPipe,
      ShopComponent,
      PackageComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule,
      OrderModule
   ],
   providers: [
      Basket
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
