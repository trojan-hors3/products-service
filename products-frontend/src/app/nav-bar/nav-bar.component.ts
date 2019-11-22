import { Link } from './../link';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  constructor() { }

  ngOnInit() {
  }

  productsShopLink: Link = { url: '/products', message: 'Products Shop' };
}
