import { Package } from './package';
import { AppComponent } from './app.component';
import { Product } from './product';
import { Basket } from './basket';
import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class BasketService {

basket: Basket = new Basket();

headers = new HttpHeaders({
    'Content-Type': 'application/json'
});

constructor(private http: HttpClient) {}

  getBasket(): Basket {
    return this.basket;
  }

  getBasketSize(): number {
    return this.basket.products.length + this.basket.packages.length;
  }

  getBasketPrice(): number {
    return this.basket.getBasketPrice();
  }

  clearBasket() {
    this.basket.clearBasket();
  }
}
