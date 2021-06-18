import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/User';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
	private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient
	) { }

	login(auth: any): Observable<any> {
		return this.http.post(`${environment.baseUrl}/${environment.login}`, {username: auth.username, password: auth.password}, {headers: this.headers, responseType: 'json'});
	}

	isLoggedIn(): boolean {
		if (!localStorage.getItem('user')) {
			return false;
		}
		return true;
	}
	signOut(): Observable<any> {
        return this.http.get(`${environment.baseUrl}/${environment.signOut}`, {headers: this.headers});

    }


}
