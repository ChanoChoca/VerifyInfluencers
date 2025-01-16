import {Component, inject, OnInit, PLATFORM_ID} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from './layout/navbar/navbar.component';
import {isPlatformBrowser} from '@angular/common';
import {ToastService} from './toast/toast.service';
import {FaConfig, FaIconLibrary} from '@fortawesome/angular-fontawesome';
import {Oauth2Service} from './auth/oauth2.service';
import {fontAwesomeIcons} from './font-awesome-icons';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  private faIconLibrary = inject(FaIconLibrary);
  private faConfig = inject(FaConfig);

  private oauth2Service = inject(Oauth2Service);

  toastService = inject(ToastService);

  platformId = inject(PLATFORM_ID);

  constructor() {
    if (isPlatformBrowser(this.platformId)) {
      this.oauth2Service.initAuthentication();
    }
    this.oauth2Service.connectedUserQuery = this.oauth2Service.fetch();
  }

  ngOnInit(): void {
    this.initFontAwesome();
  }

  private initFontAwesome() {
    this.faConfig.defaultPrefix = 'far';
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }
}
