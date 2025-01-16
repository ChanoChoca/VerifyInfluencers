// import {Component, OnInit} from '@angular/core';
// import {RouterLink} from '@angular/router';
// import {InfluencerService} from '../services/influencer.service';
// import {faUser} from '@fortawesome/free-regular-svg-icons';
// import {FaIconComponent} from '@fortawesome/angular-fontawesome';
// import {faArrowDown, faArrowUp} from '@fortawesome/free-solid-svg-icons';
// import {LeaderboardService} from '../services/leaderboard.service';
//
// @Component({
//   selector: 'app-leaderboard',
//   imports: [
//     RouterLink,
//     FaIconComponent
//   ],
//   templateUrl: './leaderboard.component.html',
//   styleUrl: './leaderboard.component.css'
// })
// export class LeaderboardComponent implements OnInit {
//   influencers: any[] = [];
//   filteredInfluencers: any[] = [];
//   categories: string[] = ['All', 'Nutrition', 'Fitness', 'Medicine', 'Mental Health'];
//   selectedCategory: string = 'All';
//   activeInfluencers = 0;
//   claimsVerified = 0;
//   averageTrustScore = 0;
//
//   protected readonly faUser = faUser;
//   protected readonly faArrowUp = faArrowUp;
//   protected readonly faArrowDown = faArrowDown;
//
//   constructor(private leaderboardService: LeaderboardService) {}
//
//   ngOnInit(): void {
//     this.loadLeaderboardData();
//   }
//
//   loadLeaderboardData(): void {
//     this.leaderboardService.getLeaderboardData().subscribe((data) => {
//       this.influencers = data;
//       this.filteredInfluencers = data;
//       this.calculateStatistics();
//     });
//   }
//
//   calculateStatistics(): void {
//     this.activeInfluencers = this.influencers.length;
//     this.claimsVerified = this.influencers.reduce((sum, influencer) => sum + influencer.verifiedClaims, 0);
//     this.averageTrustScore =
//       this.influencers.reduce((sum, influencer) => sum + influencer.trustScore, 0) / this.influencers.length;
//   }
//
//   applyFilter(category: string): void {
//     this.selectedCategory = category;
//     if (category === 'All') {
//       this.filteredInfluencers = this.influencers;
//     } else {
//       this.filteredInfluencers = this.influencers.filter(influencer => influencer.category === category);
//     }
//   }
// }
