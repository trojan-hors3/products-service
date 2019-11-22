import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from './../product.service';
import { Product } from './../product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: Product;

  constructor(
      private route: ActivatedRoute,
      private productService: ProductService
    ) {}

  ngOnInit() {
    this.getProduct();
  }

  getProduct(): void {
    const productId = this.route.snapshot.paramMap.get('id');

    this
    .productService
    .getProduct(productId)
    .subscribe(product => this.product = product);
  }
}
