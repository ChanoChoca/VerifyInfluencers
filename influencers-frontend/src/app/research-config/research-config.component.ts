import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {InfluencerService} from '../services/influencer.service';
import {TwitterApiResponse} from '../models/influencer.model';
import {RouterLink} from '@angular/router';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-research-config',
  imports: [
    ReactiveFormsModule,
    FaIconComponent,
    FormsModule,
    RouterLink,
    NgStyle,
  ],
  templateUrl: './research-config.component.html',
  styleUrl: './research-config.component.css'
})
export class ResearchConfigComponent implements OnInit {
  influencersTweets: TwitterApiResponse | undefined;
  selectedOption: string = 'lastMonth';
  selectedOptionLabel: string = 'Last Month';

  form = new FormGroup({
    timeRange: new FormControl('lastMonth'),
    influencerName: new FormControl(''),
    claimsPerInfluencer: new FormControl(50),
    productsPerInfluencer: new FormControl(10),
    includeRevenueAnalysis: new FormControl(true),
    verifyWithScientificJournals: new FormControl(true),
    selectedJournals: new FormControl<string[]>([]),
    notesForAssistant: new FormControl(''),
    selectedOption: new FormControl(1)
  });

  constructor(private influencerService: InfluencerService) {}

  ngOnInit(): void {}

  selectOption(option: string) {
    this.selectedOption = option;
    this.selectedOptionLabel = this.getLabelForOption(option);
  }

  getLabelForOption(option: string): string {
    switch (option) {
      case 'lastWeek': return 'Last Week';
      case 'lastMonth': return 'Last Month';
      case 'lastYear': return 'Last Year';
      case 'allTime': return 'All Time';
      default: return 'Select Time Range';
    }
  }

  toggleJournal(journal: string): void {
    const selectedJournals = this.form.get('selectedJournals')?.value || [];
    if (selectedJournals.includes(journal)) {
      // Si el journal ya está seleccionado, lo eliminamos
      this.form.get('selectedJournals')?.setValue(selectedJournals.filter(j => j !== journal));
    } else {
      // Si no está seleccionado, lo agregamos
      this.form.get('selectedJournals')?.setValue([...selectedJournals, journal]);
    }
  }

  journals = [
    'pubMedCentral',
    'science',
    'theLancet',
    'jamaNetwork',
    'nature',
    'cell',
    'newEnglandJournalOfMedicine',
  ];

  selectAllJournals(): void {
    this.form.get('selectedJournals')?.setValue([...this.journals]);
  }

  deselectAllJournals(): void {
    this.form.get('selectedJournals')?.setValue([]);
  }

  searchInfluencers(): void {
    if (this.form.value.selectedOption === 1) {
      this.influencerService.searchInfluencer(this.form.value).subscribe({
        next: (response) => {
          // Asignar la respuesta directamente
          this.influencersTweets = response;
        },
        error: (err) => {
          console.error('Error fetching influencers', err);
        }
      });
    } else {
      // Lógica alternativa en caso de que no se haya seleccionado la opción
    }
  }

  protected readonly String = String;
  protected readonly Number = Number;
}
