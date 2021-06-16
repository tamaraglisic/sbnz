import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { Tickets } from '../../model/Tickets';


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

	

}