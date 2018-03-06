import { OfferRequests } from './offerRequests';
import { Ballroom } from './ballroom';
export class Salon {
    id: number;
    salonName: string;
    address: string;
    capacity: number;
    surface: number;
    danceFloor: boolean;
    terrace: boolean;
    parking: boolean;
    ballroom: Ballroom;
    offerRequests: OfferRequests[] = [];
    constructor (
        id: number,
        salonName: string,
        address: string,
        capacity: number,
        surface: number,
        danceFloor: boolean,
        terrace: boolean,
        parking: boolean,
       ) {}

}
