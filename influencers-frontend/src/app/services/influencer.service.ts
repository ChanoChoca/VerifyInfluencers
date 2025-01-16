import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Config} from '../models/config.model';
import {environment} from '../environments/environment';
import {map, Observable} from 'rxjs';
import {TwitterApiResponse} from '../models/influencer.model';

@Injectable({
  providedIn: 'root'
})
export class InfluencerService {
  private http = inject(HttpClient);

  // Método de búsqueda de influencers
  searchInfluencer(formData: Partial<{
    timeRange: string | null,
    influencerName: string | null,
    claimsPerInfluencer: number | null,
    productsPerInfluencer: number | null,
    includeRevenueAnalysis: boolean | null,
    verifyWithScientificJournals: boolean | null,
    selectedJournals: string[] | null,
    notesForAssistant: string | null
  }>): Observable<TwitterApiResponse> {
    let params = new HttpParams();

    // Construir los parámetros solo si tienen valor
    Object.keys(formData).forEach((key) => {
      const value = formData[key as keyof Config];
      if (value !== null && value !== undefined) {
        params = params.append(key, String(value)); // Convertir a String para pasar a la URL
      }
    });

    // Realizar la solicitud GET con los parámetros y la URL adecuada
    return this.http.post<TwitterApiResponse>(`${environment.apiUrl}/influencers/influencer`, {
      params: params
    });
  }

  // Paso 1: Crea un método para obtener los tweets recientes del influencer.
  getRecentTweets(influencerName: string): any {
    // Lógica para consumir la API de Twitter.
  }

  // Paso 2: Crea un método para obtener las transcripciones de los podcasts.
  getPodcastTranscripts(podcastId: string): any {
    // Lógica para consumir la API de transcripción.
  }

  // Paso 3: Crea un método para procesar los datos y extraer afirmaciones.
  processTranscripts(transcript: string): any {
    // Lógica para enviar el texto al backend para extraer afirmaciones.
  }
}
