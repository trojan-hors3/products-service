import { Product } from './product';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({providedIn: 'root'})
export class ProductService {

constructor(private http: HttpClient) {}

  getProducts(): Observable<any> {
    let headers = new HttpHeaders().set('Authorization', 'Basic dXNlcjpwYXNz');

    return this.http.get('https://product-service.herokuapp.com/api/v1/products', { headers: headers });
  }

  getProduct(productId: string): Observable<any> {
    let headers = new HttpHeaders().set('Authorization', 'Basic dXNlcjpwYXNz');

    return this.http.get('https://product-service.herokuapp.com/api/v1/products/' + productId, { headers: headers });
  }
}
