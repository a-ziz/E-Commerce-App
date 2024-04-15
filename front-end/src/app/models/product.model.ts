import { ICategory } from "./category.model";

export interface IProduct{
  id: number;
  title: string;
  price: number;
  description: string;
  images: [string];
  category: ICategory;
}