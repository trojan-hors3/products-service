export class Product {
  id: string;
  name: string;
  usdPrice: number;

  constructor() { }

  getPrice(): number {
    return this.usdPrice;
  }
}
