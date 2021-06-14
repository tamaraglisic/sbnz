import { Routes } from '@angular/router';
import { RoleGuard } from '../guards/role/role.service';
import { LoginGuard } from '../guards/login/login.service';
import { LoginPageComponent } from '../pages/auth/login-page/login-page.component';
import { ResortChooserComponent } from '../pages/resort/resort-chooser/resort-chooser.component';
import { MyReservationsComponent } from '../pages/reservations/my-reservations/my-reservations.component';
import { ResortCapacityComponent } from '../pages/resort/resort-capacity/resort-capacity.component';

export const routes :Routes = [
	// {
	// 	path: '',
	// 	component: HomePageComponent
	// },
	{
		path: 'login',
		component: LoginPageComponent,
		canActivate: [LoginGuard]

	},
	{
		path: 'ski-resort',
		component: ResortChooserComponent,
		canActivate: [LoginGuard]

	},
	{
		path: 'my-reservations',
		component: MyReservationsComponent,
		canActivate: [LoginGuard]

	},
	{
		path: 'capacity',
		component: ResortCapacityComponent,
		canActivate: [LoginGuard]

	}
];
