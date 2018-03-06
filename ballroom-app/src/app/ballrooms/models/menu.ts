import { Ballroom } from './ballroom';
export class Menu {
   id: number;
   menuName: string;
   menuType: string;
   price: number;
   description: string;
   ballroom: Ballroom;
   constructor (
    id: string,
    menuName: string,
    menuType: string,
    price: number,
    description: string,
   ) {}
}
