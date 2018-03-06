import { Salon } from './salon';

export class OfferRequests {
    id: number;
    type: string;
    guestsNumber: number;
    eventDate: Date;
    message: string;
    name: string;
    email: string;
    phone: string;
    salon: Salon;
}
