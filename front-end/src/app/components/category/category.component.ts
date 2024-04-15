import { Component, OnInit } from '@angular/core';
import { ICategory } from 'src/app/models/category.model';
import { DataService } from 'src/app/services/data.service';
import { Router } from "@angular/router";
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

  categories: ICategory[] = [];

  constructor(private dataService: DataService, private router: Router) {
    this.dataService.getCategories().subscribe((response: ICategory[]) => {
      this.categories = response;
    })
  }

  onClickHandler(){
    this.router.navigateByUrl('products');
  }

  // ngOnInit(): void {
  //   this.dataService.getCategories().subscribe((data: any[]) => {
  //     this.categories = data;
  //   })
  // }

}
