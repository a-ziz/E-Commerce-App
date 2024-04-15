import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { IProduct } from 'src/app/models/product.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {
  products: IProduct[] = [];

  constructor(private dataService: DataService, private router: Router){
    this.dataService.getProducts().subscribe((product: IProduct[]) => {
      this.products = product;
    }
  )}

  onClickHandler(id: any){
    this.router.navigate(['product-detail', id]);
  }
}
