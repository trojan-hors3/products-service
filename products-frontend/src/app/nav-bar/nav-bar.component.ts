import { Link } from './../link';
import { BasketService } from './../basket.service';
import { Basket } from './../basket';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  constructor(private basketService: BasketService) { }

  ngOnInit() {}

  productsShopLink: Link = { url: '/shop', message: 'Products Shop' };
}
