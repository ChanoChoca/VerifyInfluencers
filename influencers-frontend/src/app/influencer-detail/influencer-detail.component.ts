// import {Component, OnInit} from '@angular/core';
// import {
//   faArrowDown,
//   faArrowUp,
//   faArrowUpRightFromSquare,
//   faBagShopping, faBrain,
//   faDollarSign
// } from '@fortawesome/free-solid-svg-icons';
// import {FaIconComponent} from '@fortawesome/angular-fontawesome';
// import {FormsModule} from '@angular/forms';
// import {faCalendar} from '@fortawesome/free-regular-svg-icons';
// import {InfluencerService} from '../services/influencer.service';
// import {CurrencyPipe} from '@angular/common';
//
// @Component({
//   selector: 'app-influencer-detail',
//   imports: [
//     FaIconComponent,
//     FormsModule,
//     CurrencyPipe
//   ],
//   templateUrl: './influencer-detail.component.html',
//   styleUrl: './influencer-detail.component.css'
// })
// export class InfluencerDetailComponent implements OnInit {
//   influencer: any = {};
//   categoriesInfluencers: any[] = [];
//   claims: any[] = [];
//   searchClaim: string = '';
//   selectedCategories: string[] = [];
//   claimCategories = [
//     { value: 'nutrition', label: 'Nutrition' },
//     { value: 'medicine', label: 'Medicine' },
//     { value: 'mentalHealth', label: 'Mental Health' },
//   ];
//   verificationStatuses = [
//     { value: 'allStatuses', label: 'All Statuses' },
//     { value: 'verified', label: 'Verified' },
//     { value: 'questionable', label: 'Questionable' },
//     { value: 'debunked', label: 'Debunked' },
//   ];
//   selectedVerificationStatus: string = 'allStatuses';
//   sortBy: string = 'date';
//   option: number | undefined = undefined;
//
//   constructor(private influencerService: InfluencerService) {}
//
//   ngOnInit(): void {
//     this.fetchInfluencerDetails();
//     this.fetchClaims();
//   }
//
//   fetchInfluencerDetails() {
//     this.influencerService.getInfluencerDetails().subscribe((data) => {
//       this.influencer = data;
//       this.categoriesInfluencers = data.categories;
//     });
//   }
//
//   fetchClaims() {
//     this.influencerService.getClaims().subscribe((data) => {
//       this.claims = data;
//     });
//   }
//
//   showForm(option: number) {
//     this.option = option;
//   }
//
//   protected readonly faCalendar = faCalendar;
//   protected readonly faBrain = faBrain;
//   protected readonly faArrowUp = faArrowUp;
//   protected readonly faArrowDown = faArrowDown;
//   protected readonly faDollarSign = faDollarSign;
//   protected readonly faBagShopping = faBagShopping;
//   protected readonly faArrowUpRightFromSquare = faArrowUpRightFromSquare;
// }
