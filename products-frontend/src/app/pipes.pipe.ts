import { Package } from './package';
import { Basket } from './basket';
import { Product } from './product';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'filter' })
export class FilterProductsPipe implements PipeTransform {

  transform(products: Product[], searchTermProduct: string): Product[] {
    if(!products) return [];
    if(!searchTermProduct) return products;

    searchTermProduct = searchTermProduct.toLowerCase();

    return products.filter( product => {
        return product.name.toLowerCase().includes(searchTermProduct);
      });
    }
}

@Pipe({ name: 'filter' })
export class FilterPackagesPipe implements PipeTransform {

  transform(packages: Package[], searchTermPackage: string): Package[] {
    if(!packages) return [];
    if(!searchTermPackage) return packages;

    searchTermPackage = searchTermPackage.toLowerCase();

    return packages.filter( p => {
        return p.name.toLowerCase().includes(searchTermPackage);
      });
    }
}
