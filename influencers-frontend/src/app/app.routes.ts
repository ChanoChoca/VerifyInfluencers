import { Routes } from '@angular/router';
// import {LeaderboardComponent} from './leaderboard/leaderboard.component';
// import {InfluencerDetailComponent} from './influencer-detail/influencer-detail.component';
import {ResearchConfigComponent} from './research-config/research-config.component';

export const routes: Routes = [
  // {
  //   path: 'influencer',
  //   component: InfluencerDetailComponent
  // },
  // {
  //   path: 'leaderboard',
  //   component: LeaderboardComponent
  // },
  {
    path: '',
    component: ResearchConfigComponent
  },
];
