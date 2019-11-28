import { Package } from './package';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

constructor(private http: HttpClient) { }

  getPackages(): Observable<Package[]> {
    let url = 'http://localhost:8080/packages';

    return this.http.get<Package[]>(url);
  }

  getPackage(packageId: string): Observable<Package> {
    let url = 'http://localhost:8080/package/' + packageId ;

    return this.http.get<Package>(url);
  }
}
