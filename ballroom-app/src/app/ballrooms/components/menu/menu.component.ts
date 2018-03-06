import { MenuService } from './../../services/menu.service';
import { FormGroup, Validators, FormBuilder, FormGroupDirective, AbstractControl } from '@angular/forms';
import { Menu } from './../../models/menu';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  allMenus: Menu [];
  statusCode: number;
  requestProcessing = false;
  menuIdToUpdate = null;
  processValidation = false;
  menuForm: FormGroup;
  tab= 1;
  menuNameMessage: string;

  private validationMessages = {
     required: 'Please enter a menu name.',
     minLength: 'Name must be more than 3 characters.'
  };

  // Create constructor to get service instance
  constructor(private menuService: MenuService, private fb: FormBuilder) { }

  // loading the menus
  ngOnInit() {
    this.getAllMenus();
    this.menuForm = this.fb.group ({

          menuName:  ['',
            [Validators.required,
            Validators.minLength(3)]],

          menuType: ['',
            [Validators.required,
            Validators.minLength(3)]],

          price: ['',
            Validators.required
          ],
          description: [ '',
            Validators.required
          ]
        });

      const menuNameControl =  this.menuForm.get('menuName');
      menuNameControl.valueChanges.subscribe(value =>
      this.setMessage(menuNameControl));
  }

  // Fetch all menus

  getAllMenus() {
    this.menuService.getAllMenus()
    .subscribe(
      data => this.allMenus =  data,
      errorCode => this.statusCode = errorCode);

  }
  onMenuFormSubmit(value: Menu): void {
    console.log('you submitted value: ', value);
   this.processValidation = true;
   if (this.menuForm.invalid) {
        return; // Validation failed, exit from method.
   }
   // Form is valid, now perform create or update
         this.preProcessConfigurations();

   if (this.menuIdToUpdate === null) {
     // Handle create ballroom
     this.menuService.createMenu(value)
       .subscribe(successCode => {
                 this.statusCode = successCode;
                 this.getAllMenus();
                 this.backToCreateMenu();
     },
           errorCode => this.statusCode = errorCode);
   }else {
    // Handle update menu
 value.id = this.menuIdToUpdate;
 this.menuService.updateMenu(value)
   .subscribe(successCode => {
       this.statusCode = successCode;
       this.getAllMenus();
       // this.backToCreateMenu();
 },
       errorCode => this.statusCode = errorCode);
}
  }
   // Load menu by id to edit
loadMenuToEdit(id: string) {
  this.preProcessConfigurations();
  this.menuService.getMenuById(id)
    .subscribe(menu => {
            this.menuIdToUpdate = menu.id;
            this.menuForm.setValue({
             menuName: menu.menuName,
             menuType:  menu.menuType,
             price: menu.price,
             description: menu.description
            });
      this.processValidation = true;
      this.requestProcessing = false;
    },
    errorCode =>  this.statusCode = errorCode);
 }
    // Delete menu
 deleteMenu(id: string) {
  if (window.confirm('Are sure you want to delete this menu ?')) {
      this.preProcessConfigurations();
   this.menuService.deleteMenuById(id)
     .subscribe(successCode => {
       this.statusCode = successCode;
       this.getAllMenus();
       this.backToCreateMenu();
    },
    errorCode => this.statusCode = errorCode);
   }
}
  preProcessConfigurations() {
    this.statusCode = null;
this.requestProcessing = true;
}
// Go back from update to create
backToCreateMenu() {
    this.menuIdToUpdate = null;
    this.menuForm.reset();

this.processValidation = false;
}

setMessage(c: AbstractControl): void {
  this.menuNameMessage = '';
  if ((c.touched || c.dirty) && c.errors) {
     this.menuNameMessage = Object.keys(c.errors).map(key =>
      this.validationMessages[key]).join(' ');
  }
}

}
