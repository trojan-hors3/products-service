import { Package } from './package';
import { Product } from './product';

export class Basket {
  products: Product[];
  packages: Package[];
  price: number;
  discount: number = 0;

  constructor() {
    this.products = [];
    this.packages = [];
    this.price = 0
  }

  setPrice(): void {
    let totalProductsPrice: number = this
    .products
    .map((product: Product) => product.usdPrice)
    .reduce((accLeft, accRight) => accLeft + accRight, 0);

    let totalPackagesPrice: number = this
    .packages
    .map((p: Package) => p.price)
    .reduce((accLeft, accRight) => accLeft + accRight, 0);

    if (totalPackagesPrice > 0) {
      this.discount = 10;
      let totalPriceBeforeDiscount = totalProductsPrice + totalPackagesPrice;
      let totalPriceWithDiscount = totalPriceBeforeDiscount - (totalPriceBeforeDiscount / this.discount);

      this.price = totalPriceWithDiscount;
    } else {
      this.price = totalProductsPrice;
    }
  }

  getBasketPrice(): number {
    this.setPrice();

    return this.price;
  }

  clearBasket(): void {
    this.products = [];
    this.packages = [];
    this.discount = 0.00;
    this.price = 0.00;
  }
}
