import { Product } from './product';

export class Package {
  id: string;
  name: string;
  description: string;
  products: Product[];
  price: number;

  constructor() {}

  getPrice(): number {
    return this.price;
  }
}
