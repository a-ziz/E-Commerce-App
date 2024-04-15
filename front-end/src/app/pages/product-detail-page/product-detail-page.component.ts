import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IProduct } from 'src/app/models/product.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-product-detail-page',
  templateUrl: './product-detail-page.component.html',
  styleUrls: ['./product-detail-page.component.css']
})
export class ProductDetailPageComponent {

  id: any;
  product?: IProduct;

  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute){
    this.id = this.activatedRoute.snapshot.paramMap.get('id');

      this.dataService.getProductById(this.id).subscribe((response: IProduct) => { 
        this.product = response;
      })
  }
}
