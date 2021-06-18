import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { Tickets } from '../../model/Tickets';
import { Occupancy } from '../../model/Occupancy';


@Injectable({
	providedIn: 'root'
})
export class TicketsService {
	private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient
	) { }

	finalPrice(ticket: Tickets): Observable<any> {
		let queryParams = {};
        queryParams = {
            headers: this.headers,
            observe: 'response',
            params: new HttpParams()
        };

		return this.http.put(`${environment.baseUrl}/${environment.tickets}/final-price`, ticket, queryParams).pipe(map(res => res));
	}
	
	add(newReservation: Tickets): Observable<any> {
		let queryParams = {};
        queryParams = {
            headers: this.headers,
            observe: 'response',
            params: new HttpParams()
        };
        return this.http.post(`${environment.baseUrl}/${environment.tickets}`, newReservation, queryParams).pipe(map(res => res));
    }

    myTickets():  Observable<any> {
		let queryParams = {};
        queryParams = {
            headers: this.headers,
            observe: 'response',
            params: new HttpParams()
        };
		return this.http.get(`${environment.baseUrl}/${environment.tickets}/my-tickets`, queryParams).pipe(map(res => res));
	}

    getOccupancy(id:any, occ: Occupancy):  Observable<any> {
		let queryParams = {};
        queryParams = {
            headers: this.headers,
            observe: 'response',
            params: new HttpParams()
        };
		return this.http.post(`${environment.baseUrl}/${environment.tickets}/occupancy/`+id, occ, queryParams).pipe(map(res => res));
	}

    delete(id: number): Observable<any> {
        return this.http.delete(`${environment.baseUrl}/${environment.tickets}/${id}`, {headers: this.headers, responseType: 'text'});
    }
	

}
