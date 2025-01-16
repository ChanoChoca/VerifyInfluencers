import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClaimVerificationService {
  constructor(private http: HttpClient) {}

  // Paso 1: Crea un método para enviar afirmaciones al backend para su verificación.
  verifyClaims(claims: any[]): any {
    // Lógica para enviar afirmaciones y recibir los resultados de categorización y verificación.
  }

  // Paso 2: Crea un método para obtener los journals disponibles desde el backend.
  getAvailableJournals(): any {
    // Lógica para obtener la lista de journals.
  }
}
