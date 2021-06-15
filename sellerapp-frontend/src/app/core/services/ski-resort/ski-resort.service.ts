import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class SkiResortService {
	private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient
	) { }

	getAll(): Observable<any> {
		let queryParams = {};
        queryParams = {
            headers: this.headers,
            observe: 'response',
            params: new HttpParams()
        };

		return this.http.get(`${environment.baseUrl}/${environment.skiResort}`, queryParams).pipe(map(res => res));
	}

	getOccupancy(id: any): Observable<any> {

		let queryParams = {};
        queryParams = {
            headers: this.headers,
            observe: 'response',
            params: new HttpParams()
        };

		return this.http.get(`${environment.baseUrl}/${environment.skiResort}/occupancy/`+id,  queryParams).pipe(map(res => res));
	}

}
