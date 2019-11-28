import { PackageService } from './../package.service';
import { Package } from './../package';
import { BasketService } from './../basket.service';
import { element } from 'protractor';
import { map } from 'rxjs/operators';
import { ProductService } from './../product.service';
import { Product } from './../product';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  products: Product[];
  packages: Package[];
  orderProducts: string = 'usdPrice';
  orderPackages: string = 'price';
  reverseProducts: boolean = false;
  reversePackages: boolean = false;

  constructor(private productService: ProductService,
              private packageService: PackageService,
              private basketService: BasketService) { }

  ngOnInit() {
    this.getProducts();
    this.getPackages();
  }

  getProducts() {
    this
    .productService
    .getProducts()
    .subscribe(products => this.products = products);
  }

  getPackages() {
    this
    .packageService
    .getPackages()
    .subscribe(packages => this.packages = packages);
  }

  setOrderProducts(value: string) {
    if (this.orderProducts === value) {
      this.reverseProducts = !this.reverseProducts;
    }

    this.orderProducts = value;
  }

  setOrderPackages(value: string) {
    if (this.orderPackages === value) {
      this.reversePackages = !this.reversePackages;
    }

    this.orderPackages = value;
  }

  addProductToBasket(product: Product) {
    this
    .basketService
    .basket
    .products
    .push(product);
  }

  addPackageToBasket(p: Package) {
    this
    .basketService
    .basket
    .packages
    .push(p);
  }
}
