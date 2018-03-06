import { SalonService } from './../../services/salon.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Salon } from './../../models/salon';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-salon',
  templateUrl: './salon.component.html',
  styleUrls: ['./salon.component.scss']
})
export class SalonComponent implements OnInit {

  // Component properties
  allSalons: Salon[];
  statusCode: number;
  requestProcessing = false;
  salonIdToUpdate = null;
  processValidation = false;
  salonForm: FormGroup;
  tab= 1;


  constructor(private salonService: SalonService) { }

  ngOnInit() {
    this.getAllSalons();
    this.salonForm = new FormGroup({
      salonName: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
       ]),
     address: new FormControl('', [
        Validators.required,
        Validators.minLength(3)

       ]),
       capacity: new FormControl('', [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(3)
       ]),
       surface: new FormControl('', [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(3)
       ]),
       danceFloor: new FormControl('', [
       // Validators.required
       ]),
       terrace: new FormControl('', [
       // Validators.required
       ]),
       parking: new FormControl('', [
       // Validators.required
       ])

    });
  }

   // Fetch all salons
   getAllSalons() {
    this.salonService.getAllSalons()
 .subscribe(
            data => this.allSalons = data,
            errorCode =>  this.statusCode = errorCode);
}

onSalonFormSubmit(value: Salon): void {
  console.log('you submitted value: ', value);
 this.processValidation = true;
 if (this.salonForm.invalid) {
      console.log('invalid form ');
      return; // Validation failed, exit from method.
 }
 // Form is valid, now perform create or update
       this.preProcessConfigurations();
        // tslint:disable-next-line:prefer-const
        let salonName = this.salonForm.get('salonName').value.trim();
       // tslint:disable-next-line:prefer-const
       let address = this.salonForm.get('address').value.trim();
       // tslint:disable-next-line:prefer-const
       let capacity =  this.salonForm.get('capacity').value;
       // tslint:disable-next-line:prefer-const
       let surface = this.salonForm.get('surface').value;
       // tslint:disable-next-line:prefer-const
       let danceFloor = this.salonForm.get('danceFloor').value;
       // tslint:disable-next-line:prefer-const
       let terrace = this.salonForm.get('terrace').value;
       // tslint:disable-next-line:prefer-const
       let parking = this.salonForm.get('parking').value;



 if (this.salonIdToUpdate === null) {
   // Handle create salon
   // tslint:disable-next-line:prefer-const
   let salon = new Salon(null, salonName, address, capacity, surface, danceFloor, terrace, parking);
   this.salonService.createSalon(value)
     .subscribe(successCode => {
               this.statusCode = successCode;
               this.getAllSalons();
               this.backToCreateSalon();
   },
         errorCode => this.statusCode = errorCode);
 }else {
  // Handle update salon
// tslint:disable-next-line:prefer-const
let salon = new Salon(this.salonIdToUpdate, salonName, address, capacity, surface, danceFloor, terrace, parking);
value.id = this.salonIdToUpdate;
this.salonService.updateSalon(value)
 .subscribe(successCode => {
     this.statusCode = successCode;
     this.getAllSalons();

},
     errorCode => this.statusCode = errorCode);
}
}
 // Load salon by id to edit
loadSalonToEdit(id: string) {
this.preProcessConfigurations();
this.salonService.getSalonById(id)
  .subscribe(salon => {
          this.salonIdToUpdate = salon.id;
          this.salonForm.setValue({
            salonName: salon.salonName,
            address: salon.address,
            capacity: salon.capacity,
            surface: salon.surface,
            danceFloor: salon.danceFloor,
            terrace: salon.terrace,
            parking: salon.parking
          });
    this.processValidation = true;
    this.requestProcessing = false;
  },
  errorCode =>  this.statusCode = errorCode);
}
  // Delete salon
deleteSalon(id: string) {
if (window.confirm('Are sure you want to delete this menu ?')) {
    this.preProcessConfigurations();
 this.salonService.deleteSalonById(id)
   .subscribe(successCode => {
     this.statusCode = successCode;
     this.getAllSalons();
     this.backToCreateSalon();
  },
  errorCode => this.statusCode = errorCode);
 }
}
preProcessConfigurations() {
  this.statusCode = null;
this.requestProcessing = true;
}
// Go back from update to create
backToCreateSalon() {
  this.salonIdToUpdate = null;
  this.salonForm.reset();

this.processValidation = false;
}
}
