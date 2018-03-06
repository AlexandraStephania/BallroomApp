import { FormGroup, FormControl, Validators } from '@angular/forms';
import { OfferRequests } from './../../models/offerRequests';
import { Component, OnInit } from '@angular/core';
import { OfferRequestService } from '../../services/offerRequest.service';

@Component({
  selector: 'app-offer-request',
  templateUrl: './offer-request.component.html',
  styleUrls: ['./offer-request.component.scss']
})
export class OfferRequestComponent implements OnInit {

   // Component properties
   alloffersRequests: OfferRequests[];
   statusCode: number;
   requestProcessing = false;
   offerRequestIdToUpdate = null;
   processValidation = false;

   // Create form
   salonForm = new FormGroup({
     type: new FormControl('', [
       Validators.required
      ]),
    guestsNumber: new FormControl('', [
       Validators.required

      ]),
      eventDate: new FormControl('', [
       Validators.required
      ]),
      message: new FormControl('', [
       Validators.required,
       Validators.minLength(10),
       Validators.maxLength(300)
      ]),
      name: new FormControl('', [
       Validators.required,
       Validators.min(3)
      ]),
      email: new FormControl('', [
       Validators.required
      ]),
      phone: new FormControl('', [
       Validators.required
      ]),
      salon: new FormControl('', [
        Validators.required
       ])

   });

   constructor(private offerRequestService: OfferRequestService) { }

   ngOnInit() {
     this.getAllOfferRequests();
   }

    // Fetch all requests
    getAllOfferRequests() {
     this.offerRequestService.getAllOfferRequests()
  .subscribe(
             data => this.alloffersRequests = data,
             errorCode =>  this.statusCode = errorCode);
 }
}
