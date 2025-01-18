import {Component, inject, OnDestroy} from '@angular/core';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import { Oauth2Service } from '../../auth/oauth2.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [
    FaIconComponent,
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnDestroy {
  oauth2Service = inject(Oauth2Service);
  isAsideMenuOpen = false;
  isDesktopView = false; // Valor predeterminado

  private readonly resizeObserver: (() => void) | undefined;
  connectedUserQuery = this.oauth2Service.connectedUserQuery;

  ngOnDestroy(): void {
    if (this.resizeObserver) {
      this.resizeObserver();
    }
  }

  constructor() {
    if (typeof window !== 'undefined') {
      this.isDesktopView = window.innerWidth > 1024;
      this.resizeObserver = this.listenToResize();
    }
  }

  login(): void {
    this.closeDropDownMenu();
    this.oauth2Service.login();
  }

  logout(): void {
    this.closeDropDownMenu();
    this.oauth2Service.logout();
  }

  isConnected(): boolean {
    return (
      this.connectedUserQuery?.status() === 'success' &&
      this.connectedUserQuery?.data()?.email !== this.oauth2Service.notConnected
    );
  }

  closeDropDownMenu() {
    const bodyElement = document.activeElement as HTMLBodyElement;
    if (bodyElement) {
      bodyElement.blur();
    }
  }

  toggleAsideMenu(): void {
    this.isAsideMenuOpen = !this.isAsideMenuOpen;
  }

  private listenToResize(): () => void {
    const onResize = () => {
      if (typeof window !== 'undefined') {
        this.isDesktopView = window.innerWidth > 1024;
      }
    };
    window.addEventListener('resize', onResize);
    onResize(); // Llama inmediatamente para establecer el estado inicial
    return () => window.removeEventListener('resize', onResize); // Limpia el evento
  }
}
