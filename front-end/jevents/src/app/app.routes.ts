import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {EventDetailComponent} from './components/event-detail/event-detail.component';
import {EventCardComponent} from './components/event-card/event-card.component';
import {AuthGuard} from '@auth0/auth0-angular';
import {LoginComponent} from './components/login.component';
import {ProfileComponent} from './components/profile/profile.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'events/:id',
    component: EventDetailComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'search',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
];

export const AppRoutes = RouterModule.forRoot(routes, {relativeLinkResolution: 'legacy'});
