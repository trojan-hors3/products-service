import { Product } from './product';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'filter' })
export class FilterProductsPipe implements PipeTransform {

  transform(products: Product[], searchTerm: string): Product[] {
    if(!products) return [];
    if(!searchTerm) return products;

    searchTerm = searchTerm.toLowerCase();

    return products.filter( product => {
        return product.name.toLowerCase().includes(searchTerm);
      });
    }
}
