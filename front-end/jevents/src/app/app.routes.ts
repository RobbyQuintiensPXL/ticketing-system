import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {EventDetailComponent} from './components/event-detail/event-detail.component';
import {EventCardComponent} from './components/event-card/event-card.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'events/:id',
    component: EventDetailComponent,
  }
];

export const AppRoutes = RouterModule.forRoot(routes, {relativeLinkResolution: 'legacy'});
