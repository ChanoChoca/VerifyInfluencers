import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {faGear, faPlus} from '@fortawesome/free-solid-svg-icons';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {InfluencerService} from '../services/influencer.service';
import {TwitterApiResponse} from '../models/influencer.model';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-research-config',
  imports: [
    ReactiveFormsModule,
    FaIconComponent,
    FormsModule,
    RouterLink,
  ],
  templateUrl: './research-config.component.html',
  styleUrl: './research-config.component.css'
})
export class ResearchConfigComponent implements OnInit {
  influencersTweets: TwitterApiResponse | undefined;
  selectedOption: string = 'lastMonth';
  selectedOptionLabel: string = 'Last Month';

  form = new FormGroup({
    timeRange: new FormControl('lastMonth'), // String
    influencerName: new FormControl(''), // String
    claimsPerInfluencer: new FormControl(50), // Number
    productsPerInfluencer: new FormControl(10), // Number
    includeRevenueAnalysis: new FormControl(true), // Boolean
    verifyWithScientificJournals: new FormControl(true), // Boolean
    selectedJournals: new FormControl<string[]>([]), // Array
    notesForAssistant: new FormControl(''), // String
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

  protected readonly faGear = faGear;
  protected readonly faPlus = faPlus;
  protected readonly String = String;
  protected readonly Number = Number;
}
